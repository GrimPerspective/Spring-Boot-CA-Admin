package sg.edu.iss.ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	private int courseID;
//	private int studentID;
	private int grade;
	
	@ManyToOne
	private Course course;
	
	@ManyToOne
	private Student student;	
	
	public Grade(int courseID, int studentID, int grade) {
//		this.courseID = courseID;
//		this.studentID = studentID;
		this.grade = grade;
	}



	


	
	
}
