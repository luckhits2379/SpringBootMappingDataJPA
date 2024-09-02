package com.ng.springboot.jpa.mapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ng.springboot.jpa.mapping.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
