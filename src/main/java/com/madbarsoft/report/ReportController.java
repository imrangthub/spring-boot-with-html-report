package com.madbarsoft.report;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

	@GetMapping("/msg1")
	public String getMsg() {

		System.out.println("From Report Controller");

		return "From Report Controller";
	}

}
