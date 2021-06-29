package sg.edu.iss.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.service.AdminLecturerInterface;
import sg.edu.iss.ca.service.CourseInterface;
import sg.edu.iss.ca.service.StudentInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manageCourses")
public class AdminManageCoursesController {

    @Autowired
    CourseInterface courseService;

    @Autowired
    AdminLecturerInterface adminLecturerInterface;
    
    @Autowired
    StudentInterface studentService;

    @RequestMapping("/list")
    public String list(Model model, HttpSession session)
    {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
        model.addAttribute("courses", courseService.findAllCourses());
        return "admin/adminCourse";
    }

    @PostMapping("/addCourse")
    public String addUser(Model model)
    {
        Course newCourse = new Course();
        model.addAttribute("newCourse", newCourse);
        return "admin/adminCourseAdd";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("newCourse") Course newCourse)
    {
        courseService.createCourseRecord(newCourse);
        return "forward:/manageCourses/list";
    }

    @RequestMapping("/updateCourse")
    public String editForm(@RequestParam("courseId") int courseId, Model model) {
        Course theCourse = courseService.findCourseById(courseId);
        model.addAttribute("newCourse", theCourse);

        return "admin/adminCourseAdd";
    }

    @GetMapping("/deleteCourse")
    public String delete(@RequestParam("courseId") int courseId){
    	Course courseToDelete = courseService.findCourseById(courseId);
    	
    	// If add cascade, can remove this section of code
    	// Need to change model class if adding cascade
    	List<Lecturer> lecturers = (List<Lecturer>) courseToDelete.getLecturers();
    	for (Lecturer lecturer :lecturers)
    	{
    		List<Course> courses = (List<Course>) lecturer.getTeachings();
    		courses.remove(courseToDelete);
    		lecturer.setTeachings(courses);
    		adminLecturerInterface.save(lecturer);
    	}
    	
    	// If add cascade, can remove this section of code
    	// Need to change model class if adding cascade
    	List<Student> students = (List<Student>) courseToDelete.getStudents();
    	for (Student student : students)
    	{
    		List<Course> learnings = (List<Course>) student.getLearnings();
    		learnings.remove(courseToDelete);
    		student.setLearnings(learnings);
    		studentService.save(student);
    	}
    	
        courseService.deleteCourse(courseId);
        return "redirect:/manageCourses/list";
    }

    @RequestMapping("/assigned")
    public String viewAssigned(@RequestParam("courseId") int courseId, Model model) {
        Course course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
        return "admin/adminCourseAssignedLecturer";
    }

    @RequestMapping("/assignLecturer")
    public String enroll(@RequestParam("courseId") int courseId, Model model) {
        // find all lecturers
        List<Lecturer> allLecturers = adminLecturerInterface.findALlLecturers();

        // find lecturers assigned to course
        Course course = courseService.findCourseById(courseId);
        List<Lecturer> assignedLecturers = (List<Lecturer>) course.getLecturers();

        // find lecturers not assigned to course
        List<Lecturer> unassignedLecturers = new ArrayList<Lecturer>();

        for (Lecturer lecturer : allLecturers)
        {
            if (!assignedLecturers.contains(lecturer))
            {
                unassignedLecturers.add(lecturer);
            }
        }
        model.addAttribute("unassignedLecturers", unassignedLecturers);
        model.addAttribute("course", course);
        return "admin/adminCourseAssignLecturer";
    }

    @RequestMapping("/saveAssignLecturer")
    public String saveAssignment(@RequestParam("lecturerId") int lecturerId, @RequestParam("courseId") int courseId, Model model)
    {
        Lecturer lecturerToAssign = adminLecturerInterface.findLecturerById(lecturerId);
        Course courseToAssign = courseService.findCourseById(courseId);

        List<Course> lecturerCourses = (List<Course>) lecturerToAssign.getTeachings();
        lecturerCourses.add(courseToAssign);

        lecturerToAssign.setTeachings(lecturerCourses);

        adminLecturerInterface.save(lecturerToAssign);

        model.addAttribute("course", courseToAssign);
        return "forward:/manageCourses/assigned";
    }

    @RequestMapping("/removeAssignedLecturer")
    public String removeAssignedLecturer(@RequestParam("lecturerId") int lecturerId, @RequestParam("courseId") int courseId, Model model)
    {
        Lecturer lecturerToRemove = adminLecturerInterface.findLecturerById(lecturerId);
        Course courseToRemoveFrom = courseService.findCourseById(courseId);

        List<Course> lecturerCourses = (List<Course>) lecturerToRemove.getTeachings();
        lecturerCourses.remove(courseToRemoveFrom);
        lecturerToRemove.setTeachings(lecturerCourses);

        adminLecturerInterface.save(lecturerToRemove);

        model.addAttribute("course", courseToRemoveFrom);
        return "forward:/manageCourses/assigned";
    }

}
