package repository;

import domain.Course;

import java.util.ArrayList;

public class CourseRepository {

    private ArrayList<Course> courseList = new ArrayList<>();
    private int courseId = 1;

    public void saveCourse(Course course){
        course.setId(courseId);
        this.courseId++;
        this.courseList.add(course);
    }

    public ArrayList<Course> getCourses(){
        return courseList;
    }
}
