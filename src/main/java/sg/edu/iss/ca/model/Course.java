package sg.edu.iss.ca.model;

import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Name;
	private String Description;
	private int size;
	private int enrollment;
	private int credits;
	
	@ManyToMany(mappedBy = "learnings")
	private Collection<Student> students;
	@ManyToMany(mappedBy = "teachings")
	private Collection<Lecturer> lecturers ;
	
	@OneToMany(mappedBy = "course")
	private Collection<Grade> grades;

	public Course(String name, String description, int size, int enrollment, int credits) {
		Name = name;
		Description = description;
		this.size = size;
		this.enrollment = enrollment;
		this.credits = credits;
	}
	
	public Course(String name, String description, int size, int enrollment, int credits, Collection<Student> students,
			Collection<Lecturer> lecturers, Collection<Grade> grades) {
		Name = name;
		Description = description;
		this.size = size;
		this.enrollment = enrollment;
		this.credits = credits;
		this.students = students;
		this.lecturers = lecturers;
		this.grades = grades;
	}








}
