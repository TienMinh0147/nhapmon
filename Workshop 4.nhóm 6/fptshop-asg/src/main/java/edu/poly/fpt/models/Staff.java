package edu.poly.fpt.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "staffs")
public class Staff {
  @Id
  @GeneratedValue(strategy =GenerationType.IDENTITY )
  private Long id;
  
  @Column(length =50)
  private String name;
  
  @Column(name ="gender")
  private boolean gender;
  
  
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date birthday;
  
  @Column(length =100)
  private String photo;
  
  @Column(length =100)
  private String email;
  
  @Column(length =10)
  private String phone;
 
  @Column(length =100)
  private Float salary;
  
  @ManyToOne
  @JoinColumn(name = "departId")
  private Depart depart;
  
public Staff() {
	super();
}  

public Staff(Long id, String name,boolean gender,
		String photo, Date birthday,String phone,
		 String email, Depart depart,Float salary) {
	super();
	this.id = id;
	this.name = name;
	this.gender= gender;
	this.birthday = birthday;
	this.photo = photo;
	this.email= email;
	this.phone =phone;
	this.salary= salary;
	this.depart = depart;
	
	
	
	
}

public boolean isGender() {
	return gender;
}

public void setGender(boolean gender) {
	this.gender = gender;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public Float getSalary() {
	return salary;
}

public void setSalary(Float salary) {
	this.salary = salary;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPhoto() {
	return photo;
}

public void setPhoto(String photo) {
	this.photo = photo;
}

public Date getBirthday() {
	return birthday;
}

public void setBirthday(Date birthday) {
	this.birthday = birthday;
}

public Depart getDepart() {
	return depart;
}

public void setDepart(Depart depart) {
	this.depart = depart;
}	
  
  

  
}
