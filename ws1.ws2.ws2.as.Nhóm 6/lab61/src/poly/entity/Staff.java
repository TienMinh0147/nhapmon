package poly.entity;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Staffs")
public class Staff {
	@Id
	public String id;
	public String name;
	public	Boolean gender;
	@DateTimeFormat(pattern = "yyyy/dd/MM")
	public	 String birthday;
	public	 String photo;
	public	 String email;
	public	 String phone;
	public	 Double salary;
	public	 String notes;
	@ManyToOne
	@JoinColumn(name = "DepartId")
	private	Depart depart;
	
	@OneToMany(mappedBy="staff", fetch=FetchType.EAGER)
	private Collection<Record>records;
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
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public Collection<Record> getRecords() {
		return records;
	}
	public void setRecords(Collection<Record> records) {
		this.records = records;
	}
	public Staff(String id, String name, Boolean gender, String birthday, String photo, String email, String phone,
			Double salary, String notes, Depart depart, Collection<Record> records) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.notes = notes;
		this.depart = depart;
		this.records = records;
	}
	public Staff() {
		
	}
	
	
}
