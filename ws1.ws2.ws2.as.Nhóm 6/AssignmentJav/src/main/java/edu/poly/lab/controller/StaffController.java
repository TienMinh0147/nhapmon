package edu.poly.lab.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.poly.lab.dtos.StaffDto;
import edu.poly.lab.models.Depart;
import edu.poly.lab.models.Staff;
import edu.poly.lab.services.StaffService;

@Controller
@RequestMapping(value = "/staff", method = RequestMethod.GET)
public class StaffController {
	@Autowired
	private StaffService staffService;

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("staffs", staffService.findAll());
		return "viewstaff/Staff";
	}
	@GetMapping("/list1")
	public String list1(ModelMap model) {
		model.addAttribute("staffs", staffService.findAll());
		return "viewstaff/Staff";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		StaffDto staff = new StaffDto();
		model.addAttribute("staffDto", staff);
		model.addAttribute("staffs", staffService.findAll());
		return "viewstaff/Staff";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated StaffDto staffDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Please Input All Required Fields!!");
			model.addAttribute("staffDto", staffDto);
			return "viewstaff/Staff";
		}
		if (staffDto.getId() != null && staffDto.getId() >0) {
			model.addAttribute("message", "The Staff Updated!!");
		} else {
			model.addAttribute("message", "new Staff inserted!!");
			
		}

		Path path = Paths.get("imagess/");
		try (InputStream inputStream = staffDto.getImage().getInputStream()) {
			Files.copy(inputStream, path.resolve(staffDto.getImage().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			String filename = staffDto.getImage().getOriginalFilename();

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Error:" + e.getMessage());
		}

		Staff staff = new Staff();
		staff.setBirthday(staffDto.getBirthday());
		staff.setName(staffDto.getName());
		staff.setPhoto(staffDto.getImage().getOriginalFilename());
		staff.setEmail(staffDto.getEmail());
		staff.setGender(staffDto.getGender());
		staff.setNotes(staffDto.getNotes());
		staff.setPhone(staffDto.getPhone());
		staff.setSalary(staffDto.getSalary());
		Depart depart = new Depart();
		depart.setId(staffDto.getDepartId());
		staff.setDepart(depart);
		staffService.save(staff);
		model.addAttribute("staffDto", staffDto);
		return list(model);
//		return "viewstaff/Staff";
	}

	@ModelAttribute(name = "departs")
	public List<Depart> getListDepart() {
		return staffService.findAllDeparts();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {
		staffService.deleteById(id);
		return list(model);
	}
	
	@GetMapping("/edit/{id}")
	public String edit(ModelMap model, @PathVariable(name = "id") Long id , @Validated StaffDto staffDto) {
		Optional<Staff> optDepart = staffService.findById(id);
		if (optDepart.isPresent()) {
			model.addAttribute("staff", optDepart.get());
			model.addAttribute("staffDto", staffDto);
		} else {
			return list(model);
		}
		return "viewstaff/StaffList";
	}
}
