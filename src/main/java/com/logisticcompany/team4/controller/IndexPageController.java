package com.logisticcompany.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.services.CompanyServices;

@Controller
public class IndexPageController {

	@GetMapping(path = "/")
	public String showCompaniesPage() {
		return "index";
	}
}