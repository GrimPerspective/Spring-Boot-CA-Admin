package sg.edu.iss.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.service.CourseInterface;

@Controller
@RequestMapping("/manageCourses")
public class AdminManageCoursesController {

    @Autowired
    CourseInterface courseService;

    @RequestMapping("/list")
    public String list(Model model){
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
        courseService.deleteCourse(courseId);
        return "redirect:/manageCourses/list";
    }


}
