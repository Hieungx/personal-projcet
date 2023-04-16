package com.hieungx.project.scheduler.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author hieungx
 * 08/11/2022
 **/
@Getter
@Setter
public class SchedulerRequestDTO {
    @NotEmpty
    private String cronExpression;
    private int priority;
    private boolean disabled = false;
}
