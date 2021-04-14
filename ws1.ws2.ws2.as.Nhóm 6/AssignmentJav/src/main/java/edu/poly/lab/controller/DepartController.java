package edu.poly.lab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.lab.models.Depart;

@Controller
@RequestMapping("/depart")
public class DepartController {
	@Autowired
	private edu.poly.lab.services.DepartService departService;

	@GetMapping("/add")
	public String add(ModelMap model, @RequestParam(defaultValue = "") String name) {
		model.addAttribute("depart", new Depart());

		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("departs", list);

//		list = departService.findByNameLikeOrderByName(name);
//		model.addAttribute("departs", list);

		return list2(model);
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, Depart depart , @RequestParam(defaultValue = "") String name) {
		String message = "New Depart Inserted";

		if (depart.getId() != null && depart.getId() > 0) {
			message = "The Depart Updated";
		}
		
		departService.save(depart);
		model.addAttribute(depart);
		model.addAttribute("message", message);
		model.addAttribute("depart", new Depart());

		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("departs", list);

//		list = departService.findByNameLikeOrderByName(name);
//		model.addAttribute("departs", list);

		return "viewdepart/Depart";
	}

	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Integer id) {
		departService.deleteById(id);
		return listdelete(model);
	}

	@RequestMapping("/listdelete")
	public String listdelete(ModelMap model) {
//		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("depart", new Depart());
//		model.addAttribute("departs", list);
		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("departs", list);
		return "viewdepart/DeleteDepart";
	}

	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Integer id) {
		Optional<Depart> optDepart = departService.findById(id);
		if (optDepart.isPresent()) {
			model.addAttribute("depart", optDepart.get());
			List<Depart> list = (List<Depart>) departService.findAll();
			model.addAttribute("departs", list);
		} else {
			return list2(model);
		}
		return "viewdepart/EditDepart";
	}

	@RequestMapping("/list")
	public String list(ModelMap model ,  @RequestParam(defaultValue = "") String name) {
		model.addAttribute("depart", new Depart());

		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("departs", list);

		list = departService.findByNameLikeOrderByName(name);
		model.addAttribute("departs", list);

		return "viewdepart/Depart";
	}
	@RequestMapping("/list2")
	public String list2(ModelMap model) {
		List<Depart> list = (List<Depart>) departService.findAll();
		model.addAttribute("departs", list);
		return "viewdepart/Depart";
	}

	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {

		List<Depart> list = departService.findByNameLikeOrderByName(name);
		model.addAttribute("departs", list);
		
		model.addAttribute("depart", new Depart());
		return "viewdepart/FindDepart";
	}

}
