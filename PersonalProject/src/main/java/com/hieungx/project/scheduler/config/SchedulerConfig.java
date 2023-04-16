package com.hieungx.project.scheduler.config;

import com.hieungx.project.scheduler.SchedulerJobFactory;
import com.hieungx.project.scheduler.listener.ConnectorJobListener;
import com.hieungx.project.scheduler.listener.ConnectorTriggerListener;
import com.hieungx.project.scheduler.service.SchedulerService;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author hieungx
 * 06/04/2023
 **/
@Configuration
public class SchedulerConfig {
    @Resource
    private DataSource dataSource;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private QuartzProperties quartzProperties;

    @Resource
    private SchedulerService schedulerService;

    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        factory.setGlobalJobListeners(new ConnectorJobListener(schedulerService));
        factory.setGlobalTriggerListeners(new ConnectorTriggerListener(schedulerService));
        return factory;
    }
}
