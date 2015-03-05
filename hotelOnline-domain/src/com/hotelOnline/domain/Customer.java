package com.hotelOnline.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
@Id
private String id;
private String name;
private String email;
private String pwd;


@OneToMany(mappedBy="customer")
private List<Order> listOrder;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}

public List<Order> getListOrder() {
	return listOrder;
}
public void setListOrder(List<Order> listOrder) {
	this.listOrder = listOrder;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
