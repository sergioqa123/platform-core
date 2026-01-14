import domain.Course;
import domain.Role;
import domain.User;
import service.CourseService;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static CourseService courseService = new CourseService();

    public static void main(String[] args) {
        int option = 0;

        do {
            System.out.println("=========== Welcome! ==========");
            System.out.println("[1] Register user");
            System.out.println("[2] List users");
            System.out.println("[3] Update user");
            System.out.println("[4] Delete user");
            System.out.println("[5] Register course");
            System.out.println("[6] List courses");
            System.out.println("[7] Update course");
            System.out.println("[8] Delete course");
            System.out.println("[9] Assign course instructor");
            System.out.println("[0] Exit");
            System.out.println("===============================");
            System.out.print("Choose an option: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("\nEnter a valid option.");
                option = -1; // Change option value to prevent finishing the loop
            }
            System.out.println(" ");
            switch (option){
                case 0:{
                    System.out.println("Goodbye!");
                    break;}
                case 1:{ // Register user
                    System.out.println("What type of user you want to register?");
                    System.out.println("1. Student");
                    System.out.println("2. Instructor");
                    System.out.print("Choose an option: ");
                    String roleInput = scanner.nextLine();
                    while (!userService.isValidRole(roleInput)){
                        System.out.print("Enter a valid type of user: ");
                        roleInput = scanner.nextLine();
                    }
                    System.out.print("Enter a name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter an email: ");
                    String email = scanner.nextLine();
                    userService.registerUser(name, email, roleInput);
                    System.out.println("User registered successfully!");
                    System.out.println(" ");
                    break;}
                case 2:{ // List users
                    printUsers();
                    break;}
                case 3:{ // Update user
                    printUsers();
                    User selectedUser = null;
                    int userId = 0;

                    while (selectedUser == null){
                        System.out.print("Select a user ID: ");
                        try {
                            userId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a number.");
                            continue;
                        }
                        selectedUser = userService.getUserById(userId);
                        if (selectedUser == null){
                            System.out.println("User not found.");
                        }
                    }
                    System.out.print("Enter a new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter a new email: ");
                    String newEmail = scanner.nextLine();

                    userService.updateUser(selectedUser, newName, newEmail);
                    System.out.println("User updated successfully");
                    break;}
                case 4: { // Delete user
                    printUsers();
                    User selectedUser = null;
                    int userId = 0;

                    while (selectedUser == null){
                        System.out.print("Select the user ID to be deleted: ");
                        try {
                            userId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a number.");
                            continue;
                        }
                        selectedUser = userService.getUserById(userId);
                        if (selectedUser == null){
                            System.out.println("User not found.");
                        }
                    }
                    System.out.println("User '" + selectedUser.getName() + "' deleted successfully");
                    System.out.println(" ");
                    userService.deleteUser(selectedUser);
                    break;}
                case 5:{ // Register course
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    while (!courseService.isUnique(courseName)){
                        System.out.println("Course already exists.");
                        System.out.print("Enter a new course name: ");
                        courseName = scanner.nextLine();
                    }
                    System.out.print("Enter a description: ");
                    String description = scanner.nextLine();
                    Course course = new Course(courseName, description);
                    courseService.registerCourse(course);
                    System.out.println(" ");
                    break;}
                case 6:{ // List courses
                    System.out.println("-------------- Course list --------------");
                    for (Course c : courseService.getAllCourses()) {
                        System.out.print("Course: " + c.getName() + ", Description: " + c.getDescription()  + ", Instructor: ");
                        if (c.getInstructor() == null){
                            System.out.println("Not assigned");
                        } else {
                            System.out.println(c.getInstructor().getName());
                        }
                    }
                    System.out.println("---------------------------------------");
                    System.out.println(" ");
                    break;}
                case 7:{ // Update course
                    printCourses();
                    Course selectedCourse = null;
                    int courseId = 0;

                    while (selectedCourse == null){
                        System.out.print("Select a course ID: ");
                        try {
                            courseId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a number.");
                            continue;
                        }
                        selectedCourse = courseService.getCourseById(courseId);
                        if (selectedCourse == null){
                            System.out.println("Course not found.");
                        }
                    }
                    System.out.print("Enter a new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter a new description: ");
                    String newDescription = scanner.nextLine();

                    courseService.updateCourse(selectedCourse, newName, newDescription);
                    System.out.println("User updated successfully");
                    break;}
                case 8:{ // Delete course
                    printCourses();
                    Course selectedCourse = null;
                    int courseId = 0;

                    while (selectedCourse == null){
                        System.out.print("Select the course ID to be deleted: ");
                        try {
                            courseId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a number.");
                            continue;
                        }
                        selectedCourse = courseService.getCourseById(courseId);
                        if (selectedCourse == null){
                            System.out.println("Course not found.");
                        }
                    }
                    System.out.println("Course '" + selectedCourse.getName() + "' deleted successfully");
                    System.out.println(" ");
                    courseService.deleteUser(selectedCourse);
                    break;}
                case 9: { // Assign course instructor
                    // to do: return to menu if course list is empty (same for instructors)
                    System.out.println("------------- Available courses -------------");
                    for (Course c : courseService.getAvailableCourses()){
                        System.out.println("ID: " + c.getId() + ", Name: " + c.getName());
                    }
                    System.out.println("---------------------------------------------");
                    Course selectedCourse = null;
                    int courseId;

                    while (selectedCourse == null) {
                        System.out.print("Select a course ID: ");
                        try {
                            courseId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.print("Enter a number.");
                            continue;
                        }
                        selectedCourse = courseService.getAvailableCourseById(courseId);

                        if (selectedCourse == null){
                            System.out.println("Select a valid course ID.");
                        }
                    }

                    System.out.println("------------- Available instructors -------------");
                    for (User u : userService.getAllUsersByRole(Role.INSTRUCTOR)) {
                        System.out.println("ID: " + u.getId() + ", Name: " + u.getName());
                    }
                    System.out.println("-------------------------------------------------");
                    User selectedInstructor = null;
                    int userId;

                    while (selectedInstructor == null){
                        System.out.print("Select an instructor ID: ");
                        try {
                            userId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a number.");
                            continue;
                        }
                        selectedInstructor = userService.getInstructorById(userId);

                        if (selectedInstructor == null){
                            System.out.println("Select a valid instructor ID.");
                        }
                    }
                    courseService.assignInstructorToCourse(selectedCourse, selectedInstructor);
                    System.out.println("Instructor assigned successfully!");
                    break;}
                default:
                    System.out.println("Invalid option...\n");
            }

        } while (option != 0);

        scanner.close();
    }

    public static void printUsers(){
        System.out.println("------------------- User list -------------------");
        for (User u : userService.getAllUsers()){
            System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Email: " + u.getEmail() + ", Role: " + u.getRole());
        }
        System.out.println("-------------------------------------------------");
        System.out.println(" ");
    }

    public static void printCourses(){
        System.out.println("------------- Course list -------------");
        for (Course c : courseService.getAllCourses()){
            System.out.println("ID: " + c.getId() + ", Name: " + c.getName());
        }
        System.out.println("----------------------------------------------");
        System.out.println(" ");
    }
}
