package com.hotelOnline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Employee;
import com.hotelOnline.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	public List<Order> findAllByCustomer(Customer customer);
	public List<Order> findAllByEmployee(Employee employee);

}
