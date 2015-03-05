package com.hotelOnline.service;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.repository.CustomerRepository;

@Controller
@RequestMapping("/cutomerService")
public class CustomerService {
	private static final Category logger = Category
			.getInstance(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public @ResponseBody
	Customer createCustomer(
			@RequestBody Customer customer) {
		
				return customerRepository.saveAndFlush(customer);
		
	}
	
	@RequestMapping(value = "/authenticateCustomer", method = RequestMethod.POST)
	public @ResponseBody
	Customer authenticateCustomer(
			@RequestBody Customer customer) {
		
				return customerRepository.findOneByIdAndPwd(customer.getId(), customer.getPwd());
		
	}
	
	
}
