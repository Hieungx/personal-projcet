package com.hieungx.project.scheduler.repository;

import com.hieungx.project.scheduler.entity.SchedulerTriggerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hieungx
 * 05/11/2022
 **/
@Repository
public interface SchedulerTriggerHistoryRepository extends JpaRepository<SchedulerTriggerHistory, Long> {
}
