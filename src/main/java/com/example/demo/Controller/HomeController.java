package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
	@GetMapping("/index")
    public String index() {
        return "index";
    }
//	@GetMapping("/dataSensor")
//    public String dataSensor() {
//        return "dataSensor";
//    }
//	@GetMapping("/actionHistory")
//    public String actionHistory() {
//        return "actionHistory";
//    }
	@GetMapping("/profile")
    public String profile() {
        return "profile";
    }
//	@GetMapping("/rac")
//    public String rac() {
//        return "profile";
//    }
}
