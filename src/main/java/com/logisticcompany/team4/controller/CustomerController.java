package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.repository.CustomerRepository;



@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping(path = "/customers")
	public String showCustomersPage(Model model) {
		
		List<Customer> customers = customerRepository.findAll();
		model.addAttribute("customers", customers);
		
		return "customers";
	}
	
	@GetMapping(path = "/customers/add")
	public String showAddCustomerPage(Model model) {
		model.addAttribute("customer", new Customer());
		
		return "customers-add";
	}
	
	@PostMapping(path = "/customers/add")
	public String addCustomer(@ModelAttribute Customer customer) {
		customerRepository.save(customer);
		
		return "redirect:/customers";
	}
	
	@GetMapping("/customers/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
	    
	    model.addAttribute("customer", customer);
	    return "customers-edit";
	}
	
	@PostMapping("/customers/edit/{id}")
	public String updateCustomer(@ModelAttribute Customer customer) throws Exception {

		Customer customerInDB = customerRepository.findById(customer.getId()).orElse(null);
		if (customerInDB != null) {
			customerInDB.setFirstName(customer.getFirstName());
			customerInDB.setLastName(customer.getLastName());
			customerInDB.setAddress(customer.getAddress());
			customerInDB.setUser(customer.getUser());
			customerRepository.save(customerInDB);
		} else {
			throw new Exception("Customer not found");
		}
		
	    return "redirect:/customers";
	}
	    
	@GetMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable("id") int id, Model model) {
	    Customer customer = customerRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
	    customerRepository.delete(customer);
	    return "redirect:/customers";
	}
	
}
