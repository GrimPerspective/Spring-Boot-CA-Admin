package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
