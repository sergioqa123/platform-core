import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("==== Bienvenido ====");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Salir");
            System.out.println("====================");
            System.out.print("Elija una opcion: ");
            option = Integer.parseInt(scanner.nextLine());

        } while (option != 3);
    }
}
