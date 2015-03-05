package com.hotelOnline.service;

import java.util.List;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Employee;
import com.hotelOnline.domain.Order;
import com.hotelOnline.repository.OrderRepository;


@Controller
@RequestMapping("/orderService")
public class OrderService {
	private static final Category logger = Category
			.getInstance(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public @ResponseBody
	Order createOrder(
			@RequestBody Order order) {
				return orderRepository.saveAndFlush(order);
		
	}
	
	@RequestMapping(value = "/assignOrder", method = RequestMethod.POST)
	public @ResponseBody
	Order assignOrder(
			@RequestBody Order order) {
		return orderRepository.saveAndFlush(order);
		
	}
	
	@RequestMapping(value = "/getOrderStatus", method = RequestMethod.POST)
	public @ResponseBody
	Order getOrderStatus(
			@RequestBody Order order) {
				return orderRepository.findOne(order.getId());
		
	}
	
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
	public @ResponseBody
	Order updateOrderStatus(
			@RequestBody Order order) {
				return orderRepository.saveAndFlush(order);
		
	}
	
	@RequestMapping(value = "/getOrderAssignedToCustomer", method = RequestMethod.POST)
	public @ResponseBody
	List<Order> getOrderAssignedToCustomer(
			@RequestBody Customer customer) {
				return orderRepository.findAllByCustomer(customer);
		
	}
	
	@RequestMapping(value = "/getOrderAssignedToEmployee", method = RequestMethod.POST)
	public @ResponseBody
	List<Order> getOrderAssignedToEmployee(
			@RequestBody Employee employee) {
				return orderRepository.findAllByEmployee(employee);
		
	}
	
	
	
}
