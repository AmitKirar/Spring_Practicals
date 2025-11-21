package com.rays.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping("test")
public class TestCtl {

	@GetMapping("display")
	public String display() {
		return "in display method";
	}

	@GetMapping("display1")
	public TestDTO display1() {

		TestDTO dto = new TestDTO();
		dto.setId(1L);
		dto.setFirstName("lucky");
		dto.setLastName("kirar");
		dto.setLoginId("lucky@gmail.com");
		dto.setPassword("1234");
		dto.setDob("01-06-2025");
		return dto;

	}

	@GetMapping("display2")
	public ORSResponse display2() {

		ORSResponse res = new ORSResponse();
		return res;
	}

	@GetMapping("display3")
	public ORSResponse display3() {
		ORSResponse res = new ORSResponse();

		Map errors = new HashMap();

		errors.put("firstName", "first name is required");
		errors.put("lastName", "last name is required");
		errors.put("loginId", "login id is required");
		errors.put("password", "password is required");
		res.addInputError(errors);

		return res;
	}

	@GetMapping("display4")
	public ORSResponse display4() {

		ORSResponse res = new ORSResponse();

		TestDTO dto = new TestDTO();

		dto.setId(1L);
		dto.setFirstName("amit");
		dto.setLastName("kirar");
		dto.setLoginId("amit@gmail.com");
		dto.setPassword("123");

		res.setSuccess(true);
		res.addData(dto);

		return res;
	}

	@GetMapping("display5")
	public ORSResponse display5() {
		ORSResponse res = new ORSResponse();
		TestDTO dto = new TestDTO();

		dto.setId(1L);
		dto.setFirstName("lucky");
		dto.setLastName("kirar");
		dto.setLoginId("lucky@gmail.com");
		dto.setPassword("123");

		TestDTO dto1 = new TestDTO();
		dto1.setId(2L);
		dto1.setFirstName("komal");
		dto1.setLastName("kirar");
		dto1.setLoginId("komal@gmail.com");
		dto1.setPassword("123");

		List list = new ArrayList();

		res.setSuccess(true);
		list.add(dto);
		list.add(dto1);

		res.addData(list);

		return res;
	}
	
	@GetMapping("diaplay6")
	public ORSResponse display6() { 
		
		ORSResponse res = new ORSResponse();
		
		res.addMessage("data added successfully");
		res.setSuccess(true);
		return res;
	}
	
	@GetMapping("display7")
	public ORSResponse display7() { 
		
		ORSResponse res = new ORSResponse();
		
		res.addResult("preload", "preload method");
		 return res ;
	}
	
	
	
}
