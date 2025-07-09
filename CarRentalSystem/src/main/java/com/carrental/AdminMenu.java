package com.carrental;
import java.util.Scanner;
public class AdminMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarServer server = new CarServer();

        while (true) {
            System.out.println("==== Admin Menu ====");
            System.out.println("1. Add Car");
            System.out.println("2. Delete Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    server.addCar(sc);
                    break;
                case 2:
                    server.delCar(sc);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}