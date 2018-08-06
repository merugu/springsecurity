package com.innovativeintelli.configurationmgmt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ConfigurationController {
	
	   @GetMapping("/uploadconfig")
	    public String checkUsernameAvailability(@RequestParam(value = "configid") String configid) {
	        return configid+" UPLOADED SUCCESSFULLY";
	    }

}
