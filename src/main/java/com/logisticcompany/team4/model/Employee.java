package com.logisticcompany.team4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import constant.EmployeeType;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String firstName;
	String lastName;
	EmployeeType employeeType;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	User user;

	@ManyToOne
	@JoinColumn(name = "office_id", nullable = false)
	Office office;
	
	
	public Employee() {
	}

	public Employee(int id, String firstName, String lastName, EmployeeType employeeType, Office office, User user) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeType = employeeType;
		this.office = office;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
