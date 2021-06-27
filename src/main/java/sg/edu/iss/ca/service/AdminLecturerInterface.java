package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Lecturer;

public interface AdminLecturerInterface {
	
	 public List<Lecturer> findALlLecturers();
	 
	 public void createLecturerRecord(Lecturer lecturer);
	 
	 public Lecturer findLecturerById(Integer lecturerId);
	 
	 public void deleteLecturer(Integer lecturerId);

}
