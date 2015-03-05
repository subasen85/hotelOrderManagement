package com.hotelOnline.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@ManyToOne
private Customer customer;

@ManyToOne
private Employee employee;

@ManyToMany
@JoinTable(name = "order_item", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
private List<Item> listItem;

private String status;

@Transient
private double cost=0;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Customer getUser() {
	return customer;
}

public void setUser(Customer user) {
	this.customer = user;
}

public List<Item> getListItem() {
	return listItem;
}

public void setListItem(List<Item> listItem) {
	this.listItem = listItem;
}

public double getCost() {
	for(Item item:this.getListItem()){
		cost=cost+item.getPrice();
	}
	return cost;
}

public void setCost(double cost) {
	this.cost = cost;
}

public Employee getEmployee() {
	return employee;
}

public void setEmployee(Employee employee) {
	this.employee = employee;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

}
