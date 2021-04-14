package edu.poly.lab.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.lab.dtos.RecordDto;
import edu.poly.lab.dtos.StaffDto;
import edu.poly.lab.models.Depart;
import edu.poly.lab.models.Staff;
import edu.poly.lab.models.records;
import edu.poly.lab.services.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private RecordService recordService;
	
	
	@GetMapping("/add")
	public String recordShow(ModelMap model) {
		RecordDto recordDto  = new RecordDto();
		model.addAttribute("RecordDto", recordDto);
//		model.addAttribute("staffs", recordService.findAll());
		return "viewrecord/Record";
	}
	
	@ModelAttribute(name = "staffs")
	public List<Staff> getListStaff() {
		return recordService.findAllRecords();
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated RecordDto recordDto, BindingResult result) {
//		if (result.hasErrors()) {
//			model.addAttribute("message", "Please Input All Required Fields!!");
//			model.addAttribute("staffDto", staffDto);
//			return "viewstaff/Staff";
//		}
		if (recordDto.getId() != null && recordDto.getId() >0) {
			model.addAttribute("message", "The Staff Updated!!");
		} else {
			model.addAttribute("message", "new Staff inserted!!");
			
		}
		
		records records = new records();
		records.setType(recordDto.getType());
		records.setReason(recordDto.getReason());
		records.setDate(recordDto.getDate());
		
		Staff staff = new Staff();
		staff.setId(recordDto.getStaffId());
		
		records.setStaffs(staff);
		
		recordService.save(records);
		model.addAttribute("RecordDto", recordDto);
		
		return "viewrecord/Record";
	}
	
	
}
