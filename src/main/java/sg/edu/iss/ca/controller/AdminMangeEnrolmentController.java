package sg.edu.iss.ca.controller;

import java.util.ArrayList;
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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manageEnrolment")
public class AdminMangeEnrolmentController {
	
	@Autowired
    CourseInterface courseService;
	
	@Autowired
	StudentInterface studentService;
	
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
        model.addAttribute("courses", courseService.findAllCourses());
		return "admin/adminEnrolment";
	}
	
	@RequestMapping("/enrolled")
	public String viewEnrolment(@RequestParam("courseId") int courseId, Model model, HttpSession session) {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		Course course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
		return "admin/adminListEnrolledStudents";
	}
	
	@RequestMapping("/enrollStudent")
	public String enroll(@RequestParam("courseId") int courseId, Model model, HttpSession session) {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		// find all students
		List<Student> allStudents = studentService.findAll();
		
		// find students enrolled in course
		Course course = courseService.findCourseById(courseId);
		List<Student> enrolledStudents = (List<Student>) course.getStudents();
		
		// find students not enrolled in course
		List<Student> unenrolledStudents = new ArrayList<Student>();
		
		for (Student student : allStudents)
		{
			if (!enrolledStudents.contains(student))
			{
				unenrolledStudents.add(student);
			}
		}
		
		model.addAttribute("unenrolledStudents", unenrolledStudents);
		model.addAttribute("course", course);
		return "admin/adminEnrollStudent";
	}
	
	@RequestMapping("/saveEnrollStudent")
	public String saveEnrolment(@RequestParam("studentId") int studentId, @RequestParam("courseId") int courseId, Model model, HttpSession session)
	{
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		Student studentToEnroll = studentService.findById(studentId);
		Course courseToEnroll = courseService.findCourseById(courseId);
		
		List<Course> studentCourses = (List<Course>) studentToEnroll.getLearnings();
		studentCourses.add(courseToEnroll);
		
		studentToEnroll.setLearnings(studentCourses);
		
		studentService.save(studentToEnroll);
		
        model.addAttribute("course", courseToEnroll);
		return "forward:/manageEnrolment/enrolled";
	}
	
	@RequestMapping("/removeStudentEnrollment")
	public String removeStudentEnrollment(@RequestParam("studentId") int studentId, @RequestParam("courseId") int courseId, Model model, HttpSession session)
	{
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		Student studentToRemove = studentService.findById(studentId);
		Course courseToRemoveFrom = courseService.findCourseById(courseId);
		
		List<Course> studentCourses = (List<Course>) studentToRemove.getLearnings();
		studentCourses.remove(courseToRemoveFrom);		
		studentToRemove.setLearnings(studentCourses);
		
		studentService.save(studentToRemove);
		
        model.addAttribute("course", courseToRemoveFrom);
		return "forward:/manageEnrolment/enrolled";
	}
	
}
