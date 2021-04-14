package edu.poly.fpt.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import edu.poly.fpt.models.Depart;

public class StaffDto implements Serializable {

	private Long id;

	@NotNull(message = "vui lòng nhập tên")
	@NotEmpty(message = "Name is empty")
	@Length(min = 5, max = 50, message = "Name is out of range")
	private String name;
	
	private boolean gender;
 
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	 
	@NotNull(message ="pleas choose file image")
	private MultipartFile image;
	
	@NotNull(message ="vui lòng nhập email")
	private String email;
	
	@NotNull(message ="vui lòng đăng số phone")
	private String phone;
	
	@NotNull(message ="vui lòng đăng salary")
	private Float salary;
	
	@NotNull(message ="vui lòng đăng department")
	private Integer departId;
	
	public StaffDto() {
		super();
	}
	public StaffDto(Long id,
			@NotNull(message = "vui lòng nhập tên") @NotEmpty(message = "Name is empty") @Length(min = 5, max = 50, message = "Name is out of range") String name,
			boolean gender, @NotNull Date birthday, @NotNull(message = "pleas choose file image") MultipartFile image,
			@NotNull(message = "vui lòng nhập email") String email,
			@NotNull(message = "vui lòng đăng số phone") String phone,
			@NotNull(message = "vui lòng đăng salary") Float salary,
			@NotNull(message = "vui lòng đăng department") Integer departId) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.image = image;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.departId = departId;
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


	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
