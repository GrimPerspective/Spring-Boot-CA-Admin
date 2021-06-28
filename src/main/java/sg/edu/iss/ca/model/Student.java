package sg.edu.iss.ca.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	private int userType;
	@DateTimeFormat (pattern= "yyyy-MM-dd" )
    private Date enrollmentDate;
	
	@ManyToMany
	@JoinTable(name="student_course",
	joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Collection<Course> learnings;	
	
	@OneToMany(mappedBy = "student")
	private Collection<Grade> grades;
	

	

	public Student(String userName, String userPassword, String firstName, String lastName, int userType,
			Date enrollmentDate) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.enrollmentDate = enrollmentDate;
	}



	public Student(String userName, String userPassword, String firstName, String lastName, int userType,
			@FutureOrPresent Date enrollmentDate, Collection<Course> learnings, Collection<Grade> grades) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.enrollmentDate = enrollmentDate;
		this.learnings = learnings;
		this.grades = grades;
	}






	
	
}