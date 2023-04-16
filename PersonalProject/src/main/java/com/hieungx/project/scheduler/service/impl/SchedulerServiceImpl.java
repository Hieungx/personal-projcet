package com.hieungx.project.scheduler.service.impl;

import com.hieungx.project.constant.Constants;
import com.hieungx.project.scheduler.JobScheduleCreator;
import com.hieungx.project.scheduler.SchedulerTransactional;
import com.hieungx.project.scheduler.dto.SchedulerMetaResponseDto;
import com.hieungx.project.scheduler.entity.SchedulerJobInfo;
import com.hieungx.project.scheduler.entity.SchedulerTriggerHistory;
import com.hieungx.project.scheduler.repository.SchedulerJobInfoRepository;
import com.hieungx.project.scheduler.repository.SchedulerTriggerHistoryRepository;
import com.hieungx.project.scheduler.service.SchedulerService;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Service
@SchedulerTransactional
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {
    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    @Resource
    private SchedulerJobInfoRepository schedulerRepository;

    @Resource
    private SchedulerTriggerHistoryRepository triggerHistoryRepository;

    @Resource
    private ApplicationContext context;

    @Resource
    private JobScheduleCreator scheduleCreator;

    @Override
    public void startAllSchedulers() {
//        List<SchedulerJobInfo> jobInfoList = schedulerRepository.findAllSchedulerJob();
        List<SchedulerJobInfo> jobInfoList = schedulerRepository.findAllActiveSchedulerJob();
        if (!CollectionUtils.isEmpty(jobInfoList)) {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            jobInfoList.forEach(jobInfo -> scheduleJob(scheduler, jobInfo));
        }
    }

    @Override
    public void scheduleNewJob(SchedulerJobInfo jobInfo) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            String fullPackageClassName = String.format("%s.%s", jobInfo.getJobPackage(), jobInfo.getJobClass());
            Class<? extends QuartzJobBean> jobClass = (Class<? extends QuartzJobBean>) Class.forName(fullPackageClassName);
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
                    .build();
            if (!scheduler.checkExists(jobDetail.getKey())) {
                SchedulerJobInfo jobInfoEntity = schedulerRepository.save(jobInfo);

                jobDetail = scheduleCreator.createJob(jobClass,
                        true, context, jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfoEntity.getId());

                Trigger trigger = createTrigger(jobInfo);
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                log.error("scheduleNewJobRequest.jobAlreadyExist");
            }
        } catch (ClassNotFoundException e) {
            log.error("Class Not Found - {}", jobInfo.getJobClass(), e);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateScheduleJob(SchedulerJobInfo jobInfo) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = retrieveSchedulerJobInfo(jobInfo.getId());
        schedulerJobInfo.setCronExpression(jobInfo.getCronExpression());
        schedulerJobInfo.setDisabled(jobInfo.getDisabled());
        schedulerJobInfo.setPriority(jobInfo.getPriority());

        if (StringUtils.equalsIgnoreCase(Constants.YES, jobInfo.getDisabled())) {
            TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJobInfo.getJobName());
            schedulerFactoryBean.getScheduler().unscheduleJob(triggerKey);
        } else {
            // Since job is durable so rescheduleJob will not work. Need to delete job and create a new one
            deleteJob(schedulerJobInfo);
            scheduleNewJob(schedulerJobInfo);
        }
    }

    @Override
    public boolean unScheduleJob(Long jobInfo) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = retrieveSchedulerJobInfo(jobInfo);
        boolean isStopped = schedulerFactoryBean.getScheduler().unscheduleJob(new TriggerKey(schedulerJobInfo.getJobName()));
        if (isStopped) {
            schedulerJobInfo.setDisabled(Constants.YES);
        }
        return isStopped;
    }

    @Override
    public boolean deleteJob(SchedulerJobInfo jobInfo) throws SchedulerException {
        return schedulerFactoryBean.getScheduler().deleteJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
    }

    @Override
    public void pauseJob(Long jobInfo) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = retrieveSchedulerJobInfo(jobInfo);
        schedulerFactoryBean.getScheduler().pauseJob(new JobKey(schedulerJobInfo.getJobName(), schedulerJobInfo.getJobGroup()));
    }

    @Override
    public void resumeJob(Long jobInfo) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = retrieveSchedulerJobInfo(jobInfo);
        // need to enable job if it is disabled currently
        if (Constants.YES.equalsIgnoreCase(schedulerJobInfo.getDisabled())) {
            schedulerJobInfo.setDisabled(Constants.NO);

            deleteJob(schedulerJobInfo);
            scheduleNewJob(schedulerJobInfo);
        } else {
            schedulerFactoryBean.getScheduler().resumeJob(new JobKey(schedulerJobInfo.getJobName(), schedulerJobInfo.getJobGroup()));
        }
    }

    @Override
    public void startJobNow(Long jobInfo) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = retrieveSchedulerJobInfo(jobInfo);
        // need to enable job if it is disabled currently
        if (Constants.YES.equalsIgnoreCase(schedulerJobInfo.getDisabled())) {
            schedulerJobInfo.setDisabled(Constants.NO);

            deleteJob(schedulerJobInfo);
            scheduleNewJob(schedulerJobInfo);
        } else {
            schedulerFactoryBean.getScheduler().triggerJob(new JobKey(schedulerJobInfo.getJobName(), schedulerJobInfo.getJobGroup()));
        }
    }

    @Override
    public void interruptJob(Long jobInfo) throws SchedulerException {
        // Do nothing at the moment
    }

    @Override
    public SchedulerMetaResponseDto retrieveSchedulerMetaData() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        SchedulerMetaData metaData = scheduler.getMetaData();
        SchedulerMetaResponseDto responseDto = new SchedulerMetaResponseDto();
        responseDto.setNumberOfJobsExecuted(metaData.getNumberOfJobsExecuted());
        responseDto.setRunningSince(metaData.getRunningSince());
        responseDto.setNumberOfJobsRunning(scheduler.getCurrentlyExecutingJobs().size());
        return responseDto;
    }

    @Override
    public void addSchedulerTriggerHistory(Trigger trigger, long jobRunTime, JobExecutionException exception) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = trigger.getJobKey();
        TriggerKey triggerKey = trigger.getKey();
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

        SchedulerTriggerHistory triggerHistory = new SchedulerTriggerHistory();
        triggerHistory.setJobId(jobDetail.getJobDataMap().getLong(Constants.JOB_ID_KEY));
        triggerHistory.setJobGroup(jobKey.getGroup());
        triggerHistory.setJobName(jobKey.getName());
        triggerHistory.setTriggerGroup(triggerKey.getGroup());
        triggerHistory.setTriggerName(triggerKey.getName());
        triggerHistory.setDescription(trigger.getDescription());
        triggerHistory.setPriority(trigger.getPriority());
        triggerHistory.setMisfireInstr(trigger.getMisfireInstruction());
        triggerHistory.setNextFireTime(trigger.getNextFireTime());
        triggerHistory.setPrevFireTime(trigger.getPreviousFireTime());
        triggerHistory.setTriggeredTime(new Date());
        triggerHistory.setElapsedTime(BigDecimal.valueOf(jobRunTime / 1000).setScale(2, RoundingMode.UP));

        String triggerStatus;
        if (exception != null) {
            triggerStatus = Constants.STATUS_JOB_ERROR;
            triggerHistory.setErrorMessage(exception.getMessage().length() <= Constants.ERROR_MESSAGE_SIZE
                    ? exception.getMessage()
                    : exception.getMessage().substring(0, Constants.ERROR_MESSAGE_SIZE));
        } else {
            triggerStatus = Constants.STATUS_JOB_COMPLETE;
        }
        triggerHistory.setTriggerState(triggerStatus);

        if (trigger instanceof CronTrigger) {
            triggerHistory.setTriggerType(Constants.TRIGGER_TYPE_CRON);
        } else {
            triggerHistory.setTriggerType(Constants.TRIGGER_TYPE_MANUAL);
        }

        triggerHistoryRepository.save(triggerHistory);
    }

    private SchedulerJobInfo retrieveSchedulerJobInfo(Long jobId) throws SchedulerException {
        try {
            return schedulerRepository.getOne(jobId);
        } catch (EntityNotFoundException e) {
            throw new SchedulerException("Scheduler not found");
        }
    }

    /**
     * Create a new cron/simple trigger based on scheduler job information.
     *
     * @param jobInfo SchedulerJobInfo
     * @return a new Trigger
     */
    private Trigger createTrigger(SchedulerJobInfo jobInfo) {
        return scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(), jobInfo.getCronExpression(),
                CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING, jobInfo.getPriority());
    }

    /**
     * Schedule a job.
     *
     * @param scheduler Scheduler
     * @param jobInfo   SchedulerJobInfo
     */
    private void scheduleJob(Scheduler scheduler, SchedulerJobInfo jobInfo) {
        try {
            String jobName = jobInfo.getJobName();
            String jobGroup = jobInfo.getJobGroup();
            String jobInfoCronExpression = jobInfo.getCronExpression();
            String fullPackageClassName = String.format("%s.%s", jobInfo.getJobPackage(), jobInfo.getJobClass());
            log.info("jobName: {}, jobGroup: {}, cronExpression: {}, fullPackageClassName: {}",
                    jobName, jobGroup, jobInfoCronExpression, fullPackageClassName);
            Class<? extends QuartzJobBean> jobClass = (Class<? extends QuartzJobBean>) Class.forName(fullPackageClassName);
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(jobName, jobGroup)
                    .build();

            boolean isExistingJob = scheduler.checkExists(jobDetail.getKey());

            if (isExistingJob) {
                TriggerKey triggerKey = new TriggerKey(jobName);
                CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
                if (trigger != null) {
                    boolean hasChanged = false;
                    // Check if one of these fields have been changed: cron_expression, priority, disabled

                    // If yes then reschedule the job
                    String cronExpression = trigger.getCronExpression();
                    if (!StringUtils.equals(cronExpression, jobInfoCronExpression)
                            && CronExpression.isValidExpression(jobInfoCronExpression)) {

                        // Cron expression has been updated manually in database - reschedule this job
                        trigger.setCronExpression(jobInfoCronExpression);
                        hasChanged = true;
                    }

                    // Check priority
                    if (trigger.getPriority() != jobInfo.getPriority()) {
                        trigger.setPriority(jobInfo.getPriority());
                        hasChanged = true;
                    }

                    // One of cater fields has been changed. Need to reschedule job to reflect those changes
                    if (hasChanged) {
                        scheduler.rescheduleJob(triggerKey, trigger);
                    }

                    if (StringUtils.equalsIgnoreCase(Constants.YES, jobInfo.getDisabled())) {
                        scheduler.unscheduleJob(triggerKey);
                    }
                } else {
                    // Job has been stopped so there is no trigger attached to this job.
                    deleteJob(jobInfo);
                    scheduleNewJob(jobInfo);
                }
            } else {
                jobDetail = scheduleCreator.createJob(jobClass, true, context, jobName, jobGroup, jobInfo.getId());
                Trigger trigger;
                if (CronExpression.isValidExpression(jobInfoCronExpression)) {
                    trigger = scheduleCreator.createCronTrigger(jobName, new Date(),
                            jobInfoCronExpression, CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING, jobInfo.getPriority());

                    scheduler.scheduleJob(jobDetail, trigger);
                } else {
                    log.error("Invalid Cron expression of - {} - {}", jobInfo.getJobClass(), jobInfoCronExpression);
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("Class Not Found - {}", jobInfo.getJobClass(), e);
        } catch (SchedulerException | ParseException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void syncSchedulerJob(Long jobId) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = retrieveSchedulerJobInfo(jobId);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob(scheduler, schedulerJobInfo);
    }
}
