import domain.Course;
import domain.Role;
import domain.User;
import service.CourseService;
import service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = 0;

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        CourseService courseService = new CourseService();

        do {
            System.out.println("=========== Welcome! ==========");
            System.out.println("[1] Register user");
            System.out.println("[2] List users");
            System.out.println("[3] Register course");
            System.out.println("[4] List courses");
            System.out.println("[5] Assign course instructor");
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
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1: // Register user
                    System.out.println("What type of user you want to register?");
                    System.out.println("1. Student");
                    System.out.println("2. Instructor");
                    System.out.print("Choose an option: ");
                    String roleInput = scanner.nextLine();
                    while(!userService.isValidRole(roleInput)){
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
                    break;
                case 2: // List users
                    System.out.println("------------------- User list -------------------");
                    for (User u : userService.listUsers()) {
                        System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Email: " + u.getEmail() + ", Role: " + u.getRole());
                    }
                    System.out.println("-------------------------------------------------");
                    System.out.println(" ");
                    break;
                case 3: // Register course
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    while(!courseService.isValidName(courseName)){
                        System.out.print("Enter a new course name: ");
                        courseName = scanner.nextLine();
                    }
                    System.out.print("Enter a description: ");
                    String description = scanner.nextLine();
                    Course course = new Course(courseName, description);
                    courseService.registerCourse(course);
                    System.out.println(" ");
                    break;
                case 4: // List courses
                    System.out.println("-------------- Course list --------------");
                    for (Course c : courseService.listCourses()) {
                        System.out.print("Course: " + c.getName() + ", Description: " + c.getDescription()  + ", Instructor: ");
                        if (c.getInstructor() == null){
                            System.out.println("Not assigned");
                        } else {
                            System.out.println(c.getInstructor());
                        }
                    }
                    System.out.println("---------------------------------------");
                    System.out.println(" ");
                    break;
                case 5: // Assign course instructor
                    // to do: return to menu if course list is empty
                    assignInstructorToCourse(courseService, userService);
                    break;
                default:
                    System.out.println("Invalid option...\n");
            }

        } while (option != 0);

        scanner.close();
    }

    public static void assignInstructorToCourse(CourseService courseService, UserService userService){
        int courseOption = 0; // course id
        int userOption = 0; // user id
        boolean fOption = false; // flag to stop loop
        boolean fInstructor = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> coursesWithoutInstructor = new ArrayList<>();
        ArrayList<User> instructors = new ArrayList<>();

        for (Course c : courseService.listCourses()){
            if(c.getInstructor() == null){
                coursesWithoutInstructor.add(c);
            }
        }

        for (User u : userService.listUsers()){
            if(u.getRole() == Role.INSTRUCTOR){
                instructors.add(u);
            }
        }

        // Course logic
        do {
            System.out.println("------------- Available courses -------------");
            for (Course c : coursesWithoutInstructor){
                System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Description: " + c.getDescription());
            }
            System.out.println("---------------------------------------------");

            System.out.print("Select a course ID: ");
            courseOption = Integer.parseInt(scanner.nextLine());

            // verify course id
            for (Course c : coursesWithoutInstructor){
                if(courseOption == c.getId()){
                    System.out.println("Selected course: " + c.getName());
                    fOption = true;
                    break;
                }
            }
            if (!fOption){
                System.out.println("Invalid course ID...\n");
            }

        } while (!fOption);

        //User logic
        do {
            System.out.println("------------- Available instructors -------------");
            for (User u : instructors){
                System.out.println("ID: " + u.getId() + ", Name: " + u.getName());
            }
            System.out.println("---------------------------------------------");

            System.out.print("Select an instructor ID: ");
            userOption = Integer.parseInt(scanner.nextLine());

            // verify instructor id
            for (User u : instructors){
                if(userOption == u.getId()){
                    System.out.println("Selected instructor: " + u.getName());
                    fInstructor = true;
                    break;
                }
            }
            if (!fInstructor){
                System.out.println("Invalid instructor ID...\n");
            }

        } while (!fInstructor);

        // assignment
        //courseService.assignInstructor(, userOption);
    }

}
