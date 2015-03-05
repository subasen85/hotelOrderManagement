package com.hotelOnline.service;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Employee;
import com.hotelOnline.repository.CustomerRepository;
import com.hotelOnline.repository.EmployeeRepository;


@Controller
@RequestMapping("/employeeService")
public class EmployeeService {
	private static final Category logger = Category
			.getInstance(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/authenticateEmployee", method = RequestMethod.POST)
	public @ResponseBody
	Employee authenticateEmployee(
			@RequestBody Employee employee) {
		return employeeRepository.findOneByIdAndPwd(employee.getId(), employee.getPwd());
		
	}
}
