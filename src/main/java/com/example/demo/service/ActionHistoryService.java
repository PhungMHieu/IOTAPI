package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.ActionHistoryRepository;
//import com.example.demo.Controller.ActionHistoryRepository;
import com.example.demo.model.ActionHistory;
import com.example.demo.model.ActionHistory;
//import com.example.demo.model.ActionHistory;
import com.example.demo.model.ActionHistory;

@Service
public class ActionHistoryService {
	@Autowired
    private ActionHistoryRepository actionHistoryRepository; // Định nghĩa biến userRepository
    public Page<ActionHistory> findAllActionHistory(Pageable pageable) {
        return actionHistoryRepository.findAll(pageable);
    }
    public Page<ActionHistory> searchByCriteria(Pageable pageable, String criteria, String value, 
    		LocalDateTime startTime, 
    		LocalDateTime endTime) {
        switch (criteria) {
            case "deviceType":
                return actionHistoryRepository.findByDeviceTypeStartingWith(pageable, value);
            case "time":
                // Assuming value contains two timestamps for time range
//                String[] times = value.split(",");
//                LocalDateTime startTime = LocalDateTime.parse(times[0]);
//                LocalDateTime endTime = LocalDateTime.parse(times[1]);
//                System.out.println(value);
                return actionHistoryRepository.findByTimeBetween(pageable,startTime, endTime);
            default:
                return actionHistoryRepository.findAll(pageable);
        }
    }
    public ActionHistory saveActionHistory(ActionHistory actionHistory) {
        return actionHistoryRepository.save(actionHistory);
    }
}
