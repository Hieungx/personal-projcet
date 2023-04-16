package com.hieungx.project.scheduler.service;

import com.hieungx.project.scheduler.dto.SchedulerMetaResponseDto;
import com.hieungx.project.scheduler.entity.SchedulerJobInfo;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

/**
 * @author hieungx
 * 05/11/2022
 **/
public interface SchedulerService {
    void startAllSchedulers();

    void scheduleNewJob(SchedulerJobInfo jobInfo);

    void updateScheduleJob(SchedulerJobInfo schedulerJobInfo) throws SchedulerException;

    boolean unScheduleJob(Long jobInfo) throws SchedulerException;

    boolean deleteJob(SchedulerJobInfo jobInfo) throws SchedulerException;

    void pauseJob(Long jobInfo) throws SchedulerException;

    void resumeJob(Long jobInfo) throws SchedulerException;

    void startJobNow(Long jobInfo) throws SchedulerException;

    void interruptJob(Long jobInfo) throws SchedulerException;

    SchedulerMetaResponseDto retrieveSchedulerMetaData() throws SchedulerException;

    void addSchedulerTriggerHistory(Trigger trigger, long jobRunTime, JobExecutionException exception) throws SchedulerException;

    void syncSchedulerJob(Long jobId) throws SchedulerException;
}
