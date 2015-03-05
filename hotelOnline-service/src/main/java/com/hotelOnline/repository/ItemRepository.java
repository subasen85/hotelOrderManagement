package com.hotelOnline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelOnline.domain.Employee;
import com.hotelOnline.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
