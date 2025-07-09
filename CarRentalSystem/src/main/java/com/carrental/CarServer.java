package com.carrental;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CarServer {
    private static final String url = "jdbc:postgresql://localhost:5432/carrentaldb";
    private static final String user = "postgres";
    private static final String pw = "gaurav";

    // Show available cars
    public void showAvailableCars() {
        String q = "select id, model, brand, year, price_per_day from car where available=true";
        try (Connection c = DriverManager.getConnection(url, user, pw);
             PreparedStatement st = c.prepareStatement(q);
             ResultSet rs = st.executeQuery()) {
            System.out.println("ID | Model | Brand | Year | Price/Day");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%d | %s | %s | %d | %.2f\n",
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getInt("year"),
                        rs.getDouble("price_per_day"));
            }
            if (!found) System.out.println("No available cars.");
        } catch (Exception ex) {
            System.out.println("Error showing available cars!");
        }
    }

    // Rent a car
    public void rentCar(int custId, int carId, String start, String end) {
        String q1 = "select price_per_day from car where id=? and available=true";
        String q2 = "update car set available=false where id=?";
        String q3 = "insert into rental (car_id, customer_id, start_date, end_date, total_price) values (?, ?, ?, ?, ?)";
        try (Connection c = DriverManager.getConnection(url, user, pw)) {
            double price = 0;
            try (PreparedStatement st = c.prepareStatement(q1)) {
                st.setInt(1, carId);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        price = rs.getDouble(1);
                    } else {
                        System.out.println("Car not found or not available.");
                        return;
                    }
                }
            }
            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);
            long days = ChronoUnit.DAYS.between(d1, d2) + 1;
            double total = price * days;

            try (PreparedStatement st = c.prepareStatement(q2)) {
                st.setInt(1, carId);
                st.executeUpdate();
            }
            try (PreparedStatement st = c.prepareStatement(q3)) {
                st.setInt(1, carId);
                st.setInt(2, custId);
                st.setDate(3, Date.valueOf(start));
                st.setDate(4, Date.valueOf(end));
                st.setDouble(5, total);
                st.executeUpdate();
            }
            System.out.println("Car rented! Total price: " + total);
        } catch (Exception ex) {
            System.out.println("Error renting car!");
        }
    }

    // Add a car (for server/admin)
    public void addCar(Scanner sc) {
        System.out.print("Enter car model: ");
        String model = sc.nextLine();
        System.out.print("Enter car brand: ");
        String brand = sc.nextLine();
        System.out.print("Enter car year: ");
        int year = sc.nextInt();
        System.out.print("Enter price per day: ");
        double price = sc.nextDouble();
        sc.nextLine();

        String sql = "insert into car (model, brand, year, price_per_day) values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, pw);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, model);
            ps.setString(2, brand);
            ps.setInt(3, year);
            ps.setDouble(4, price);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Car added!" : "Failed to add car.");
        } catch (Exception e) {
            System.out.println("Error adding car!");
        }
    }

    // Delete a car (for server/admin)
    public void delCar(Scanner sc) {
        System.out.print("Car id to delete: ");
        int id = sc.nextInt();
        String q = "delete from car where id=?";
        try (Connection c = DriverManager.getConnection(url, user, pw);
             PreparedStatement st = c.prepareStatement(q)) {
            st.setInt(1, id);
            int r = st.executeUpdate();
            System.out.println(r > 0 ? "Deleted!" : "Not found.");
        } catch (Exception ex) {
            System.out.println("Error deleting car!");
        }
    }
}