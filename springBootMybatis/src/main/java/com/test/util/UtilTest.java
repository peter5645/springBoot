package com.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.service.EmpService;

@Component
public class UtilTest {
	
	@Autowired
	private EmpService empService;
	
	public String sayHi() {
		String hi = empService.sayHallo();
		
		return hi;
	}
}
