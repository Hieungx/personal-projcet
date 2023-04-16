package com.hieungx.project.scheduler.service.impl;

import com.hieungx.project.scheduler.dto.SchedulerResponseDTO;
import com.hieungx.project.scheduler.entity.SchedulerJobInfo;
import com.hieungx.project.scheduler.repository.SchedulerJobInfoRepository;
import com.hieungx.project.scheduler.repository.SchedulerTriggerHistoryRepository;
import com.hieungx.project.scheduler.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Resource
    private SchedulerJobInfoRepository schedulerRepository;

    @Resource
    private SchedulerTriggerHistoryRepository schedulerTriggerHistoryRepository;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @Override
    public void processJobReport() {
        log.info("Start processJobReport");
        log.info("End processJobReport");
    }

    @Override
    public List<SchedulerResponseDTO> listAllJobs() {
        List<SchedulerResponseDTO> resultList = new ArrayList<>();
        List<SchedulerJobInfo> jobInfoList = schedulerRepository.findAllActiveSchedulerJob();
        if (!CollectionUtils.isEmpty(jobInfoList)) {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            jobInfoList.stream()
                    .map(jobInfo -> populateSchedulerResponseDto(scheduler, jobInfo))
                    .filter(Objects::nonNull)
                    .forEach(resultList::add);

        }
        return resultList;
    }

    private SchedulerResponseDTO populateSchedulerResponseDto(Scheduler scheduler, SchedulerJobInfo jobInfo) {
        String jobName = jobInfo.getJobName();
        String jobGroup = jobInfo.getJobGroup();
        String fullPackageClassName = String.format("%s.%s", jobInfo.getJobPackage(), jobInfo.getJobClass());

        SchedulerResponseDTO responseDto = new SchedulerResponseDTO();
        responseDto.setJobId(jobInfo.getId());
        responseDto.setJobName(jobName);
        responseDto.setJobGroup(jobGroup);
        responseDto.setJobClass(fullPackageClassName);
        responseDto.setCronExpression(jobInfo.getCronExpression());

        TriggerKey triggerKey = new TriggerKey(jobName);
        CronTriggerImpl trigger;
        try {
            trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);

            if (trigger != null) {
                responseDto.setNextFireTime(trigger.getNextFireTime());
                responseDto.setPreviousFireTime(trigger.getPreviousFireTime());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                responseDto.setJobStatus(triggerState.name());
            } else {
                // Completed triggers will be removed automatically
                responseDto.setJobStatus(Trigger.TriggerState.COMPLETE.name());
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
        return responseDto;
    }

    private long getTotalActiveJob() {
        long totalActiveJob;
        totalActiveJob = schedulerRepository.countTotalActiveJob();
        return totalActiveJob;
    }
}
