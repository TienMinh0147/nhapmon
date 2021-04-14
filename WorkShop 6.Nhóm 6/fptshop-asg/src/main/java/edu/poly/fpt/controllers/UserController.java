package edu.poly.fpt.controllers;



import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.fpt.models.User;
import edu.poly.fpt.services.UserService;





@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	User _userBean;
	
	@GetMapping("/")
	public String addOrEdit(ModelMap model) {
		User u = new User();
		//u.setUsername("tungpnpd02992");
		model.addAttribute("USER", u);
		model.addAttribute("ACTION", "saveOrUpdate");
		return "register-user";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
	//	UserDAO dao = new UserDAO();
	//	dao.save(user);
	//	System.out.println("size:"+dao.getAll().size());
		userService.save(user);
		return "register-user";
	}
	
	@RequestMapping("list")
	public String list(ModelMap model, HttpSession session) {
	//	UserDAO dao = new UserDAO();
	//	model.addAttribute("USERS", dao.getAll());
		if (session.getAttribute("USERNAME") !=null) {
			model.addAttribute("USERS", userService.findAll());
			return "view-user";
		}else {
			return "login";
		}
	}
	@RequestMapping("/edit/{username}")
	public String edit(ModelMap model,
			@PathVariable(name="username") String username) {
	//	UserDAO dao = new UserDAO();
	//	User u = dao.findByUserName(username);
		Optional<User> u = userService.findById(username);
		if (u.isPresent()) {
			model.addAttribute("USER", u.get());
		} else {
			model.addAttribute("USER", new User());
		}
		model.addAttribute("ACTION", "/saveOrUpdate");
		return "register-user";
	}
	@RequestMapping("/delete/{username}")
	public String delete(ModelMap model,
			@PathVariable(name="username") String username) {
	//	UserDAO dao = new UserDAO();
	//	dao.delete(username);
		userService.deleteById(username);
		model.addAttribute("USERS", userService.findAll());
		return "view-user";
	}
	//----------------------------------------------------------------
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/checklogin")
	public String checkLogin(ModelMap model, @RequestParam("username")String username, 
			@RequestParam("password")String password,
			HttpSession session) {
//		if (_userBean.getUsername().equals(username)&& _userBean.getPassword().equals(password)) {
//			System.out.println("Login Sucess");
//			return "view-user";
//		}else {
//			System.out.println("Login Fail");
//		}
		if(userService.checkLogin(username, password)) {
			System.out.println("Login Sucess !");
			session.setAttribute("USERNAME", username);
			model.addAttribute("USERS", userService.findAll());
			return "view-user";
		}else {
			System.out.println("Login Fail");
			model.addAttribute("ERROR", "Username or password not exist !");
		}
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("USERNAME");
		return "login";
	}
	@GetMapping("/register-user")
	public String logout() {
		return "register-user";
}
}
