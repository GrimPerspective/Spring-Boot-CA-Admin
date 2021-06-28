package sg.edu.iss.ca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.repo.CourseRepository;


import java.util.List;

@Service
public class CourseService implements CourseInterface{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void createCourseRecord(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Integer courseId) {
        return courseRepository.findCourseById(courseId);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        Course courseToDelete = courseRepository.findCourseById(courseId);
        courseRepository.delete(courseToDelete);
    }

}
