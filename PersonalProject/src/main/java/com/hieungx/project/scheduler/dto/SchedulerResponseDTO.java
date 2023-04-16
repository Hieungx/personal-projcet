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
public class SchedulerResponseDTO {

    private Long jobId;

    private String cronExpression;

    private String jobStatus;

    private String jobName;

    private String jobGroup;

    private String jobClass;

    private String emailTo;

    private String emailCc;

    private Date nextFireTime;

    private Date previousFireTime;
}
