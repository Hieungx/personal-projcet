package com.hieungx.project.scheduler.repository;

import com.hieungx.project.scheduler.entity.SchedulerJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author hieungx
 * 05/11/2022
 **/
public interface SchedulerJobInfoRepository extends JpaRepository<SchedulerJobInfo, Long> {
    @Query("SELECT u FROM SchedulerJobInfo u WHERE u.disabled IS NULL OR u.disabled = 'N' ORDER BY u.jobName")
    List<SchedulerJobInfo> findAllActiveSchedulerJob();

    @Query("SELECT u FROM SchedulerJobInfo u ORDER BY u.jobName")
    List<SchedulerJobInfo> findAllSchedulerJob();

    @Query("SELECT COUNT(u) FROM SchedulerJobInfo u WHERE u.disabled IS NULL OR u.disabled = 'N'")
    long countTotalActiveJob();
}
