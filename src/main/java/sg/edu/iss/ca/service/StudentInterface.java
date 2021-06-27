package sg.edu.iss.ca.service;

import sg.edu.iss.ca.model.Student;

import java.util.List;

public interface StudentInterface {

    public List<Student> findAll();

    public Student findById(int id);

    public void save(Student theStudent);

    public void deleteById(int id);

    public List<Student> searchBy(String studentUser);
}
