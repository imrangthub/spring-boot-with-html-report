package com.madbarsoft.report;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService reportService;
	

	@GetMapping("/raw-data")
	public String test() {
		System.out.println("Raw Data:"+reportService.getEmpData());
		return reportService.getEmpData();
	}

	@GetMapping(value = "/report1")
	public ResponseEntity<byte[]> sampleReport1() throws IOException, DocumentException {
		System.out.println("From Report Controller sampleReport1 get");
		return reportService.sampleReport1();
	}
	
	@PostMapping(value = "/report2")
	public ResponseEntity<byte[]> sampleReport2() throws IOException, DocumentException {
		System.out.println("From Report Controller sampleReport2 post");
		return reportService.sampleReport2();
	}
	


}
