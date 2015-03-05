package com.hotelOnline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelOnline.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
public Customer findOneByIdAndPwd(String id,String pwd);
}
