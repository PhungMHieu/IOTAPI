package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.DataSensor;

public interface DataSensorRepository extends JpaRepository<DataSensor, Integer>{
	Page<DataSensor> findAll(Pageable pageable);
	Page<DataSensor> findByTempStartingWith(Pageable pageable,String temperature);
    Page<DataSensor> findByHumStartingWith(Pageable pageable,String humidity);
    Page<DataSensor> findByLightStartingWith(Pageable pageable,String light);
    Page<DataSensor> findByTimeBetween(Pageable pageable,LocalDateTime startTime, LocalDateTime endTime);
	
	@Query(value = "SELECT * FROM data_sensor ORDER BY id DESC LIMIT 20", nativeQuery = true)
	List<DataSensor> findLast20Records();
	@Query(value = "SELECT * FROM data_sensor ORDER BY id DESC LIMIT 1", nativeQuery = true)
	List<DataSensor> findLastRecords();
	
}
