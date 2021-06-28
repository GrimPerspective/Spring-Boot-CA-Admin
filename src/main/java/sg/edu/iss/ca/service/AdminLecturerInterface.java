package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.model.Student;

public interface AdminLecturerInterface {
	
	 public List<Lecturer> findALlLecturers();
	 
	 public void createLecturerRecord(Lecturer lecturer);
	 
	 public Lecturer findLecturerById(Integer lecturerId);
	 
	 public void deleteLecturer(Integer lecturerId);

	 public void save(Lecturer theLecturer);

}
