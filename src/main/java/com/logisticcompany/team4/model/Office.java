package com.logisticcompany.team4.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "offices")
public class Office {

	@Id
	@Column(name = "office_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String address;
	
	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	Company company;

	@OneToMany(mappedBy =  "office")
	List<Employee> employees;

	public Office() {
	}

	public Office(int id, String address, Company company, List<Employee> employees) {
		this.id = id;
		this.address = address;
		this.company = company;
		this.employees = employees;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
