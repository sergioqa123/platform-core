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

    public boolean isValidCourseName(String courseName) {
        for(Course c : courseRepository.getCourses()){
            if(courseName.equals(c.getName())) {
                System.out.println("Course already exists.");
                return false;
            }
        }
        return true;
    }

    public Course getAvailableCourseById(int courseId){
        for(Course c : getAvailableCourses()){
            if(courseId == c.getId()){
                return c;
            }
        }
        return null;
    }

//    public boolean isValidAvailableCourseId(String courseOption) { // to-do: simplify
//        for (Course c : getAvailableCourses()) {
//            if (Integer.parseInt(courseOption) == c.getId()) {
//                System.out.println("Selected course: " + c.getName());
//                return true;
//            }
//        }
//        System.out.println("Invalid course ID...");
//        return false;
//    }


    public boolean assignInstructorToCourse(String courseOption, String userOption){
        if(instructor.getRole() == Role.INSTRUCTOR){
            course.setInstructor(instructor);
            course.setStatus(true); // course is now available
            return true; // successful assignment
        }
        return false;
    }

    // "Available" = No instructor
    public ArrayList<Course> getAvailableCourses (){
        ArrayList<Course> availableCourses = new ArrayList<>();
        for (Course c : this.listCourses()) {
            if (c.getInstructor() == null) {
                availableCourses.add(c);
            }
        }
        return availableCourses;
    }

    public void assignInstructorToCourse(String courseOption, String userOption) {

    }

    public ArrayList<Course> listCourses(){
        return courseRepository.getCourses();
    }
}
