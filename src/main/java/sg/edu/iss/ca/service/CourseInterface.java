package sg.edu.iss.ca.service;

import sg.edu.iss.ca.model.Course;

import java.util.List;

public interface CourseInterface {

    public List<Course> findAllCourses();

    public void createCourseRecord(Course course);

    public Course findCourseById(Integer courseId);

    public void deleteCourse(Integer courseId);


}
