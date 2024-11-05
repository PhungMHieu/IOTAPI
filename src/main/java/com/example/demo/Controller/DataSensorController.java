package com.example.demo.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DataSensor;
import com.example.demo.service.DataSensorService;

@RestController
//@Controller
public class DataSensorController {
	@Autowired
	private DataSensorService dataSensorService; // Định nghĩa biến userService
	@GetMapping("/dataSensor")
//	public String listDataSensor
	 public Page<DataSensor> listDataSensor
		(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortOrder,
			@RequestParam(required = false) String criteria, 
			@RequestParam(required = false) String value, 
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
	        Model model)
	{
			Sort sort = Sort.by(sortBy);
	        sort = sortOrder.equals("desc") ? sort.descending() : sort.ascending();
	        Pageable pageable = PageRequest.of(page, size,sort);
			Page<DataSensor> dataSensorPage;
			if(criteria == null){
				dataSensorPage = dataSensorService.findAllDataSensor(pageable);
			}else{
				dataSensorPage = dataSensorService.searchByCriteria(pageable, criteria, value,startTime,endTime);
			}
	        // Page<DataSensor> dataSensorPage = dataSensorService.findAllDataSensor(pageable);
	        
	        model.addAttribute("dataSensorPage", dataSensorPage);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", size);
	        model.addAttribute("sortBy", sortBy);
	        model.addAttribute("sortOrder", sortOrder);
			model.addAttribute("criteria", criteria); // Add criteria to model
        	model.addAttribute("value", value); // Add value to model
			 return dataSensorPage;
//	        return "dataSensor";
		
	}
	@GetMapping("/last20")
	@ResponseBody
    public List<DataSensor> getLast20Data() {
        return dataSensorService.getLast20Records();
    }
	@GetMapping("/last")
	@ResponseBody
    public List<DataSensor> getLastData() {
        return dataSensorService.getLastRecords();
    }
	@PostMapping("/saveDataSensor")
	public ResponseEntity<DataSensor> saveDataSensor(@RequestBody DataSensor dataSensor) {
        DataSensor savedDataSensor = dataSensorService.saveDataSensor(dataSensor);
        return ResponseEntity.ok(savedDataSensor);
    }
}
