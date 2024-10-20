/* Top-selling Products */
SELECT P.product_name, SUM(OD.quantity) AS total_sold
FROM OrderDetails OD
JOIN Products P ON OD.product_id = P.product_id
GROUP BY P.product_name
ORDER BY total_sold DESC;

/* Top Customers by Spend */
SELECT C.name, SUM(O.total_amount) AS total_spent
FROM Orders O
JOIN Customers C ON O.customer_id = C.customer_id
GROUP BY C.name
ORDER BY total_spent DESC;

/* Customer Churn Rate (No Orders in Last 30 Days) */
SELECT COUNT(*) AS churned_customers
FROM Customers C
WHERE C.customer_id NOT IN (
    SELECT customer_id FROM Orders 
    WHERE order_date >= CURRENT_DATE - INTERVAL '30 days'
);

/* Revenue Growth by Month */
SELECT TO_CHAR(order_date, 'YYYY-MM') AS month, SUM(total_amount) AS revenue
FROM Orders
GROUP BY month
ORDER BY month;

/* Total Revenue */
SELECT SUM(total_amount) AS total_revenue
FROM Orders;

/* Revenue by Product Category */
SELECT p.category, SUM(o.total_amount) AS total_revenue
FROM Orders o
JOIN OrderDetails od ON o.order_id = od.order_id
JOIN Products p ON od.product_id = p.product_id
GROUP BY p.category
ORDER BY total_revenue DESC;

/* Top-Selling Products */
SELECT p.product_name, SUM(od.quantity) AS total_quantity_sold
FROM OrderDetails od
JOIN Products p ON od.product_id = p.product_id
GROUP BY p.product_name
ORDER BY total_quantity_sold DESC
LIMIT 10;

/* Customer Purchase History */
SELECT o.order_id, o.order_date, o.total_amount
FROM Orders o
WHERE o.customer_id = 1;  -- Replace '1' with the desired customer ID

/* Monthly Revenue Growth */
SELECT DATE_TRUNC('month', o.order_date) AS month,
       SUM(o.total_amount) AS monthly_revenue
FROM Orders o
WHERE o.order_date >= CURRENT_DATE - INTERVAL '1 year'
GROUP BY month
ORDER BY month;

/*Average Order Value */
SELECT AVG(total_amount) AS average_order_value
FROM Orders;

/*Most Active Customers */
SELECT c.name, COUNT(o.order_id) AS order_count
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
GROUP BY c.name
ORDER BY order_count DESC
LIMIT 10;





