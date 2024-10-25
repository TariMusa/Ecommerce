Online Retail Store Analytics

This project simulates an e-commerce platform's relational database to analyze key business metrics like sales trends, product performance, customer churn, and more. It demonstrates SQL skills in managing relational databases with PostgreSQL.

Project Structure
Database Name: online_retail_store

Tables:
customers: Stores customer information.
products: Contains product details.
orders: Tracks orders and their status.
order_details: Details of each order.
reviews: Holds customer feedback for products.

How to Run the Project

Prerequisites
PostgreSQL installed on your machine.
pgAdmin (optional) for easier database management.
Java or Python (optional) to generate and insert large datasets.

Step-by-Step Guide
1. Create the Database:
Open a PostgreSQL terminal or pgAdmin and run:
    CREATE DATABASE online_retail_store;

2. Create Tables:
Use the SQL script below to create the necessary tables:
    -- Customers Table
    CREATE TABLE Customers (
        customer_id SERIAL PRIMARY KEY,
        name VARCHAR(100),
        email VARCHAR(100),
        join_date DATE
    );

    -- Products Table
    CREATE TABLE Products (
        product_id SERIAL PRIMARY KEY,
        product_name VARCHAR(100),
        category VARCHAR(50),
        price NUMERIC(10, 2)
    );

    -- Orders Table
    CREATE TABLE Orders (
        order_id SERIAL PRIMARY KEY,
        customer_id INT REFERENCES Customers(customer_id),
        order_date DATE,
        total_amount NUMERIC(10, 2)
    );

    -- OrderDetails Table
    CREATE TABLE OrderDetails (
        order_id INT REFERENCES Orders(order_id),
        product_id INT REFERENCES Products(product_id),
        quantity INT,
        price INT
    );

    -- Reviews Table
    CREATE TABLE Reviews (
        review_id SERIAL PRIMARY KEY,
        product_id INT REFERENCES Products(product_id),
        customer_id INT REFERENCES Customers(customer_id),
        rating INT CHECK (rating BETWEEN 1 AND 5),
        review_date DATE,
        review_text TEXT
    );

3. Insert Sample Data:
You can insert sample data manually or generate a large dataset with Java or Python. Here's an example for manual insertion:
    INSERT INTO customers (name, email, join_date, region)
    VALUES ('John Doe', 'john.doe@example.com', '2023-02-15', 'North America');
Alternatively, run my Java Code 'DataGenerator' that will automatically generate and enter the data into your SQL tables.

4. Run Queries:
Use SQL queries to extract insights. Some examples are in the EcormmerceQueries file.

Features Demonstrated
SQL Transactions: Ensures consistent order processing.
Joins and Views: Analyze data across multiple tables.
Window Functions: Track customer purchase behavior over time.
Aggregate Functions: Monitor revenue and sales trends.

Future Improvements
Implement triggers to update stock levels automatically.
Add user authentication for a multi-user system.
Integrate with Tableau or Power BI for enhanced reporting.

Conclusion
This project showcases essential relational database management skills through the lens of an e-commerce platform. It provides hands-on experience with SQL, from table creation to advanced queries, and demonstrates how relational databases can support analytics and business insights.

License
This project is not licensed.

