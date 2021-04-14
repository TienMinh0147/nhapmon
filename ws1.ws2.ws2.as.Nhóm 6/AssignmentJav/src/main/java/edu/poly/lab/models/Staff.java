package edu.poly.lab.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "staffs")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String name;
	@Column(length = 100)
	private String photo;
	@Column(length = 50)
	private String gender;
	@Column(length = 100)
	private String email;
	private Float salary;
	@Column(length = 100)
	private String notes;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	@Column(length = 10)
	private String phone;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "departId")
	private Depart depart;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="staffs")
	private Set<records> records;
	
	
	public Staff() {
		super();
	}
	public Staff(Long id, String name, String photo, String gender, String email, Float salary, String notes,
			Date birthday, String phone, Depart depart, Set<edu.poly.lab.models.records> records) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.gender = gender;
		this.email = email;
		this.salary = salary;
		this.notes = notes;
		this.birthday = birthday;
		this.phone = phone;
		this.depart = depart;
		this.records = records;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public Set<records> getRecords() {
		return records;
	}
	public void setRecords(Set<records> records) {
		this.records = records;
	}
	
	
	
}
