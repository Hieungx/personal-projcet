package com.hieungx.project.scheduler.service;

import com.hieungx.project.scheduler.dto.SchedulerResponseDTO;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author hieungx
 * 05/11/2022
 **/
public interface JobService {
    void processJobReport() throws SchedulerException;

    List<SchedulerResponseDTO> listAllJobs() throws SchedulerException;
}
