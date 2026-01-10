package service;

import domain.Course;
import domain.Role;
import domain.User;
import repository.CourseRepository;

import java.util.ArrayList;

public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(){
        this.courseRepository = new CourseRepository();
    }

    public void registerCourse(Course course){
        courseRepository.saveCourse(course);
    }

    public boolean assignInstructor(Course course, User instructor){
        if(instructor.getRole() == Role.INSTRUCTOR){
            course.setInstructor(instructor);
            course.setStatus(true); // course is now available
            return true; // successful assignment
        }
        return false;
    }

    public ArrayList<Course> listCourses(){
        return courseRepository.getCourses();
    }
}
