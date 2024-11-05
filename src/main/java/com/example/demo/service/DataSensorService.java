package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.DataSensorRepository;
//import com.example.demo.controller.UserRepository;
import com.example.demo.model.DataSensor;
//import com.example.demo.model.User;

@Service
public class DataSensorService {
	@Autowired
    private DataSensorRepository dataSensorRepository; // Định nghĩa biến userRepository
    public Page<DataSensor> findAllDataSensor(Pageable pageable) {
        return dataSensorRepository.findAll(pageable);
    }
    public Page<DataSensor> searchByCriteria(Pageable pageable, String criteria, String value,
    		LocalDateTime startTime, 
    		LocalDateTime endTime) {
        switch (criteria) {
            case "temperature":
                return dataSensorRepository.findByTempStartingWith(pageable, value);
            case "humidity":
                return dataSensorRepository.findByHumStartingWith(pageable,value);
            case "light":
                return dataSensorRepository.findByLightStartingWith(pageable,value);
            case "time":
                // Assuming value contains two timestamps for time range
//                String[] times = value.split(",");
//                LocalDateTime startTime = LocalDateTime.parse(times[0]);
//                LocalDateTime endTime = LocalDateTime.parse(times[1]);
                return dataSensorRepository.findByTimeBetween(pageable,startTime, endTime);
            default:
                return dataSensorRepository.findAll(pageable);
        }
    }
    public List<DataSensor> getLast20Records() {
        return dataSensorRepository.findLast20Records();
    }
    public List<DataSensor> getLastRecords() {
        return dataSensorRepository.findLastRecords();
    }
    public DataSensor saveDataSensor(DataSensor dataSensor) {
        return dataSensorRepository.save(dataSensor);
    }
}
