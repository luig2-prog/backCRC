package com.back.crc.utils;

import org.springframework.http.HttpStatus;

public class ResponseEmisor {
	
	HttpStatus code;
	Object dataRes;
	
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus ok) {
		this.code = ok;
	}
	public Object getDataRes() {
		return dataRes;
	}
	public void setDataRes(Object dataRes) {
		this.dataRes = dataRes;
	}
	
}
