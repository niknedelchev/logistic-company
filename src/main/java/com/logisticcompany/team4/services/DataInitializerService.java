package com.logisticcompany.team4.services;

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
		userRepository.save(new User(0, "Andrew", "Andersen", "a.a@gmail.com", "aa_user", "password", true, role1, null, null));
		userRepository.save(new User(0, "Branden", "Burton", "b.b@gmail.com", "bb_user", "password", true, role2, null, null));
		userRepository.save(new User(0, "Carmen", "Carlson", "c.c@gmail.com", "cc_user", "password", true, role1, null, null));
		userRepository.save(new User(0, "Donald", "Dune", "d.d@gmail.com", "dd_user", "password", true, role2, null, null));
		userRepository.save(new User(0, "Elinor", "Elensen", "e.e@gmail.com", "ee_user", "password", true, role1, null, null));
		userRepository.save(new User(0, "Frank", "Flyman", "f.f@gmail.com", "ff_user", "password", true, role2, null, null));
		userRepository.save(new User(0, "George", "Gibson", "g.g@gmail.com", "gg_user", "password", true, role1, null, null));
		userRepository.save(new User(0, "Harold", "Hudson", "h.h@gmail.com", "hh_user", "password", true, role2, null, null));

		// Creating companies
		companyRepository.save(new Company(0, "Company A", "Street A", null));
		companyRepository.save(new Company(0, "Company B", "Street B", null));

		Company co1 = companyRepository.findById(1).get();
		Company co2 = companyRepository.findById(2).get();

		// Creating offices
		officeRepository.save(new Office(0, "Street C", 500, co1, null, null));
		officeRepository.save(new Office(0, "Street D", 600, co2, null, null));

		Office off1 = officeRepository.getById(1);
		Office off2 = officeRepository.getById(2);

		// Creating employees
		employeeRepository.save(new Employee(0, 1500, EmployeeType.OFFICE_CLERK, off1, userRepository.findById(1).get()));
		employeeRepository.save(new Employee(0, 1700, EmployeeType.OFFICE_CLERK, off2, userRepository.findById(3).get()));
		employeeRepository.save(new Employee(0, 1400, EmployeeType.OFFICE_CLERK, off1, userRepository.findById(5).get()));
		employeeRepository.save(new Employee(0, 1800, EmployeeType.OFFICE_CLERK, off2, userRepository.findById(7).get()));

		//Creating customers
		customerRepository.save(new Customer(0, userRepository.findById(2).get(), "Place A"));
		customerRepository.save(new Customer(0, userRepository.findById(4).get(), "Place B"));
		customerRepository.save(new Customer(0, userRepository.findById(6).get(), "Place C"));
		customerRepository.save(new Customer(0, userRepository.findById(8).get(), "Place D"));

		
		Customer cust1 = customerRepository.findById(1).get();
		Customer cust2 = customerRepository.findById(2).get();
		Customer cust3 = customerRepository.findById(3).get();
		Customer cust4 = customerRepository.findById(4).get();
		
		//Creating Parcels
		parcelRepository.save(new Parcel(0,cust1,cust2,2.50,"Place B", 12.95, 3.25, off1, ParcelStatus.IN_TRANSIT));
		parcelRepository.save(new Parcel(0,cust3,cust4,1.45,"Place D", 10.99, 1.99, off2, ParcelStatus.IN_TRANSIT));
		parcelRepository.save(new Parcel(0,cust4,cust1,3.40,"Place A", 18.45, 5.45, off1, ParcelStatus.DELIVERED));
		parcelRepository.save(new Parcel(0,cust2,cust3,0.99,"Place C", 8.99, 1.45, off2, ParcelStatus.DELIVERED));


		System.out.println("Data initialization ends.");

	}

}
