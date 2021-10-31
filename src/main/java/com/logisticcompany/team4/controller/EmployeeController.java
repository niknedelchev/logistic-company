package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.repository.EmployeeRepository;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(path = "/employees")
	public String showEmployeesPage(Model model) {
		
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		
		return "employees";
	}
	
	@GetMapping(path = "/employees/add")
	public String showAddEmployeePage(Model model) {
		model.addAttribute("employee", new Employee());
		
		return "employees-add";
	}
	
	@PostMapping(path = "/employees/add")
	public String addEmployee(@ModelAttribute Employee employee) {
		employeeRepository.save(employee);
		
		return "redirect:/employees";
	}
	
	@GetMapping("/employees/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	    
	    model.addAttribute("employee", employee);
	    return "employees-edit";
	}
	
	@PostMapping("/employees/edit/{id}")
	public String updateEmployee(@ModelAttribute Employee employee) throws Exception {

		Employee employeeInDB = employeeRepository.findById(employee.getId()).orElse(null);
		if (employeeInDB != null) {
			employeeInDB.setFirstName(employee.getFirstName());
			employeeInDB.setLastName(employee.getLastName());
			employeeInDB.setUser(employee.getUser());
			employeeRepository.save(employeeInDB);
		} else {
			throw new Exception("Employee not found");
		}
		
	    return "redirect:/employees";
	}
	    
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id, Model model) {
	    Employee employee = employeeRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	    employeeRepository.delete(employee);
	    return "redirect:/employees";
	}
	
}
