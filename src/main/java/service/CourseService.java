package service;

import domain.Course;
import domain.Role;
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

    public boolean alreadyExists(String courseName) {
        for(Course c : courseRepository.getCourses()){
            if(courseName.equals(c.getName())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Course> getAllCourses(){
        return courseRepository.getCourses();
    }

    // "Available" = no active
    public ArrayList<Course> getAvailableCourses (){
        ArrayList<Course> availableCourses = new ArrayList<>();
        for (Course c : this.getAllCourses()) {
            if (!c.isActive()) {
                availableCourses.add(c);
            }
        }
        return availableCourses;
    }

    public Course getAvailableCourseById(int courseId){
        for(Course c : getAvailableCourses()){
            if(c.getId() == courseId){
                return c;
            }
        }
        return null;
    }

//    public boolean assignInstructorToCourse(Course course, User instructor){
//        if(instructor.getRole() == Role.INSTRUCTOR){
//            course.setInstructor(instructor);
//            course.setStatus(true); // course is now available
//            return true; // successful assignment
//        }
//        return false;
//    }


}
