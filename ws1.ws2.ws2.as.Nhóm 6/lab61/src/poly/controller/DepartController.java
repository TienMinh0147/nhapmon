package poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.net.httpserver.HttpServer;

import poly.entity.Depart;
import poly.entity.User;
@Transactional
@Controller
@RequestMapping("/depart/")
public class DepartController {
	
	Depart depart1;
	String message ="";
	@Autowired
	SessionFactory factory;
	@RequestMapping("list")
	public String list(ModelMap model) {
	
		Session ss = factory.getCurrentSession();
		String hql ="FROM Depart";
		Query query = ss.createQuery(hql);
		List<Depart> list = query.list();
		model.addAttribute("depart",list);
		return "depart/list";
	}
	@RequestMapping("depart/update/{id}")
	public String detail(ModelMap model, @PathVariable("id") String id) { 
		Session ss = factory.getCurrentSession();
		
		depart1 = (Depart) ss.get(Depart.class,id);
		model.addAttribute("user", depart1);
		
		return "redirect:/depart/update.htm";
		
		//if (.replaceAll(" ","").matches("[a-zA-Z]+"))
	}
	@RequestMapping("depart/delete/{id}")
	public String delete(ModelMap model, @PathVariable("id") String id) { 
try {
	Session ss = factory.getCurrentSession();
	 Depart d = (Depart) ss.get(Depart.class,id);
	ss.delete(d);
} catch (Exception e) {
	
}
		
		
		
		return "redirect:/depart/list.htm";
		
		
	}
	@RequestMapping("insert")
	public String insert(ModelMap model) {
		model.addAttribute("depart", new Depart());
		return"depart/insert";
	}
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("depart") Depart depart) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			
			
			ss.save(depart);
			t.commit();
			message ="thêm thành công Username " ;
			
		} catch (Exception e) {
			t.rollback();
			message ="Them moi that bai " ;
			
			
		}finally {
			ss.close();
		}
		//return"redirect:/user/insert.htm";
		return "redirect:/depart/list.htm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("update")
	public String update(ModelMap model,HttpServletRequest request) {
		request.setAttribute("name", depart1.getId());
		request.setAttribute("password", depart1.getName());
		
		model.addAttribute("depart", new Depart());
		
		return"depart/update";
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("depart") Depart depart) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
	
		try {
			ss.update(depart);
			t.commit();
			message ="sửa thành công Username " ;
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Them moi that bai !"+e.toString());			
		}finally {
			ss.close();
		}
		return"redirect:/depart/list.htm";
	}
	
	
}



