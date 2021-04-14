package edu.poly.fpt.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "departs")
public class Depart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
	@Column(length = 100)
private String name;
	@OneToMany(mappedBy = "depart", cascade=CascadeType.ALL)
	private Set<Staff> staff;

	
	public Depart() {
		super();
	}
	public Depart(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Set<Staff> getStaff() {
		return staff;
	}
	public void setStaff(Set<Staff> staff) {
		this.staff = staff;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
