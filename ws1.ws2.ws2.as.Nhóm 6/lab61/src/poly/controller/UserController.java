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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.net.httpserver.HttpServer;

import poly.entity.User;

@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {
	char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à',
			'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ',
			'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ',
			'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể',
			'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
			'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử',
			'Ữ', 'ữ', 'Ự', 'ự', };
	User user;

	@Autowired
	SessionFactory factory;

	@RequestMapping("list")
	public String list(ModelMap model) {

		Session ss = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = ss.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("user", list);
		return "user/list";
	}
	@RequestMapping("list1")
	public String list(ModelMap model,HttpServletRequest request) {

		Session ss = factory.getCurrentSession();
		String hql = "FROM User u where u.username LIKE '%" +request.getParameter("tim")+"%'";
		Query query = ss.createQuery(hql);
	
		List<User> list = query.list();
		model.addAttribute("user", list);
		return "redirect:/user/list";
	}
	@RequestMapping("user/update/{username}")
	public String detail(ModelMap model, @PathVariable("username") String username) {
		Session ss = factory.getCurrentSession();
		user = (User) ss.get(User.class, username);
		model.addAttribute("user", user);

		return "redirect:/user/update.htm";

		// if (.replaceAll(" ","").matches("[a-zA-Z]+"))
	}

	@RequestMapping("user/delete/{username}")
	public String delete(ModelMap model, @PathVariable("username") String username) {

		Session ss = factory.getCurrentSession();
		user = (User) ss.get(User.class, username);
		ss.delete(user);
		return "redirect:/user/list.htm";

	}

	@RequestMapping("insert")
	public String insert(ModelMap model) {
		model.addAttribute("users", new User());
		return "user/insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("users") User users) {
		Session ss = factory.openSession();
		
		Transaction t = ss.beginTransaction();
		int a = 0;
		try {
			
				ss.save(user);
				t.commit();
				model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng !");
			
			

		} catch (Exception e) {
			t.rollback();
			 model.addAttribute(
					  "message","Username đã trùng và nhỏ hơn và bằng 16 ký tự không có khoảng trống và không để trống"
					  );
			 a=1;
			

		} finally {
			ss.close();
		}
		if(a==1) {
			return "user/insert";
		}
		return "redirect:/user/list.htm";

	}

	@RequestMapping("update")
	public String update(ModelMap model, HttpServletRequest request) {
		request.setAttribute("name", user.getUsername());
		request.setAttribute("password", user.getPassword());
		request.setAttribute("fullname", user.getFullname());
		model.addAttribute("users", new User());

		return "user/update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("users") User user) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();

		try {
			ss.update(user);
			t.commit();

		} catch (Exception e) {
			t.rollback();

		} finally {
			ss.close();
		}
		return "redirect:/user/list.htm";
	}

	@RequestMapping("login")
	public String login() {
		return "user/login";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession request) {
		Session ss = factory.openSession();
		int a = 0;

		try {
			User user1 = (User) ss.get(User.class, username);
			if (!user1.getPassword().replaceAll(" ", "").equals(password)) {
				model.addAttribute("message", "tên hoặc mât khẩu sai");
				a = 1;
			} else {
				request.setAttribute("user1", user1);
				
				model.addAttribute("message", "đăng nhâp thành công");
			}

		} catch (Exception e) {

			model.addAttribute("message", "tên hoặc mât khẩu sai");
			a = 1;

		} finally {
			ss.close();
		}
		if (a == 1) {
			return "user/login";
		}

		return "redirect:/user/index.htm";

	}

	
	@RequestMapping("thongke")
	public String report(ModelMap model) {
	Session session = factory.getCurrentSession();
	String hql = "SELECT r.staff.name	, "+
	" SUM(case when r.type=1 then 1 else 0 end), "+
	" SUM(case when r.type=0 then 1 else 0 end)"+
	" FROM Record r "+
	" GROUP BY r.staff.name ";
	Query query = session.createQuery(hql);
	List<Object[]> list = query.list();
	model.addAttribute("arrays", list);
	
	String hql1 = "SELECT r.staff.depart.name, "+
			" SUM(case when r.type=1 then 1 else 0 end), "+
			" SUM(case when r.type=0 then 1 else 0 end)"+
			" FROM Record r "+
			" GROUP BY r.staff.depart.name ";
			Query query1 = session.createQuery(hql1);
			List<Object[]> list1 = query1.list();
			model.addAttribute("arrays1", list1);
	return "user/thongke";
	}
	@RequestMapping("index")
	public String index(ModelMap model) {
	Session session = factory.getCurrentSession();
	String hql = "SELECT r.staff.name	,r.staff.photo,r.staff.depart.name, "+
	" SUM(case when r.type=1 then 1 else 0 end)- "+
	" SUM(case when r.type=0 then 1 else 0 end) as t"+
	" FROM Record r "+
	" GROUP BY r.staff.name	,r.staff.photo,r.staff.depart.name  order by t desc   ";
	Query query = session.createQuery(hql).setMaxResults(10);
	List<Object[]> list = query.list();
	model.addAttribute("arrays", list);
	
	
	return "user/index";
	}
}
