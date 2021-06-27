package sg.edu.iss.ca;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//import sg.edu.iss.ca.model.Course;
//import sg.edu.iss.ca.model.Grade;
//import sg.edu.iss.ca.model.Lecturer;
//import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.repo.CourseRepository;
import sg.edu.iss.ca.repo.GradeRepository;
import sg.edu.iss.ca.repo.LecturerRepository;
//import sg.edu.iss.ca.repo.StudentRepository;



@SpringBootApplication
public class CaApplication {

	@Autowired
	GradeRepository grepo;
	
	@Autowired
	CourseRepository crepo;
	
	@Autowired
	LecturerRepository lrepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CaApplication.class, args);
	}
	@Bean
	CommandLineRunner runner() {
		return args -> { 			
//			Course c1 = new Course("C#", "Programming language", 50, 0, 8);
//			Course c2 = new Course("C", "Programming language", 50, 0, 10);
//			Course c3 = new Course("Java", "Programming language", 50, 0, 16);
//			crepo.saveAndFlush(c1);
//			crepo.saveAndFlush(c2);
//			crepo.saveAndFlush(c3);
//			
//			
//			List<Course> courses1= new ArrayList<Course>();
//			courses1.add(c1);
//			courses1.add(c2);
//			List<Course> courses2= new ArrayList<Course>();
//			courses2.add(c2);
//			courses2.add(c3);
//			
//			Lecturer l1 = new Lecturer("AAA", "12345", "Xs", "H", 0, null, courses1);
//			Lecturer l2 = new Lecturer("BBB", "12345", "Sq", "L", 0, null, courses2);			
//			lrepo.saveAndFlush(l1);
//			lrepo.saveAndFlush(l2);
//			
//			Student s = new Student("Akon", "12345","Xuesheng", "He", 0, null);
//			s.setLearnings(courses1);
//			srepo.saveAndFlush(s);
//
//			
//			Grade g1 = new Grade(c1.getId(),s.getId() , 3);
//			Grade g2 = new Grade(c2.getId(),s.getId(),5);
//			grepo.saveAndFlush(g1);
//			grepo.saveAndFlush(g2);
//			
//			List<Grade> grades= new ArrayList<Grade>();
//			grades.add(g1);
//			grades.add(g2);
//			
//			s.setGrades(grades);
//			
//			srepo.saveAndFlush(s);
			
		};
	}
}
