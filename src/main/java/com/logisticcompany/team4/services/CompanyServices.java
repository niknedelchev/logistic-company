package com.logisticcompany.team4.services;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional

public class CompanyServices {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> findAll() {
		List<Company> companies = companyRepository.findAll();
		return companies;
	}

	public void addCompany(@ModelAttribute Company company) {
		companyRepository.save(company);
	}

	public Company getCompanyById(@PathVariable("id") int id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
		return company;
	}

	public void updateCompany(@ModelAttribute Company company) throws Exception {
		Company companyInDB = companyRepository.findById(company.getId()).orElse(null);
		if (companyInDB != null) {
			companyInDB.setName(company.getName());
			companyInDB.setAddress(company.getAddress());
			companyRepository.save(companyInDB);
		} else {
			throw new Exception("Company not found");
		}
	}

	public void deleteCompany(@PathVariable("id") int id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
		companyRepository.delete(company);
	}
}
