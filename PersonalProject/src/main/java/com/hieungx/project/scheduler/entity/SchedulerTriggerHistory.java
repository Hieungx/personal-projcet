package com.hieungx.project.scheduler.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Getter
@Setter
@Entity
@Table(name = "scheduler_trigger_history")
public class SchedulerTriggerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "trigger_name")
    private String triggerName;

    @Column(name = "trigger_group")
    private String triggerGroup;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "description")
    private String description;

    @Column(name = "next_fire_time")
    private Date nextFireTime;

    @Column(name = "prev_fire_time")
    private Date prevFireTime;

    @Column(name = "triggered_time")
    private Date triggeredTime;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "trigger_state")
    private String triggerState;

    @Column(name = "trigger_type")
    private String triggerType;

    @Column(name = "misfire_instr")
    private Integer misfireInstr;

    @Column(name = "elapsed_time")
    private BigDecimal elapsedTime;

    @Column(name = "error_message")
    private String errorMessage;
}
