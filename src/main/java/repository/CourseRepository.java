package repository;

import domain.Course;

import java.util.ArrayList;

public class CourseRepository {

    private ArrayList<Course> courseList = new ArrayList<>();

    public void saveCourse(Course course){
        this.courseList.add(course);
    }

    public ArrayList<Course> getCourses(){
        return courseList;
    }
}
