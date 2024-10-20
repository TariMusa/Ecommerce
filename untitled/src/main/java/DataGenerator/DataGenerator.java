package DataGenerator;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class DataGenerator {

    // PostgreSQL connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/e_commerce_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Tarie?9654";

    public static void main(String[] args) {
        Faker faker = new Faker();  // For generating fake data

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to PostgreSQL database!");

            generateCustomers(conn, faker, 100); // Generate 100 customers
            generateProducts(conn, faker, 50);   // Generate 50 products
            generateOrders(conn, faker, 200);    // Generate 200 orders
            generateReviews(conn, faker, 300);    // Generate 300 reviews
            generateOrderDetails(conn, faker, 500); // Generate 500 order details

            System.out.println("Data generation completed successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Generate Customers
    private static void generateCustomers(Connection conn, Faker faker, int count) throws SQLException {
        String sql = "INSERT INTO Customers (name, email, join_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < count; i++) {
                stmt.setString(1, faker.name().fullName());
                stmt.setString(2, faker.internet().emailAddress());
                // Correctly convert java.util.Date to java.sql.Date
                java.util.Date utilDate = faker.date().past(1000, TimeUnit.DAYS);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                stmt.setDate(3, sqlDate);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Generate Products
    private static void generateProducts(Connection conn, Faker faker, int count) throws SQLException {
        String sql = "INSERT INTO Products (product_name, category, price) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<String> categories = Arrays.asList("Electronics", "Books", "Clothing", "Toys");
            for (int i = 0; i < count; i++) {
                stmt.setString(1, faker.commerce().productName());
                stmt.setString(2, categories.get(faker.random().nextInt(categories.size())));
                stmt.setDouble(3, Double.parseDouble(faker.commerce().price()));
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Generate Orders
    private static void generateOrders(Connection conn, Faker faker, int count) throws SQLException {
        String sql = "INSERT INTO Orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < count; i++) {
                stmt.setInt(1, faker.random().nextInt(1, 100));  // Assuming 100 customers
                // Correctly convert java.util.Date to java.sql.Date
                java.util.Date utilDate = faker.date().past(100, TimeUnit.DAYS);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                stmt.setDate(2, sqlDate);
                stmt.setDouble(3, faker.number().randomDouble(2, 20, 500));
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Generate Reviews
    // Generate Reviews
    private static void generateReviews(Connection conn, Faker faker, int count) throws SQLException {
        String sql = "INSERT INTO Reviews (customer_id, product_id, review_text, rating, review_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < count; i++) {
                stmt.setInt(1, faker.random().nextInt(1, 100)); // Assuming 100 customers
                stmt.setInt(2, faker.random().nextInt(1, 51)); // Assuming 50 products
                stmt.setString(3, faker.lorem().sentence()); // Generates a random review text
                // Ensure rating is between 1 and 5
                int rating = faker.random().nextInt(1, 5); // Generates a rating between 1 and 5
                stmt.setInt(4, rating);
                java.util.Date utilDate = faker.date().past(30, TimeUnit.DAYS); // Random past date within 30 days
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                stmt.setDate(5, sqlDate);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }


    // Generate Order Details
    private static void generateOrderDetails(Connection conn, Faker faker, int count) throws SQLException {
        String sql = "INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < count; i++) {
                stmt.setInt(1, faker.random().nextInt(1, 200));  // Assuming 200 orders
                stmt.setInt(2, faker.random().nextInt(1, 50));   // Assuming 50 products
                stmt.setInt(3, faker.random().nextInt(1, 5));     // Quantity between 1 and 4
                stmt.setDouble(4, faker.number().randomDouble(2, 20, 500)); // Random price
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}
