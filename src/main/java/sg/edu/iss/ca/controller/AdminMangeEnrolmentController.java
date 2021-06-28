package sg.edu.iss.ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.service.CourseInterface;
import sg.edu.iss.ca.service.StudentInterface;

@Controller
@RequestMapping("/manageEnrolment")
public class AdminMangeEnrolmentController {
	
	@Autowired
    CourseInterface courseService;
	
	@Autowired
	StudentInterface studentService;
	
	@RequestMapping("/list")
	public String list(Model model) {
        model.addAttribute("courses", courseService.findAllCourses());
		return "admin/adminEnrolment";
	}
	
	@RequestMapping("/enrolled")
	public String viewEnrolment(@RequestParam("courseId") int courseId, Model model) {
		Course course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
		return "admin/enrolledStudents";
	}
	
	@RequestMapping("/enrollStudent")
	public String enroll(Model model) {
		List<Student> allStudents = studentService.findAll();
		
//		List<Student> enrolledStudents = ;
//		
//		List<Student> unenrolledStudents = new List<Student>();
		
		model.addAttribute("allStudents", allStudents);
		return "admin/adminEnrollStudent";
	}
	
	@RequestMapping("/saveEnrollStudent")
	public String saveEnrolment(Model model) {
		return "forward:/manageEnrolment/list";
	}
	
	
}
