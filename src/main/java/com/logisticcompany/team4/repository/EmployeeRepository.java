package com.logisticcompany.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logisticcompany.team4.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
