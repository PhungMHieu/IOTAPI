package com.example.demo.Controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ActionHistory;
import com.example.demo.model.ActionHistory;
import com.example.demo.service.ActionHistoryService;
import com.example.demo.service.ActionHistoryService;
import java.time.LocalDateTime;
 @RestController
//@Controller
public class ActionHistoryController {
	@Autowired
	private ActionHistoryService actionHistoryService; // Định nghĩa biến userService
	@GetMapping("/actionHistory")
//	public String listActionHistory
	 public Page<ActionHistory> listActionHistory
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
        	
	        Page<ActionHistory> actionHistoryPage; 
			if(criteria == null){
				actionHistoryPage = actionHistoryService.findAllActionHistory(pageable);
			}else{
				actionHistoryPage = actionHistoryService.searchByCriteria(pageable, criteria, value,startTime,endTime);
			}
			
	        
	        model.addAttribute("actionHistoryPage", actionHistoryPage);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", size);
	        model.addAttribute("sortBy", sortBy);
	        model.addAttribute("sortOrder", sortOrder);
			model.addAttribute("criteria", criteria); // Add criteria to model
        	model.addAttribute("value", value); // Add value to model
			 return actionHistoryPage;
//	        return "actionHistory";
		
	}
	@GetMapping("/saveActionHistory")
	public ResponseEntity<ActionHistory> saveActionHistory(@RequestBody ActionHistory actionHistory) {
        ActionHistory savedActionHistory = actionHistoryService.saveActionHistory(actionHistory);
        return ResponseEntity.ok(savedActionHistory);
    }
}
