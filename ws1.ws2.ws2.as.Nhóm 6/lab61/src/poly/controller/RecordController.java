package poly.controller;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.entity.Depart;
import poly.entity.Record;
import poly.entity.Staff;
import poly.entity.User;

@Transactional
@Controller
@RequestMapping("/record/")
public class RecordController {
	Record record1;
@Autowired
SessionFactory factory;
@RequestMapping(value="insert", method=RequestMethod.GET)
public String insert(ModelMap model) {
	
model.addAttribute("record1", new Record());
return "record/insert";
}
@RequestMapping(value="insert", method=RequestMethod.POST)
public String insert(ModelMap model,
@ModelAttribute("record1") Record record) {
	
Session session = factory.openSession();

Transaction t = session.beginTransaction();
try {
	long millis=System.currentTimeMillis();  
	java.sql.Date date=new java.sql.Date(millis);  
record.setDate(date);
session.save(record);
t.commit();
model.addAttribute("message", "Thêm mới thành công !");
}
catch (Exception e) {
	t.rollback();
	model.addAttribute("message", "Thêm mới thất bại !");
	return "record/insert";
	}
	finally {
	session.close();
	}
	return "redirect:/record/list.htm";
	}

@ModelAttribute("staffs")
public List<Staff> getStaffs() {
Session session = factory.getCurrentSession();
String hql = "FROM Staff";
Query query = session.createQuery(hql);
List<Staff> list = query.list();
return list;
}

	
@RequestMapping("list")
public String list(ModelMap model) {
	Session ss = factory.getCurrentSession();
	String hql ="FROM Record ";
	Query query = ss.createQuery(hql);
	List<Record> list = query.list();
	model.addAttribute("record",list);
	return "record/list";
}
@RequestMapping("record/update/{username}")
public String detail(ModelMap model, @PathVariable("username") String username) { 
	Session ss = factory.getCurrentSession();
	record1 = (Record) ss.get(Record.class,username);
	model.addAttribute("record", record1);
	
	return "redirect:/record/update.htm";
	
	//if (.replaceAll(" ","").matches("[a-zA-Z]+"))
}
@RequestMapping("update")
public String update(ModelMap model,HttpServletRequest request) {
//	request.setAttribute("name", depart1.getId());
//	request.setAttribute("password", depart1.getName());
	request.setAttribute("name",record1.staff.name);
	request.setAttribute("date",record1.getDate());
	request.setAttribute("type",record1.type);
	request.setAttribute("reason",record1.reason);
	model.addAttribute("record", new Record());
	
	return"record/update";
}
@RequestMapping(value = "update", method = RequestMethod.POST)
public String update(ModelMap model,
@ModelAttribute("record") Record record2) {
Session session = factory.openSession();
Transaction t = session.beginTransaction();
try {
	long millis=System.currentTimeMillis();  
	java.sql.Date date=new java.sql.Date(millis);  
	record2.setId(record1.getId());
	record2.setStaff(record1.getStaff());
	
record2.setDate(record1.getDate());
session.update(record2);
t.commit();
model.addAttribute("message", "Thêm mới thành công !");
}
catch (Exception e) {
	t.rollback();
	model.addAttribute("message", "Thêm mới thất bại !"+e.toString());
	}
	finally {
	session.close();
	}
	return "redirect:/record/list.htm";
	}



@RequestMapping("record/delete/{id}")
public String delete(ModelMap model, @PathVariable("id") String id) { 
	
	
	
	Session ss = factory.getCurrentSession();
	Record record = (Record) ss.get(Record.class,id);
	ss.delete(record);
	return "redirect:/record/list.htm";
	
	
}
@RequestMapping("report")
public String report(ModelMap model) {
Session session = factory.getCurrentSession();
String hql = "SELECT r.staff.depart.name, "+
" SUM(case when r.type=1 then 1 else 0 end), "+
" SUM(case when r.type=0 then 1 else 0 end)"+
" FROM Record r "+
" GROUP BY r.staff.depart.name ";
Query query = session.createQuery(hql);
List<Object[]> list = query.list();
model.addAttribute("arrays", list);
return "record/report";
}

}

