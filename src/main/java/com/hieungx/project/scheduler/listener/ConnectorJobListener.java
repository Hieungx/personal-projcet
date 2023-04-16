package com.hieungx.project.scheduler.listener;

import com.hieungx.project.constant.Constants;
import com.hieungx.project.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
public class ConnectorJobListener implements JobListener {
    private SchedulerService schedulerService;

    public ConnectorJobListener(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    public String getName() {
        return Constants.JOB_LISTENER_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        log.info("{} is going to start...", jobName);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        log.info("{}'s execution is vetoed...", jobName);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException exception) {
        try {
            this.schedulerService.addSchedulerTriggerHistory(jobExecutionContext.getTrigger(), jobExecutionContext.getJobRunTime(), exception);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        log.info("{} is finished in {} ms. Error: {}", jobName, jobExecutionContext.getJobRunTime(), exception != null);
    }
}
