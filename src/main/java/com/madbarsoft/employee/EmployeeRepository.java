package com.madbarsoft.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
	
	
	
	public List<EmployeeDto> getEmpList(){
		
		List<EmployeeDto> empList = new ArrayList<>();
		
		empList.add(new EmployeeDto("ID-87OIUJDO", "MD IMRAN HOSSAIN", "MALE"));
		empList.add(new EmployeeDto("ID-87CVCVDO", "MD IMRAN MADBAR", "MALE"));
		empList.add(new EmployeeDto("ID-8721SFDO", "MD IMRAN KHAN", "MALE"));
		
		
		
		
		return empList;
	}
	
	

}
