package com.back.crc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.back.crc.models.Receptor;
import com.back.crc.models.prueba;
import com.back.crc.utils.OperationReceptor;
import com.back.crc.utils.Operations;
import com.back.crc.utils.ResponseEmisor;



@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class mainController {
	
	private Operations operation;
	private OperationReceptor operationReceptor;
	
	@GetMapping("/prueba")
//	@ResponseBody
	public String getTime(){
//	    time.capturedTime();
		
    return "prueba";
	}
	
	@PostMapping("/calcularCRC")
	@ResponseBody
	public ResponseEmisor getTime(@RequestBody prueba str1){
		ResponseEmisor response = new ResponseEmisor();
		try {
			operation = new Operations();
			operation.setD(prueba.getD());
			operation.setG(prueba.getG());
			operation.setCRC(operation.CRC());
			operation.mostrar();
			operation.setResTX(operation.TX());
			
			response.setCode(HttpStatus.OK);
			
			response.setDataRes(operation);
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setDataRes(e.toString());
		}
		
		return response;
	}
	
	

	@PostMapping("/calcularReceptor")
	@ResponseBody
	public ResponseEmisor getReceptor(@RequestBody Receptor str1){
		
		ResponseEmisor responseR = new ResponseEmisor();
//		try {
			operationReceptor = new OperationReceptor();
			operationReceptor.setTX(Receptor.getTX());
			operationReceptor.setG(Receptor.getG());
//			operationReceptor.setCRC(Receptor.getC());
			operationReceptor.setResultCRC(operationReceptor.CRC());
//			operationReceptor.mostrar();
		
			
			responseR.setCode(HttpStatus.OK);
			
//			responseR.setDataRes(operationReceptor);
			responseR.setDataRes(operationReceptor);
//		} catch (Exception e) {
//			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
//			response.setDataRes("Algo ha sucedido mal");
//		}
		
		return responseR;
	}
}
