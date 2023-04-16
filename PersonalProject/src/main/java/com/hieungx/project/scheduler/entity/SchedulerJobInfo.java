package com.hieungx.project.scheduler.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Getter
@Setter
@Entity
@Table(name = "scheduler_job_info")
public class SchedulerJobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "job_package")
    private String jobPackage;

    @Column(name = "job_class")
    private String jobClass;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "priority")
    private int priority;

    @Column(name = "remark")
    private String remark;

    @Column(name = "disabled")
    private String disabled;
}
