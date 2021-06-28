package sg.edu.iss.ca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.repo.LecturerRepository;

@Service
public class AdminLecturerService implements AdminLecturerInterface {
	
	@Autowired
	LecturerRepository lRepo;

	@Override
	public List<Lecturer> findALlLecturers() {
		return lRepo.findAll();
	}

	@Override
	public void createLecturerRecord(Lecturer lecturer) {
		// TODO Auto-generated method stub
		lRepo.save(lecturer);
	}

	@Override
	public Lecturer findLecturerById(Integer lecturerId) {
		// TODO Auto-generated method stub
		return lRepo.findLecturerById(lecturerId);
	}

	@Override
	public void deleteLecturer(Integer lecturerId) {
		// TODO Auto-generated method stub
		Lecturer lecturerToDelete = lRepo.findLecturerById(lecturerId);
		lRepo.delete(lecturerToDelete);
		return;
	}
	
	
	
}
