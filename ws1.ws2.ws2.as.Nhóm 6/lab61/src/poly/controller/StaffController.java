package poly.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
@RequestMapping("/staff/")
public class StaffController {
	Staff staff1;
	@Autowired
	SessionFactory factory;
	@RequestMapping("list")
	public String list(ModelMap model) {
		Session ss = factory.getCurrentSession();
		String hql ="FROM Staff";
		Query query = ss.createQuery(hql);
		List<Staff> list = query.list();
		model.addAttribute("staff",list);
		return "staff/list";
	}
	@RequestMapping("report")
	public String report(ModelMap model) {
	Session session = factory.getCurrentSession();
	String hql = "SELECT r.staff.id,SUM(case when r.type =1 then 1 else 0 end),SUM(case when r.type=0 then 1 else 0 end)FORM Record rGROUP BY r.staff.id";
	 
	Query query = session.createQuery(hql);
	List<Object[]> list = query.list();
	model.addAttribute("arrays", list);
	return "staff/report";
	}
	
	@RequestMapping("list1")
	public String list(ModelMap model,HttpServletRequest request) {

		Session ss = factory.getCurrentSession();
		String hql = "FROM Staff u where u.name LIKE '%" +request.getParameter("tim")+"%'";
		Query query = ss.createQuery(hql);
	
		List<Staff> list = query.list();
		model.addAttribute("staff", list);
		return "staff/list";
	}
	
	
	@RequestMapping("insert")
	public String insert(ModelMap model) {
	model.addAttribute("staff", new Staff());
	return "staff/insert";
	}
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(ModelMap model,
	@ModelAttribute("staff") Staff staff,HttpServletRequest request
	
			) {
	Session session = factory.openSession();
	Transaction t = session.beginTransaction();
	try {

	session.save(staff);
	t.commit();
	model.addAttribute("message", "Them moi thanh cong !");
	}
	catch (Exception e) {
		t.rollback();
		model.addAttribute("message", "Them moi that bai !"+e.toString());
		}
		finally {
		session.close();
		}
		return "staff/insert";
		}
	
	
	
	
	@ModelAttribute("departs")
	public List<Depart> getDeparts() {
	Session session = factory.getCurrentSession();
	String hql = "FROM Depart";
	Query query = session.createQuery(hql);
	List<Depart> list = query.list();
	return list;
	}

	
	@RequestMapping("staff/delete/{id}")
	public String delete(ModelMap model, @PathVariable("id") String id) { 
		
		
		try {
			Session ss = factory.getCurrentSession();
			Staff record = (Staff) ss.get(Staff.class,id);
			ss.delete(record);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/staff/list.htm";
		
		
	}
	@RequestMapping("staff/update/{id}")
	public String detail(ModelMap model, @PathVariable("id") String username) { 
		Session ss = factory.getCurrentSession();
		staff1 = (Staff) ss.get(Staff.class,username);
		model.addAttribute("staff1", staff1);
		
		return "redirect:/staff/update.htm";
		
		//if (.replaceAll(" ","").matches("[a-zA-Z]+"))
	}
	@RequestMapping("update")
	public String update(ModelMap model,HttpServletRequest request) {
		request.setAttribute("id", staff1.getId());
		request.setAttribute("name", staff1.getName());
		request.setAttribute("birthday",staff1.getBirthday());
		request.setAttribute("photo",staff1.getPhoto());
		request.setAttribute("email",staff1.getEmail());
		request.setAttribute("phone",staff1.getPhone());
		request.setAttribute("salary",staff1.getSalary());
		request.setAttribute("notes",staff1.getNotes());
		model.addAttribute("staff", new Staff());
		
		return"staff/update";
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(ModelMap model,
	@ModelAttribute("staff") Staff staff) {
	Session session = factory.openSession();
	Transaction t = session.beginTransaction();
	try {
		
		
	session.update(staff);
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
		return "redirect:/staff/list.htm";
		}

}
