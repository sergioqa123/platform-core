import domain.User;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = 0;

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        do {
            System.out.println("======= Welcome ======");
            System.out.println("| 1. Register user   |");
            System.out.println("| 2. List users      |");
            System.out.println("| 3. Exit            |");
            System.out.println("======================");
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scanner.nextLine());
            System.out.println(" ");
            switch (option){
                case 1:
                    System.out.print("Enter a name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter an email address: ");
                    String email = scanner.nextLine();
                    User user = new User(name, email);
                    userService.registerUser(user);
                    System.out.println(" ");
                    break;
                case 2:
                    System.out.println("-------------- User list --------------");
                    for (User u : userService.listUsers()) {
                        System.out.println("Name: " + u.getName() + ", Email:" + u.getEmail());
                    }
                    System.out.println("---------------------------------------");
                    System.out.println(" ");
                    break;
            }

        } while (option != 3);

        scanner.close();
    }

}
