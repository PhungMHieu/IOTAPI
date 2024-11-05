package com.example.demo.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ActionHistory;
import com.example.demo.model.ActionHistory;
import java.time.*;
//import com.example.demo.model.ActionHistory;
// @Repository
public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Integer>{
	Page<ActionHistory> findAll(Pageable pageable);
	Page<ActionHistory> findByDeviceTypeStartingWith(Pageable pageable,String deviceType);
	Page<ActionHistory> findByTimeBetween(Pageable pageable,LocalDateTime startTime, LocalDateTime endTime);
}
