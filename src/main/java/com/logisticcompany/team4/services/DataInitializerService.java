package com.logisticcompany.team4.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.logisticcompany.team4.model.Company;
import com.logisticcompany.team4.model.Customer;
import com.logisticcompany.team4.model.Employee;
import com.logisticcompany.team4.model.Office;
import com.logisticcompany.team4.model.Parcel;
import com.logisticcompany.team4.model.Role;
import com.logisticcompany.team4.model.User;
import com.logisticcompany.team4.repository.CompanyRepository;
import com.logisticcompany.team4.repository.CustomerRepository;
import com.logisticcompany.team4.repository.EmployeeRepository;
import com.logisticcompany.team4.repository.OfficeRepository;
import com.logisticcompany.team4.repository.ParcelRepository;
import com.logisticcompany.team4.repository.RoleRepository;
import com.logisticcompany.team4.repository.UserRepository;

import constant.EmployeeType;
import constant.ParcelStatus;
import constant.RoleType;

@Service
public class DataInitializerService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private ParcelRepository parcelRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@EventListener
	public void eventListener(ApplicationReadyEvent e) {
		System.out.println("Logistic company app starts: Data initialization starts.");

		// Creating roles
		roleRepository.save(new Role(0, "employee", RoleType.EMPLOYEE, null));
		roleRepository.save(new Role(0, "customer", RoleType.EMPLOYEE, null));

		Role role1 = roleRepository.findById(1).get();
		Role role2 = roleRepository.findById(2).get();

		// Creating users
		userRepository.save(new User(0, "Andrew", "Andersen", "a.a@gmail.com", "aa_user", "password", true, role1));
		userRepository.save(new User(0, "Branden", "Burton", "b.b@gmail.com", "bb_user", "password", true, role2));
		userRepository.save(new User(0, "Carmen", "Carlson", "c.c@gmail.com", "cc_user", "password", true, role1));
		userRepository.save(new User(0, "Donald", "Dune", "d.d@gmail.com", "dd_user", "password", true, role2));
		userRepository.save(new User(0, "Elinor", "Elensen", "e.e@gmail.com", "ee_user", "password", true, role1));
		userRepository.save(new User(0, "Frank", "Flyman", "f.f@gmail.com", "ff_user", "password", true, role2));
		userRepository.save(new User(0, "George", "Gibson", "g.g@gmail.com", "gg_user", "password", true, role1));
		userRepository.save(new User(0, "Harold", "Hudson", "h.h@gmail.com", "hh_user", "password", true, role2));

		// Creating companies
		companyRepository.save(new Company(0, "Company A", "Street A", null));
		companyRepository.save(new Company(0, "Company B", "Street B", null));

		Company co1 = companyRepository.getById(1);
		Company co2 = companyRepository.getById(2);

		// Creating offices
		officeRepository.save(new Office(0, "Street C", co1, null));
		officeRepository.save(new Office(0, "Street D", co2, null));

		Office off1 = officeRepository.getById(1);
		Office off2 = officeRepository.getById(2);

		// Creating employees
		employeeRepository.save(new Employee(0, "Andrew", "Andersen", EmployeeType.OFFICE_CLERK, off1, userRepository.getById(1)));
		employeeRepository.save(new Employee(0, "Carmen", "Carlson", EmployeeType.OFFICE_CLERK, off2, userRepository.getById(3)));
		employeeRepository.save(new Employee(0, "Elinor", "Elensen", EmployeeType.OFFICE_CLERK, off1, userRepository.getById(5)));
		employeeRepository.save(new Employee(0, "George", "Gibson", EmployeeType.OFFICE_CLERK, off2, userRepository.getById(7)));

		//Creating customers
		customerRepository.save(new Customer(0,"Branden", "Burton",userRepository.getById(2), "Place A"));
		customerRepository.save(new Customer(0,"Donald", "Dune", userRepository.getById(4), "Place B"));
		customerRepository.save(new Customer(0,"Frank", "Flyman",userRepository.getById(6), "Place C"));
		customerRepository.save(new Customer(0,"Harold", "Hudson",userRepository.getById(8), "Place D"));
		
		Customer cust1 = customerRepository.getById(1);
		Customer cust2 = customerRepository.getById(2);
		Customer cust3 = customerRepository.getById(3);
		Customer cust4 = customerRepository.getById(4);
		
		//Creating Parcels
		parcelRepository.save(new Parcel(0,cust1,cust2,2.50,"Place B", 12.95,ParcelStatus.IN_TRANSIT));
		parcelRepository.save(new Parcel(0,cust3,cust4,1.45,"Place D", 10.99,ParcelStatus.IN_TRANSIT));
		parcelRepository.save(new Parcel(0,cust4,cust1,3.40,"Place A", 18.45,ParcelStatus.DELIVERED));
		parcelRepository.save(new Parcel(0,cust2,cust3,0.99,"Place C", 8.99,ParcelStatus.DELIVERED));
		
		System.out.println("Data initialization ends.");

	}

}
