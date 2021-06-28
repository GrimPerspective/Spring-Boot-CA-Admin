package sg.edu.iss.ca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.repo.StudentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements StudentInterface{

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Student findById(int id) {
        Optional<Student> result = studentRepository.findById(id);
        Student theStudent = null;

        if (result.isPresent()){
            theStudent = result.get();
        }
        else {
            throw new RuntimeException("Did not find Student " + id);
        }
        return  theStudent;
    }

    @Override
    public void save(Student theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> searchBy(String studentUser) {
        List<Student> results = null;

        if (studentUser != null && (studentUser.trim().length() > 0)) {
            results = studentRepository.findByUserNameContainsAllIgnoreCase(studentUser);
        }
        else {
            results = findAll();
        }

        return results;
    }
    
    
}
