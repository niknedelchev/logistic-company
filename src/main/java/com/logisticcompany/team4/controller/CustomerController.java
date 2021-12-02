package com.logisticcompany.team4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.services.CustomerServices;

import java.util.List;


@Controller
public class CustomerController {

	@Autowired
	private CustomerServices customerServices;

	@GetMapping(path = "/customers")
	public String showCustomersPage(Model model) {
		List<Customer> customers = customerServices.findAll(model);
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
		customerServices.addCustomer(customer);
		return "redirect:/customers";
	}

	@GetMapping("/customers/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Customer customer = customerServices.findCustomerById(id);
		model.addAttribute("customer", customer);
		return "customers-edit";
	}

	@PostMapping("/customers/edit/{id}")
	public String updateCustomer(@ModelAttribute Customer customer) throws Exception {
		customerServices.updateCustomer(customer);
		return "redirect:/customers";
	}

	@GetMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
		customerServices.deleteCustomer(id);
		return "redirect:/customers";
	}

}
