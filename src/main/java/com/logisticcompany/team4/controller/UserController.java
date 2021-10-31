package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.UserRepository;


@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path = "/users")
	public String showUsersPage(Model model) {
		
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		
		return "users";
	}
	
	@GetMapping(path = "/users/add")
	public String showAddUserPage(Model model) {
		model.addAttribute("user", new User());
		
		return "users-add";
	}
	
	@PostMapping(path = "/users/add")
	public String addUser(@ModelAttribute User user) {
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("user", user);
	    return "users-edit";
	}
	
	@PostMapping("/users/edit/{id}")
	public String updateUser(@ModelAttribute User user) throws Exception {

		User userInDB = userRepository.findById(user.getId()).orElse(null);
		if (userInDB != null) {
			userInDB.setUsername(user.getUsername());
			userInDB.setEmail(user.getEmail());
			userInDB.setPassword(user.getPassword());
			userInDB.setFirstName(user.getFirstName());
			userInDB.setLastName(user.getLastName());
			userInDB.setActive(user.isActive());
			userInDB.setRole(user.getRole());
			userRepository.save(userInDB);
		} else {
			throw new Exception("User not found");
		}
		
	    return "redirect:/users";
	}
	    
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    User user = userRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    userRepository.delete(user);
	    return "redirect:/users";
	}
	
}
