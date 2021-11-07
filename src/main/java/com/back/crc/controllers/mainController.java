package com.back.crc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.crc.utils.prueba;



@RestController
@RequestMapping()
public class mainController {

	@GetMapping("/prueba")
//	@ResponseBody
	public String getTime(){
//	    time.capturedTime();
		
    return "prueba";
	}
//	
//	@PostMapping("/prueba")
//	@ResponseBody
//	public prueba getTime(@RequestBody prueba str1){
////	    time.capturedTime();
//		
//    return str1;
//	}
}
