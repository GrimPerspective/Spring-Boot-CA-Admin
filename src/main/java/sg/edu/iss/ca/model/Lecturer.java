package sg.edu.iss.ca.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Lecturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	private int userType;
	private String titel;
	
	@ManyToMany
	@JoinTable(name="lecturer_course",
	joinColumns = @JoinColumn(name = "lecturer_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Collection<Course> teachings;
	
	public Lecturer(String userName, String userPassword, String firstName, String lastName, int userType, String titel,
			Collection<Course> teachings) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.titel = titel;
		this.teachings = teachings;
	}

	public Lecturer(String userName, String userPassword, String firstName, String lastName, int userType,
			String titel) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.titel = titel;
	}
	
	

}
