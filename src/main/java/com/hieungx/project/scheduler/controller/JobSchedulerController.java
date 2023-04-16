package com.hieungx.project.scheduler.controller;

import com.hieungx.project.constant.Constants;
import com.hieungx.project.scheduler.dto.SchedulerRequestDTO;
import com.hieungx.project.scheduler.dto.SchedulerResponseDTO;
import com.hieungx.project.scheduler.entity.SchedulerJobInfo;
import com.hieungx.project.scheduler.service.JobService;
import com.hieungx.project.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @author hieungx
 * 06/04/2023
 **/
@Slf4j
//@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/schedulers")
public class JobSchedulerController {
    @Resource
    private SchedulerService schedulerService;

    @Resource
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<SchedulerResponseDTO>> listAllSchedulers() throws SchedulerException {
        return new ResponseEntity<>(jobService.listAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/synchronize")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void synchronizeSchedulers() {
        schedulerService.startAllSchedulers();
    }

    @PutMapping("/{jobId}")
    public String updateScheduleJob(@Valid @PathVariable Long jobId, @Valid @RequestBody SchedulerRequestDTO requestDto) throws SchedulerException {
        SchedulerJobInfo schedulerJobInfo = new SchedulerJobInfo();
        schedulerJobInfo.setId(jobId);
        schedulerJobInfo.setCronExpression(requestDto.getCronExpression());
        schedulerJobInfo.setDisabled(requestDto.isDisabled() ? Constants.YES : Constants.NO);
        schedulerJobInfo.setPriority(requestDto.getPriority());

        schedulerService.updateScheduleJob(schedulerJobInfo);
        return HttpStatus.OK.toString();
    }

    @PutMapping("/{jobId}/pause")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pauseScheduleJob(@Valid @PathVariable Long jobId) throws SchedulerException {
        schedulerService.pauseJob(jobId);
        log.info("Paused scheduler job with id {}", jobId);
    }

    @PutMapping("/{jobId}/resume")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resumeScheduleJob(@Valid @PathVariable Long jobId) throws SchedulerException {
        schedulerService.resumeJob(jobId);
        log.info("Resumed scheduler job with id {}", jobId);
    }

    @PutMapping("/{jobId}/start")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void startScheduleJob(@Valid @PathVariable Long jobId) throws SchedulerException {
        schedulerService.startJobNow(jobId);
        log.info("Started scheduler job with id {}", jobId);
    }

    @PutMapping("/{jobId}/stop")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void stopScheduleJob(@Valid @PathVariable Long jobId) throws SchedulerException {
        boolean result = schedulerService.unScheduleJob(jobId);
        if (result) {
            log.info("Stopped scheduler job with id {}", jobId);
        }
    }

//    @ApiOperation(value = "Interrupt a specific scheduler")
//    @PutMapping("/{jobId}/interrupt")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void interruptScheduleJob(@Valid @PathVariable Long jobId) throws SchedulerException {
//        schedulerService.interruptJob(jobId);
//        log.info("Interrupted scheduler job with id {}", jobId);
//    }

    @GetMapping("/synchronize/{jobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void synchronizeSchedulers(@Valid @PathVariable Long jobId) throws SchedulerException {
        schedulerService.syncSchedulerJob(jobId);
        log.info("Synchronize scheduler job with id {}", jobId);
    }
}

