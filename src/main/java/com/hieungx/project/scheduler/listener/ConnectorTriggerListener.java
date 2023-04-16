package com.hieungx.project.scheduler.listener;

import com.hieungx.project.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
public class ConnectorTriggerListener implements TriggerListener {

    private SchedulerService schedulerService;

    public ConnectorTriggerListener(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        log.info("Trigger {} completed in {}", completedExecutionInstruction.name(), jobExecutionContext.getJobRunTime());
    }
}
