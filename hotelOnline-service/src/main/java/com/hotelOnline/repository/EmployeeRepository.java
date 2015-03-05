package com.hotelOnline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelOnline.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	public Employee findOneByIdAndPwd(String Id,String pwd);
}
