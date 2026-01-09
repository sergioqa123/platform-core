import domain.Course;
import domain.Role;
import domain.User;
import service.CourseService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = 0;

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        CourseService courseService = new CourseService();

        do {
            System.out.println("=========== Welcome! ==========");
            System.out.println("| 1. Register user            |");
            System.out.println("| 2. List users               |");
            System.out.println("| 3. Register course          |");
            System.out.println("| 4. List courses             |");
            System.out.println("| 5. Assign course instructor |");
            System.out.println("| 0. Exit                     |");
            System.out.println("===============================");
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scanner.nextLine());
            System.out.println(" ");
            switch (option){
                case 1:
                    System.out.println("What type of user you want to register?");
                    System.out.println("1. Student");
                    System.out.println("2. Instructor");
                    System.out.print("Choose an option: ");
                    String typeOfUser = scanner.nextLine();
                    Role role = null;
                    while (role == null){
                        if (typeOfUser.equals("1")){
                            role = Role.STUDENT;
                        } else if (typeOfUser.equals("2")){
                            role = Role.INSTRUCTOR;
                        } else {
                            System.out.println("Enter a valid type of user.");
                            typeOfUser = scanner.nextLine();
                        }
                    }

                    System.out.print("Enter a name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter an email: ");
                    String email = scanner.nextLine();
                    User user = new User(name, email, role);
                    userService.registerUser(user);
                    System.out.println(" ");
                    break;
                case 2:
                    System.out.println("-------------- User list --------------");
                    for (User u : userService.listUsers()) {
                        System.out.println("Name: " + u.getName() + ", Email: " + u.getEmail() + ", Role: " + u.getRole());
                    }
                    System.out.println("---------------------------------------");
                    System.out.println(" ");
                    break;
                case 3:
                    System.out.print("\nEnter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter a description: ");
                    String description = scanner.nextLine();
                    Course course = new Course(courseName, description);
                    courseService.registerCourse(course);
                    System.out.println(" ");
                    break;
                case 4:
                    System.out.println("-------------- Course list --------------");
                    for (Course c : courseService.listCourses()) {
                        System.out.println("Course: " + c.getName() + ", Description:" + c.getDescription());
                    }
                    System.out.println("---------------------------------------");
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Invalid option...\n");
            }

        } while (option != 0);

        scanner.close();
    }

}
