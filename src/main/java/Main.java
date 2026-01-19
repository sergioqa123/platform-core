import domain.Course;
import domain.Role;
import domain.User;
import service.CourseService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CourseService courseService = new CourseService();

    public static void main(String[] args) {
        int option = 0;
        do {
            showMenu();
            System.out.print("Choose an option: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.print("\nEnter a number.");
                option = -1;
            }
            System.out.println(" ");
            switch (option){
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    registerUser();
                    break;
                case 2:
                    printUsers();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    registerCourse();
                    break;
                case 6:
                    printCourses();
                    break;
                case 7:
                    updateCourse();
                    break;
                case 8:
                    deleteCourse();
                    break;
                case 9:
                    assignInstructorToCourse();
                    break;
                case 10:
                    enrollStudent();
                    break;
                default:
                    System.out.println("Selected option doesn't exists...\n");
            }
        } while (option != 0);
        scanner.close();
    }

    public static void showMenu(){
        System.out.println("\n=========== Welcome! ==========");
        System.out.println("[1] Register user");
        System.out.println("[2] List users");
        System.out.println("[3] Update user");
        System.out.println("[4] Delete user");
        System.out.println("[5] Register course");
        System.out.println("[6] List courses");
        System.out.println("[7] Update course");
        System.out.println("[8] Delete course");
        System.out.println("[9] Assign instructor");
        System.out.println("[10] Enroll student");
        System.out.println("[0] Exit");
        System.out.println("===============================");
    }

    // -- USER OPTIONS --

    public static void registerUser(){
        System.out.println("What type of user you want to register?");
        System.out.println("[1] Student");
        System.out.println("[2] Instructor");
        System.out.print("\nChoose an option (0 to cancel): ");

        String roleInput = scanner.nextLine();
        if (roleInput.equals("0")){
            return;
        }
        while (!userService.isValidRole(roleInput)){
            System.out.print("Enter a valid type of user (1 or 2): ");
            roleInput = scanner.nextLine();
        }

        String name = "";
        while (name.isBlank()){
            System.out.print("Enter a name: ");
            name = scanner.nextLine();
        }

        String email = "";
        while (email.isBlank()){
            System.out.print("Enter an email: ");
            email = scanner.nextLine();
        }

        userService.registerUser(name, email, roleInput);
        System.out.println("User registered successfully!");
    }

    public static User selectUserById(){
        User selectedUser = null;
        int userId = 0;
        while (selectedUser == null){
            System.out.print("Select a user ID (0 to cancel): ");
            try {
                userId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
                continue;
            }
            if (userId == 0){
                return null;
            }
            selectedUser = userService.getUserById(userId);
            if (selectedUser == null){
                System.out.println("User not found.");
            }
        }
        return selectedUser;
    }

    public static void printUsers(){
        System.out.println("------------------- User list -------------------");
        List<User> userList = userService.getAllUsers();
        for (User u : userList){
            System.out.println(u);
        }
        if (userList.isEmpty()){
            System.out.println("No users found.");
        }
        System.out.println("-------------------------------------------------");
    }

    public static void updateUser(){
        printUsers();
        User selectedUser = selectUserById();
        if (selectedUser == null) {
            return;
        }
        System.out.print("Enter a new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter a new email: ");
        String newEmail = scanner.nextLine();
        userService.updateUser(selectedUser, newName, newEmail);
        System.out.println("User updated successfully");
    }

    public static void deleteUser(){
        printUsers();
        User selectedUser = selectUserById();

        if (selectedUser == null) {
            return;
        }

        if(selectedUser.getRole() == Role.INSTRUCTOR){
            if (courseService.isInstructorAssigned(selectedUser)){
                System.out.println("Failed: instructor is currently assigned in a course.");
                System.out.println("Unassign the instructor from all courses first.");
                return;
            }
        } else if (selectedUser.isEnrolled()) {
            System.out.println("Failed: student is currently enrolled in a course.");
            System.out.println("Unenroll the student from all courses first.");
            return;
        }

        System.out.println("Are you sure you want to delete " + selectedUser.getName() + "? (y/n)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")){
            userService.deleteUser(selectedUser);
            System.out.println("\nUser '" + selectedUser.getName() + "' deleted successfully!");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // -- COURSE OPTIONS --

    public static void registerCourse(){
        String courseName = null;
        while (courseName == null){
            System.out.print("Enter a course name: ");
            courseName = scanner.nextLine();

            if (!courseService.isUnique(courseName)){
                System.out.println("Course '" + courseName + "' already exists.");
                courseName = null;
            } else if (courseName.isBlank()){
                courseName = null;
            }
        }

        String description = "";
        while (description.isBlank()){
            System.out.print("Enter a description: ");
            description = scanner.nextLine();
        }

        Course course = new Course(courseName, description);
        courseService.registerCourse(course);
        System.out.println("Course registered successfully!");
    }

    public static Course selectCourseById(){
        Course selectedCourse = null;
        int courseId = 0;

        while (selectedCourse == null){
            System.out.print("Select a course ID (0 to cancel): ");
            try {
                courseId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
                continue;
            }
            if (courseId == 0){
                return null;
            }
            selectedCourse = courseService.getCourseById(courseId);
            if (selectedCourse == null){
                System.out.println("Course not found.");
            }
        }
        return selectedCourse;
    }

    public static Course selectAvailableCourseById(){
        Course selectedCourse = null;
        int courseId = 0;

        while (selectedCourse == null){
            System.out.print("Select a course ID (0 to cancel): ");
            try {
                courseId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
                continue;
            }
            if (courseId == 0){
                return null;
            }
            selectedCourse = courseService.getAvailableCourseById(courseId);
            if (selectedCourse == null){
                System.out.println("Course not found.");
            }
        }
        return selectedCourse;
    }

    public static Course selectActiveCourseById(){
        Course selectedCourse = null;
        int courseId = 0;

        while (selectedCourse == null){
            System.out.print("Select a course ID (0 to cancel): ");
            try {
                courseId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
                continue;
            }
            if (courseId == 0){
                return null;
            }
            selectedCourse = courseService.getActiveCourseById(courseId);
            if (selectedCourse == null){
                System.out.println("Course not found.");
            }
        }
        return selectedCourse;
    }

    public static void printCourses(){
        System.out.println("------------- Course list -------------");
        List<Course> courseList = courseService.getAllCourses();
        for (Course c : courseList){
            System.out.println(c);
        }
        if (courseList.isEmpty()){
            System.out.println("No courses found.");
        }
        System.out.println("----------------------------------------------");
        System.out.println(" ");
    }

    public static void printAvailableCourses(){
        System.out.println("------------- Available courses -------------");
        List<Course> availableCourses = courseService.getAvailableCourses();
        for (Course c : availableCourses) {
            System.out.println(c);
        }
        if (availableCourses.isEmpty()){
            System.out.println("No available courses found.");
        }
        System.out.println("---------------------------------------------");
    }

    public static void printActiveCourses(){
        System.out.println("------------- Active courses -------------");
        List<Course> activeCourses = courseService.getActiveCourses();
        for (Course c : activeCourses){
            System.out.println(c);
        }
        if (activeCourses.isEmpty()){
            System.out.println("No active courses found.");
        }
        System.out.println("---------------------------------------------");
    }

    public static void updateCourse(){
        printCourses();
        Course selectedCourse = selectCourseById();
        if (selectedCourse == null){
            return;
        }
        System.out.print("Enter a new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter a new description: ");
        String newDescription = scanner.nextLine();

        courseService.updateCourse(selectedCourse, newName, newDescription);
        System.out.println("Course updated successfully");
    }

    public static void deleteCourse(){
        printCourses();
        Course selectedCourse = selectCourseById();
        if (selectedCourse == null){
            return;
        }
        System.out.println("Are you sure you want to delete " + selectedCourse.getName() + "? (y/n)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")){
            courseService.deleteCourse(selectedCourse);
            System.out.println("\nCourse '" + selectedCourse.getName() + "' deleted successfully!");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // OTHERS

    public static void assignInstructorToCourse(){
        printAvailableCourses();
        Course selectedCourse = selectAvailableCourseById();
        if (selectedCourse == null){
            return;
        }

        System.out.println("------------- Available instructors -------------");
        List<User> availableInstructors = userService.getAllInstructors();
        for (User u : availableInstructors) {
            System.out.println(u);
        }
        if (availableInstructors.isEmpty()){
            System.out.println("No available instructors found.");
        }
        System.out.println("-------------------------------------------------");

        User selectedInstructor = null;
        int userId;

        while (selectedInstructor == null){
            System.out.print("Select an instructor ID (0 to cancel): ");
            try {
                userId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
                continue;
            }
            if (userId == 0){
                return;
            }

            selectedInstructor = userService.getInstructorById(userId);

            if (selectedInstructor == null) {
                System.out.println("Select a valid instructor ID.");
            }
        }
        courseService.assignInstructorToCourse(selectedCourse, selectedInstructor);
        System.out.println("Instructor assigned successfully!");
    }

    public static void enrollStudent(){
        printActiveCourses();
        Course selectedCourse = null;
        while (selectedCourse == null){
            selectedCourse = selectActiveCourseById();
            if (selectedCourse == null){
                System.out.println("Enrollment failed.");
                return;
            }
        }
        List<User> students = userService.getAllUsersByRole(Role.STUDENT);
        if (students.isEmpty()){
            System.out.println("There are no students!");
            return;
        }

        System.out.println("------------------- Student list -------------------");
        for (User u : students){
            System.out.println(u);
        }
        System.out.println("-------------------------------------------------");

        User selectedStudent = null;

        while (true){
            selectedStudent = selectUserById();
            if (selectedStudent == null){
                System.out.println("Enrollment cancelled.");
                return;
            }
            if (selectedStudent.getRole() != Role.STUDENT) {
                System.out.println("Selected user is not a student!");
                continue;
            }
            if (selectedCourse.getStudents().contains(selectedStudent)) {
                System.out.println("'" + selectedStudent.getName() + "' is already enrolled in '" + selectedCourse.getName() + "'.");
                continue;
            }
            break;
        }
        courseService.enrollStudentToCourse(selectedCourse, selectedStudent);
        System.out.println("Student enrolled successfully!");
    }
}
