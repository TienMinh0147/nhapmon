package edu.poly.fpt.models;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Record")
public class records {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 50)
	private String type;
	@Column(length = 200)
	private String reason;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="staffId")
	private Staff staffs;
	
	
	public Staff getStaffs() {
		return staffs;
	}

	public void setStaffs(Staff staffs) {
		this.staffs = staffs;
	}

	public records() {
		super();
	}

	public records(Integer id, String type, String reason, Date date) {
		super();
		this.id = id;
		this.type = type;
		this.reason = reason;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
}
