package com.hieungx.project.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
public abstract class ConnectorJobWrapper implements InterruptableJob {
    @Override
    public final void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
            MutablePropertyValues pvs = new MutablePropertyValues();
            pvs.addPropertyValues(context.getScheduler().getContext());
            pvs.addPropertyValues(context.getMergedJobDataMap());
            bw.setPropertyValues(pvs, true);
            this.executeInternal(context);
        } catch (Exception ex) {
            throw new JobExecutionException(ex);
        }
    }

    protected abstract void executeInternal(JobExecutionContext context) throws Exception;
}
