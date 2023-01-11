package com.Api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.entity.Employee;


public interface UserRepository extends JpaRepository<Employee, Integer>{

	 
	public List<Employee> findByName(String name);
	public List<Employee> findByCity(String city);
	public List<Employee> findByPosition(String position);
	
	//public List<employee> findByNameStartingWith(String prefix);
	//public List<employee> findByNameEndingWith(String suffix);
}
