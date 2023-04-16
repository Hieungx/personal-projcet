package com.hieungx.project.scheduler;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface SchedulerTransactional {
}
