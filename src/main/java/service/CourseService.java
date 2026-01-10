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

    public boolean isValidName(String courseName) {
        for(Course c : courseRepository.getCourses()){
            if(courseName.equals(c.getName())) {
                System.out.println("Course already exists.");
                return false;
            }
        }
        return true;
    }

    public boolean isValidAvailableCourseID(String courseOption) {
        for (Course c : getAvailableCourses()) {
            if (Integer.parseInt(courseOption) == c.getId()) {
                System.out.println("Selected course: " + c.getName());
                return true;
            }
        }
        System.out.println("Invalid course ID...");
        return false;
    }


    public boolean assignInstructorToCourse(Course course, User instructor){
        if(instructor.getRole() == Role.INSTRUCTOR){
            course.setInstructor(instructor);
            course.setStatus(true); // course is now available
            return true; // successful assignment
        }
        return false;
    }

    public ArrayList<Course> getAvailableCourses (){
        ArrayList<Course> availableCourses = new ArrayList<>(); // "available" meaning there's no instructor
        for (Course c : this.listCourses()) {
            if (c.getInstructor() == null) {
                availableCourses.add(c);
            }
        }
        return availableCourses;
    }

    public void assignInstructorToCourse(CourseService courseService, UserService userService) {

//        //User logic
//        do {
//
//
//            // verify instructor id
//            for (User u : instructors) {
//                if (userOption == u.getId()) {
//                    System.out.println("Selected instructor: " + u.getName());
//                    fInstructor = true;
//                    break;
//                }
//            }
//            if (!fInstructor) {
//                System.out.println("Invalid instructor ID...\n");
//            }
//
//        } while (!fInstructor);
    }

    public ArrayList<Course> listCourses(){
        return courseRepository.getCourses();
    }
}
