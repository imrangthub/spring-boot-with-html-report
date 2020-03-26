package com.madbarsoft.report;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	
	public String getEmpData() {
		return reportRepository.getEmp().toString();
	}

	public ResponseEntity<byte[]> sampleReport1() throws DocumentException, IOException {
		return reportRepository.sampleReport1();
	}
	
	public ResponseEntity<byte[]> sampleReport2() throws DocumentException, IOException {
		return reportRepository.sampleReport2();
	}
	


}
