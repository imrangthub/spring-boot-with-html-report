package com.madbarsoft.report;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.madbarsoft.barcode.BarcodeReplacedElementFactory;
import com.madbarsoft.employee.EmployeeDto;
import com.madbarsoft.employee.EmployeeRepository;

@Repository
public class ReportRepository {

	@Autowired
	private TemplateEngine templateEngine;

	private static final String UTF_8 = "UTF-8";
	
	@Autowired
	private EmployeeRepository employeeRepository;
	

	public ResponseEntity<byte[]> sampleReport1() throws DocumentException, IOException {

		ITextRenderer renderer = new ITextRenderer();
		Context context = new Context();
		
		String renderedHtmlContent = templateEngine.process("report-templates/simple-report1", context);
		String xHtml = convertToXhtml(renderedHtmlContent);

		renderer.setDocumentFromString(xHtml);
		renderer.layout();

		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		renderer.createPDF(os);
		
		return ResponseEntity.ok().body(os.toByteArray());
	}
	
	
	

	public ResponseEntity<byte[]> sampleReport2() throws DocumentException, IOException {

		Context context = new Context();
		context.setVariable("empList", employeeRepository.getEmpList());
		context.setVariable("code1", "1212");
		context.setVariable("code2", "BABU");

		String renderedHtmlContent = templateEngine.process("report-templates/simple-report2", context);
		String xHtml = convertToXhtml(renderedHtmlContent);

		ITextRenderer renderer = new ITextRenderer();
		renderer.getSharedContext()
				.setReplacedElementFactory(new BarcodeReplacedElementFactory(renderer.getOutputDevice()));

		renderer.getFontResolver().addFont("templates/report-templates/Code39.ttf", IDENTITY_H, EMBEDDED);
		renderer.getFontResolver().addFont("templates/report-templates/Segoe UI.ttf", IDENTITY_H, EMBEDDED);

		String baseUrl = FileSystems.getDefault().getPath("src", "main", "resources", "templates", "report-templates")
				.toUri().toURL().toString();

		renderer.setDocumentFromString(xHtml, baseUrl);
		renderer.layout();

		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		renderer.createPDF(os);

		return ResponseEntity.ok().body(os.toByteArray());
	}
	
	
	
//
//	public ResponseEntity<byte[]> sampleReport3() throws DocumentException, IOException {
//
//		Context context = new Context();
//		context.setVariable("data", "SomeData");
//
//		String renderedHtmlContent = templateEngine.process("report-templates/simple-report3", context);
//		String xHtml = convertToXhtml(renderedHtmlContent);
//
//		ITextRenderer renderer = new ITextRenderer();
//		renderer.getSharedContext()
//				.setReplacedElementFactory(new BarcodeReplacedElementFactory(renderer.getOutputDevice()));
//
//		renderer.getFontResolver().addFont("templates/report-templates/Code39.ttf", IDENTITY_H, EMBEDDED);
//		renderer.getFontResolver().addFont("templates/report-templates/Segoe UI.ttf", IDENTITY_H, EMBEDDED);
//
//		String baseUrl = FileSystems.getDefault().getPath("src", "main", "resources", "templates", "report-templates")
//				.toUri().toURL().toString();
//
//		renderer.setDocumentFromString(xHtml, baseUrl);
//		renderer.layout();
//
//		final ByteArrayOutputStream os = new ByteArrayOutputStream();
//		renderer.createPDF(os);
//
//		return ResponseEntity.ok().body(os.toByteArray());
//	}
	
	
	
	public List<EmployeeDto> getEmp(){
		return employeeRepository.getEmpList();
	}

	

	private String convertToXhtml(String html) throws UnsupportedEncodingException {
		Tidy tidy = new Tidy();
		tidy.setInputEncoding(UTF_8);
		tidy.setOutputEncoding(UTF_8);
		tidy.setXHTML(true);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parseDOM(inputStream, outputStream);
		return outputStream.toString(UTF_8);
	}
	
	
	
	

}
