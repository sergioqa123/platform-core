package service;

import domain.Course;
import domain.User;
import repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(){
        this.courseRepository = new CourseRepository();
    }

    public void registerCourse(Course course){
        courseRepository.saveCourse(course);
    }

    public boolean isUnique(String courseName) {
        for (Course c : courseRepository.getCourses()){
            if (courseName.equals(c.getName())) {
                return false;
            }
        }
        return true;
    }

    public List<Course> getAllCourses(){
        return courseRepository.getCourses();
    }

    // "Available" = no active
    public List<Course> getAvailableCourses (){
        List<Course> availableCourses = new ArrayList<>();
        for (Course c : this.getAllCourses()) {
            if (!c.isActive()) {
                availableCourses.add(c);
            }
        }
        return availableCourses;
    }

    public Course getCourseById(int courseId){
        return courseRepository.getCourseById(courseId);
    }

    public Course getAvailableCourseById(int courseId){
        for (Course c : getAvailableCourses()){
            if (c.getId() == courseId){
                return c;
            }
        }
        return null;
    }

    public void assignInstructorToCourse(Course course, User instructor){
        course.setInstructor(instructor);
        course.setStatus(true); // course is now available
    }

    public void updateCourse(Course selectedCourse, String newName, String newDescription){
        courseRepository.updateCourse(selectedCourse, newName, newDescription);
    }

    public void deleteCourse(Course selectedCourse){
        courseRepository.removeCourse(selectedCourse);
    }
}
