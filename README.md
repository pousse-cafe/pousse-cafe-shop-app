# Pousse-Café Shop App

This project is a sample app illustrating the usage of Pousse-Café. It features a Spring Boot application exposing a
REST API whose resources interact with an online shop model. Data are stored in an H2 embedded DB using JPA. Messages
are exchanged using Pousse-Café's embedded messaging.

# Explore the code

- `poussecafe.shop` contains Spring and Pousse-Café configuration classes
- `poussecafe.shop.api` package and its sub-packages contain the controllers and views implementing the REST API
- `poussecafe.shop.model` package and its sub-packages contain the aggregates and their implementation

# Test the app

1. Build and launch the app with Maven: `mvn spring-boot:run`
2. Create a product with the following command:

        curl -X POST "http://localhost:8080/product" -d '{"id":"product-123"}' -H "Content-Type: application/json"

3. Fetch the product:

        curl -X GET "http://localhost:8080/product/product-123"

4. Add units to a product:

        curl -X POST "http://localhost:8080/product/product-123/add-units" -d '{"units":"10"}' -H "Content-Type: application/json"

5. Create a customer:

        curl -X POST "http://localhost:8080/customer" -d '{"id":"cust-456"}' -H "Content-Type: application/json"

6. Fetch the customer:

        curl -X GET "http://localhost:8080/customer/cust-456"

7. Place an order:

        curl -X POST "http://localhost:8080/placed-orders" -d '{"customerId":"cust-456", "reference":"my-ref", "productId":"product-123", "units":"5"}' -H "Content-Type: application/json"

8. Fetch the order

        curl -X GET "http://localhost:8080/orders/cust-456/my-ref/product-123"

7. Settle the order:

        curl -X POST "http://localhost:8080/settled-orders" -d '{"customerId":"cust-456", "reference":"my-ref", "productId":"product-123"}' -H "Content-Type: application/json"

8. Ship the order:

        curl -X POST "http://localhost:8080/shipped-orders" -d '{"customerId":"cust-456", "reference":"my-ref", "productId":"product-123"}' -H "Content-Type: application/json"
