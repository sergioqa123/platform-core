package repository;

import domain.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    private final List<Course> courseList = new ArrayList<>();
    private int courseId = 1;

    public void saveCourse(Course course){
        course.setId(courseId);
        courseId++;
        courseList.add(course);
    }

    public void removeCourse(Course course){
        courseList.remove(course);
    }

    public List<Course> getCourses(){
        return courseList;
    }
}
