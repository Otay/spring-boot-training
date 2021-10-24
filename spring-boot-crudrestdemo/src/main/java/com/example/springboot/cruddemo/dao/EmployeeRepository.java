package com.example.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//no need to implement crud methods!!
}
