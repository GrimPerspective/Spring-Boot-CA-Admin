package sg.edu.iss.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.service.AdminLecturerInterface;

@Controller
@RequestMapping("/manageLecturers")
public class AdminManageLecturersController {
	
	@Autowired
	AdminLecturerInterface alService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("lecturers", alService.findALlLecturers());
		return "admin/adminLecturer";
	}
	
	@PostMapping("/addLecturer")
	public String addUser(Model model) 
	{
		Lecturer newLecturer = new Lecturer();
		model.addAttribute("newLecturer", newLecturer);
		return "admin/adminLecturerAdd";
	}
	
	@PostMapping("/saveLecturer")
	public String saveUser(@ModelAttribute("newLecturer") Lecturer newLecturer)
	{
		alService.createLecturerRecord(newLecturer);
		return "forward:/manageLecturers/list";
	}
	
	@RequestMapping("/updateLecturer")
	public String editForm(@RequestParam("lecturerId") int lecturerId, Model model) {
        Lecturer updateLecturer = alService.findLecturerById(lecturerId);
        model.addAttribute("newLecturer", updateLecturer);

		return "admin/adminLecturerAdd";
	}
	
    @GetMapping("/deleteLecturer")
    public String delete(@RequestParam("lecturerId") int lecturerId){
    	alService.deleteLecturer(lecturerId);
        return "redirect:/manageLecturers/list";
    }
}
