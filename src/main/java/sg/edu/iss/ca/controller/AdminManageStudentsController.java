package sg.edu.iss.ca.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/manageStudents")
public class AdminManageStudentsController {

    private StudentService studentService;

    public AdminManageStudentsController(StudentService theStudentService){
        studentService = theStudentService;
    }

    @GetMapping("/adminStudent")
    public String listStudent(Model theModel){
        List<Student> theStudents = studentService.findAll();

        theModel.addAttribute("students", theStudents);

        return "/admin/adminStudent";
    }

    @GetMapping("/adminAddStudent")
    public String adminFormAddStudent(Model theModel){
        Student theStudent = new Student();

        theModel.addAttribute("student", theStudent);

        return "/admin/adminStudentAdd";
    }

    @GetMapping("/adminUpdateStudent")
    public String adminFormUpdateStudent(@RequestParam("studentId") int id, Model theModel){
        Student theStudent = studentService.findById(id);

        theModel.addAttribute("student", theStudent);

        return "/admin/adminStudentAdd";
    }


    @PostMapping("/save")
    public String adminSaveStudent(@ModelAttribute("student") Student theStudent){
        studentService.save(theStudent);
        //prevent multiple submission redirect user to adminstudent again
        return "redirect:/manageStudents/adminStudent";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("studentid") int id){
        studentService.deleteById(id);
        return "redirect:/manageStudents/adminStudent";
    }

    @GetMapping("/search")
    public String delete(@RequestParam("studentUser") String theUser,
                         Model theModel) {

        List<Student> theStudents = studentService.searchBy(theUser);

        theModel.addAttribute("employees", theStudents);

        return "/admin/adminStudent";

    }

}
