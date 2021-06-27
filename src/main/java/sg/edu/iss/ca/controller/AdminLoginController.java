package sg.edu.iss.ca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Admin;
import sg.edu.iss.ca.service.AdminInterface;

@Controller
public class AdminLoginController {
	
	@Autowired
	AdminInterface aService;
	
	@RequestMapping(path = "/login")
	public String login(Model model) {
		Admin a = new Admin();
		model.addAttribute("admin", a);
		return "admin/login";
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("admin") Admin admin, Model model, HttpSession session) {
		if(aService.authenticate(admin)) 
		{
			Admin a = aService.findByName(admin.getUserName());
			session.setAttribute("asession", a);
			return "admin/adminHome";
		}
		else
			return "admin/login";
	}
	
}