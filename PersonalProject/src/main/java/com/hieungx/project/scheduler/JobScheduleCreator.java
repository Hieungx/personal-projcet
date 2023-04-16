package com.hieungx.project.scheduler;

import com.hieungx.project.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.plugins.interrupt.JobInterruptMonitorPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
@Component
public class JobScheduleCreator {
    /**
     * Create Quartz Job.
     *
     * @param jobClass   Class whose executeInternal() method needs to be called.
     * @param isDurable  Job needs to be persisted even after completion. if true, job will be persisted, not otherwise.
     * @param context    Spring application context.
     * @param jobName    Job name.
     * @param jobGroup   Job group.
     * @return JobDetail object
     */
    public JobDetail createJob(Class<? extends QuartzJobBean> jobClass, boolean isDurable,
                               ApplicationContext context, String jobName, String jobGroup, Long jobId) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(isDurable);
        factoryBean.setApplicationContext(context);
        factoryBean.setName(jobName);
        factoryBean.setGroup(jobGroup);

        // set job data map
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(Constants.JOB_ID_KEY, jobId);
        jobDataMap.put(JobInterruptMonitorPlugin.AUTO_INTERRUPTIBLE, "true");
        jobDataMap.put(jobName + jobGroup, jobClass.getName());
        factoryBean.setJobDataMap(jobDataMap);

        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    /**
     * Create cron trigger.
     *
     * @param triggerName        Trigger name.
     * @param startTime          Trigger start time.
     * @param cronExpression     Cron expression.
     * @param misFireInstruction Misfire instruction (what to do in case of misfire happens).
     * @return {@link CronTrigger}
     */
    public CronTrigger createCronTrigger(String triggerName, Date startTime, String cronExpression, int misFireInstruction, int priority) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(misFireInstruction);
        factoryBean.setPriority(priority);

        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return factoryBean.getObject();
    }

}
