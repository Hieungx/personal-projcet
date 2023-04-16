package com.hieungx.project.scheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Getter
@Setter
public class SchedulerMetaResponseDto {
    private int numberOfJobsExecuted;

    private int numberOfJobsRunning;

    private Date runningSince;
}
