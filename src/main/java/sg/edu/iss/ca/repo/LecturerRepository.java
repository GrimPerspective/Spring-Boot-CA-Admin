package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

	public Lecturer findLecturerById(Integer lecturerId);
		
}
