package com.hieungx.project.scheduler.job;

import com.hieungx.project.scheduler.ConnectorJobWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.UnableToInterruptJobException;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
@DisallowConcurrentExecution
@RequiredArgsConstructor
public class TestCronJob extends ConnectorJobWrapper {
    @Override
    protected void executeInternal(JobExecutionContext context) throws Exception {
        log.info("Generate Time Sheet Flatted END START");
        log.info("Generate Time Sheet Flatted END................ {}", "test");
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {

    }
}
