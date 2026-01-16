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

    public List<Course> getCourses(){
        return courseList;
    }

    public void updateCourse(Course selectedCourse, String newName, String newDescription){
        selectedCourse.setName(newName);
        selectedCourse.setDescription(newDescription);
    }

    public void removeCourse(Course course){
        courseList.remove(course);
    }

    public Course getCourseById(int id){
        for (Course c : courseList){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
