package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.Role;
import com.logisticcompany.team4.repository.RoleRepository;


@Controller
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping(path = "/roles")
	public String showRolesPage(Model model) {
		
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("roles", roles);
		
		return "roles";
	}
	
	@GetMapping(path = "/roles/add")
	public String showAddRolePage(Model model) {
		model.addAttribute("role", new Role());
		
		return "roles-add";
	}
	
	@PostMapping(path = "/roles/add")
	public String addRole(@ModelAttribute Role role) {
		roleRepository.save(role);
		
		return "redirect:/roles";
	}
	
	@GetMapping("/roles/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
	    
	    model.addAttribute("role", role);
	    return "roles-edit";
	}
	
	@PostMapping("/roles/edit/{id}")
	public String updateRole(@ModelAttribute Role role) throws Exception {

		Role roleInDB = roleRepository.findById(role.getId()).orElse(null);
		if (roleInDB != null) {
			roleInDB.setName(role.getName());
			roleInDB.setRoleType(role.getRoleType());
			roleRepository.save(roleInDB);
		} else {
			throw new Exception("Role not found");
		}
		
	    return "redirect:/roles";
	}
	    
	@GetMapping("/roles/delete/{id}")
	public String deleteRole(@PathVariable("id") int id, Model model) {
	    Role role = roleRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
	    roleRepository.delete(role);
	    return "redirect:/roles";
	}
	
}
