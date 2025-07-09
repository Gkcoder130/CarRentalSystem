package com.carrental;
import java.util.Scanner;

public class UserMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarServer server = new CarServer();

        while (true) {
            System.out.println("==== User Menu ====");
            System.out.println("1. Show Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    server.showAvailableCars();
                    break;
                case 2:
                    System.out.print("Enter your customer id: ");
                    int custId = sc.nextInt();
                    System.out.print("Enter car id: ");
                    int carId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Start date (yyyy-mm-dd): ");
                    String start = sc.nextLine();
                    System.out.print("End date (yyyy-mm-dd): ");
                    String end = sc.nextLine();
                    server.rentCar(custId, carId, start, end);
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