package com.Api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Api.Exceptionhandler.resourceNotFound;
import com.Api.Repository.UserRepository;
import com.Api.entity.Employee;

@Service
public class ServiceClass  implements EmpService{
 
	@Autowired
	UserRepository userRepository;
	
	// employee
	public List<Employee> getAllEmployee(Integer pageNumber,Integer pageSize ){
	
		Pageable p=PageRequest.of(pageNumber, pageSize); 
		  Page<Employee> findAll = this.userRepository.findAll(p);
		 List<Employee> content = findAll.getContent();
		 return (List<Employee>) content;
		
	}
	//get one employee
	public Employee getEmpById(int id) {
		Employee emp=null;
		try {
		  emp = this.userRepository.findById(id).orElseThrow(()->new resourceNotFound("Id not found",id));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	//Adding employee
	public Employee addEmp(Employee emp) {
		
		Employee result= userRepository.save(emp);
		return result; 
	}
	//delete one book
	public void deleteBook(int Empid) {
		 
		userRepository.deleteById(Empid);
	}
	//update book
	public Employee updateEmp(Employee emp,int empid) {
		
		  Employee emp1;
		  Employee save = null;
		try {
			emp1 = this.userRepository.findById(empid).orElseThrow(()-> new resourceNotFound("User Not Found",empid));
			emp1.setName(emp.getName());
			 emp1.setCity(emp.getCity());
			  save = this.userRepository.save(emp1);		
		} catch (resourceNotFound e) {
			
			e.printStackTrace();
		}
		 
		 return save;
		 
	}
	
    public List<Employee> findByName(String name) {
    	
       	List<Employee> findByName =  userRepository.findByName(name);
       	
    	return findByName;
    }
    
    
	public List<Employee> findByCity(String city){
		  
		
		List<Employee> findByCity = userRepository.findByCity(city);
		return findByCity;
	}
	
	public List<Employee> findByPosition(String position){
	    List<Employee> findByPosition = userRepository.findByPosition(position);
	    return findByPosition;
	}
	
	
	
	
}
