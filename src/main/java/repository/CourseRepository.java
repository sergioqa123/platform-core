package repository;

import domain.Course;

import java.util.ArrayList;

public class CourseRepository {

    private final ArrayList<Course> courseList = new ArrayList<>();
    private int courseId = 1;

    public void saveCourse(Course course){
        course.setId(courseId);
        courseId++;
        courseList.add(course);
    }

    public void removeCourse(Course course){
        courseList.remove(course);
    }

    public ArrayList<Course> getCourses(){
        return courseList;
    }
}
