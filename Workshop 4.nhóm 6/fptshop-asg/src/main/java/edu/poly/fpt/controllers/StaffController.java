package edu.poly.fpt.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.fpt.dtos.StaffDto;
import edu.poly.fpt.models.Depart;
import edu.poly.fpt.models.Staff;
import edu.poly.fpt.services.StaffService;

@Controller

 @RequestMapping("/staffs")

public class StaffController {
	
 @Autowired
 private StaffService staffService;
 
 @RequestMapping("/list")
 public String list(ModelMap model) {
	 
	 model.addAttribute("staffs",staffService.findAll());
	 
	 return "staffs/list";
	 
 }
 @GetMapping("/add")
 public String add(ModelMap model) {
	 StaffDto staff = new StaffDto();
	 
	 model.addAttribute("staffDto", staff);
	 
	 return "staffs/addOrEdit";
	 
 }
 @RequestMapping("/delete/{id}")
 public String delete(ModelMap model,@PathVariable(name="id") Long id) {
       staffService.deleteById(id);
 model.addAttribute("staffs", staffService.findAll());
 return "staffs/list";
 }
 @RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<Staff> list = staffService.findByNameLikeOrderByName(name);
		model.addAttribute("staffs", list);
		return "staffs/list";
	}


@RequestMapping("/edit/{id}")
public String edit(ModelMap model, @PathVariable(name = "id") Long id) {
	Optional<Staff> staf = staffService.findById(id);
	StaffDto dto = null;
	if (staf.isPresent()) {
		Staff st = staf.get();
		
		File file = new File("images/" +st.getPhoto());
		FileInputStream input;
			try {
				input = new FileInputStream(file);

				dto = new StaffDto(st.getId(),st.getName(), st.isGender(),st.getBirthday(),null,st.getEmail(),
						st.getPhone(),st.getSalary(),st.getDepart().getId());
				System.out.println("-----------------------------------" + dto.getId());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			model.addAttribute("staffDto", dto);
		} else {
			model.addAttribute("staffDto", new StaffDto());
		}
		model.addAttribute("ACTION", "/staffs/saveOrUpdate");
		return "staffs/addOrEdit";
	}
 
 

 @PostMapping("/saveOrUpdate")
 public String saveOrUpdate(ModelMap model,@Validated StaffDto staffDto ,
        BindingResult result){
        	
	 if(result.hasErrors()) {
		 model.addAttribute("message", "Please input all required fields!!");
		 model.addAttribute("staffDto", staffDto);
		 
		 return "staffs/addOrEdit";
	 } 
	 if(staffDto.getId()!= null && staffDto.getId()>0) {
		 model.addAttribute("message", " new staff update!");
	 }else {
		 model.addAttribute("message", " new staff inserted!");
	 }
	 
	 Path path = Paths.get("images/");
	 
	 try{
		 InputStream inputStream = staffDto.getImage().getInputStream();
	    Files.copy(inputStream, path.resolve(staffDto.getImage().getOriginalFilename()),
	    		StandardCopyOption.REPLACE_EXISTING);
	    String filename =staffDto.getImage().getOriginalFilename();
	 }catch (Exception e) {
		 e.printStackTrace();
		 model.addAttribute("message", "Error: "+ e.getMessage());
	 }

		Staff staff = new Staff();
		staff.setId(staffDto.getId());
		staff.setName(staffDto.getName());
		staff.setGender(staffDto.isGender());
		
		staff.setBirthday(staffDto.getBirthday());
		
		staff.setPhoto(staffDto.getImage().getOriginalFilename());
		staff.setEmail(staffDto.getEmail());
		staff.setPhone(staffDto.getPhone());
		staff.setSalary(staffDto.getSalary());
		
		Depart depart = new Depart();
		depart.setId(staffDto.getDepartId());
		staff.setDepart(depart);
		staffService.save(staff);
		
		model.addAttribute("staffDto", staffDto);
	 	 System.out.println("================="+ staffDto.getId());
	 
	 return "staffs/addOrEdit";
	 
 }
 @ModelAttribute(name = "departs")
 public List<Depart> getDeparts(){
   return staffService.findAllDeparts();
 }
	 
}
