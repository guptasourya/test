package com.Api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Api.entity.Employee;

@Service
public interface EmpService {

	
	public List<Employee> getAllEmployee(Integer pageNumber, Integer pageSize) ;

	
	public Employee addEmp(Employee emp);

	
	public void deleteBook(int Empid); 

	
	public Employee updateEmp(Employee emp, int empid); 

	 
}
