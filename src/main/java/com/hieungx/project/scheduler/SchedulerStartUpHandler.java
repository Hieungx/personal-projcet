package com.hieungx.project.scheduler;

import com.hieungx.project.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Slf4j
@Component
public class SchedulerStartUpHandler implements ApplicationRunner {
    @Resource
    private SchedulerService schedulerService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Schedule all new scheduler jobs at app startup - starting");
        try {
            schedulerService.startAllSchedulers();
            log.info("Schedule all new scheduler jobs at app startup - complete");
        } catch (Exception ex) {
            log.error("Schedule all new scheduler jobs at app startup - error", ex);
        }
    }
}
