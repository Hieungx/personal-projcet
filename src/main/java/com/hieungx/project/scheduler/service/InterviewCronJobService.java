package com.hieungx.project.scheduler.service;

import java.util.Date;

/**
 * @author hieungx
 * 19/11/2022
 **/
public interface InterviewCronJobService {
    void expiredInterviewTime(Date currentDate);
}
