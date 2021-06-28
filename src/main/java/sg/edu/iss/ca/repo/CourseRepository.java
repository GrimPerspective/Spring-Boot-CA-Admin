package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    public Course findCourseById(Integer courseId);
    
    

}
