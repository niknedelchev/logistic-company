package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.repository.CompanyRepository;

@Controller
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping(path = "/companies")
	public String showCompaniesPage(Model model) {
		
		List<Company> companies = companyRepository.findAll();
		model.addAttribute("companies", companies);
		
		return "companies";
	}
	
	@GetMapping(path = "/companies/add")
	public String showAddCompanyPage(Model model) {
		model.addAttribute("company", new Company());
		
		return "companies-add";
	}
	
	@PostMapping(path = "/companies/add")
	public String addCompany(@ModelAttribute Company company) {
		companyRepository.save(company);
		
		return "redirect:/companies";
	}
	
	@GetMapping("/companies/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
	    
	    model.addAttribute("company", company);
	    return "companies-edit";
	}
	
	@PostMapping("/companies/edit/{id}")
	public String updateCompany(@ModelAttribute Company company) throws Exception {

		Company companyInDB = companyRepository.findById(company.getId()).orElse(null);
		if (companyInDB != null) {
			companyInDB.setName(company.getName());
			companyInDB.setAddress(company.getAddress());
			companyRepository.save(companyInDB);
		} else {
			throw new Exception("Company not found");
		}
		
	    return "redirect:/companies";
	}
	    
	@GetMapping("/companies/delete/{id}")
	public String deleteCompany(@PathVariable("id") int id, Model model) {
	    Company company = companyRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
	    companyRepository.delete(company);
	    return "redirect:/companies";
	}
	
}
