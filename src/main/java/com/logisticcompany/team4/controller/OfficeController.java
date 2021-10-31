package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.repository.OfficeRepository;


@Controller
public class OfficeController {

	@Autowired
	private OfficeRepository officeRepository;
	
	@GetMapping(path = "/offices")
	public String showOfficesPage(Model model) {
		
		List<Office> offices = officeRepository.findAll();
		model.addAttribute("offices", offices);
		
		return "offices";
	}
	
	@GetMapping(path = "/offices/add")
	public String showAddOfficePage(Model model) {
		model.addAttribute("office", new Office());
		
		return "offices-add";
	}
	
	@PostMapping(path = "/offices/add")
	public String addOffice(@ModelAttribute Office office) {
		officeRepository.save(office);
		
		return "redirect:/offices";
	}
	
	@GetMapping("/offices/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Office office = officeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));
	    
	    model.addAttribute("office", office);
	    return "offices-edit";
	}
	
	@PostMapping("/offices/edit/{id}")
	public String updateOffice(@ModelAttribute Office office) throws Exception {

		Office officeInDB = officeRepository.findById(office.getId()).orElse(null);
		if (officeInDB != null) {
			officeInDB.setAddress(office.getAddress());
			officeInDB.setCompany(office.getCompany());
			officeRepository.save(officeInDB);
		} else {
			throw new Exception("Office not found");
		}
		
	    return "redirect:/offices";
	}
	    
	@GetMapping("/offices/delete/{id}")
	public String deleteOffice(@PathVariable("id") int id, Model model) {
	    Office office = officeRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid office Id:" + id));
	    officeRepository.delete(office);
	    return "redirect:/offices";
	}
	
}
