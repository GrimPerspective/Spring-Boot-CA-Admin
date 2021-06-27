package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findAllByOrderByFirstNameAsc();

    public List<Student> findByUserNameContainsAllIgnoreCase(String user);

}
