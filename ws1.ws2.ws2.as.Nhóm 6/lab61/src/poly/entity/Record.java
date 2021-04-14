package poly.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="Records")
public class Record {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	public	String id;
	@Column(name = "Type")
	public	Boolean type;
	@Column(name = "Reason")
	public	String reason;
	
	@DateTimeFormat
	public	Date date;
	@ManyToOne
	@JoinColumn(name="StaffId")
	public	Staff staff;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Record(String id, Boolean type, String reason, Date date, Staff staff) {
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.date = date;
		this.staff = staff;
	}
	public Record() {
		
	}
	
	
	
	
	
	

}
