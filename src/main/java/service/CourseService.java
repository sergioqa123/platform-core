package service;

import domain.Course;
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

    public ArrayList<Course> listCourses(){
        return courseRepository.getCourses();
    }
}
