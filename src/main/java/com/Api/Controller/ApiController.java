package com.Api.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Api.Exceptionhandler.resourceNotFound;
import com.Api.Repository.UserRepository;
import com.Api.Service.EmpService;
import com.Api.Service.ServiceClass;
import com.Api.entity.Employee;
import com.Api.entity.Response;

@RestController

public class ApiController {

	@Autowired
	private ServiceClass serviceclass;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmpService empService;

	// get all employee
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getBooks(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
		List<Employee> list = serviceclass.getAllEmployee(pageNumber, pageSize);
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	// get one book
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmp(@PathVariable("id") int id) throws resourceNotFound {
	
		Employee emp = userRepository.findById(id).orElseThrow(()-> new resourceNotFound("User Not found",id));
		if (emp == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(emp));
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> addBook(@RequestBody Employee emp) {
		Employee e = null;
		try {
			e = this.serviceclass.addEmp(emp);
			System.out.println(emp);
			return ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (Exception e2) {
			e2.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Response> deleteEmp(@PathVariable int id) {

		this.serviceclass.deleteBook(id);

		return new ResponseEntity(new Response("Deleted Successfully", true), HttpStatus.OK);
	}

	// put employee or update
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee emp, @PathVariable int id) {

		Employee updateEmp = this.serviceclass.updateEmp(emp, id);
		return new ResponseEntity<Employee>(updateEmp, HttpStatus.CREATED);
	}

	// get employee by name

	@GetMapping("/emp/{name}")
	public ResponseEntity<List<Employee>> getEmpByName(@PathVariable("name") String name) {

		List<Employee> findByName = serviceclass.findByName(name);
		return new ResponseEntity<List<Employee>>(findByName, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/emp1/{city}")
	public ResponseEntity<List<Employee>> getEmpByCity(@PathVariable("city") String city) {
	
		List<Employee> findByCity = this.serviceclass.findByCity(city);
		return new ResponseEntity<List<Employee>>(findByCity,HttpStatus.OK);

	}
	@GetMapping("/empl/{position}")
	  public ResponseEntity<List<Employee>> getEmpByPosition(@PathVariable("position") String position) throws resourceNotFound {
		  
		     List<Employee> findByPosition = this.serviceclass.findByPosition(position);
		     if(findByPosition.size()<=0) {
		    	// return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		    	throw new resourceNotFound();
		     }
		     return new ResponseEntity<List<Employee>>(findByPosition,HttpStatus.ACCEPTED);
	  }
	
	
	
	
	
	
	
}