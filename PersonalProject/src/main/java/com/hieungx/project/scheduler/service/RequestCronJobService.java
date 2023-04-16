package com.hieungx.project.scheduler.service;

import java.io.IOException;

/**
 * @author hieungx
 * 08/11/2022
 **/
public interface RequestCronJobService {
    void expireRequest() throws IOException;
}
