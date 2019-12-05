# Pousse-Café Sample App

This project is the Sample App module of Pousse-Café. It provides a concrete example of how the Sample Meta App can be 
integrated in an actual back-end application. This example features a Spring Boot application exposing a REST API
whose resources interact with the Meta-Application.

In this example, data are stored in a MongoDB instance. Default Spring Boot configuration is being used, but feel free to override it
(see how to override default configuration [here](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html)
and see
[here](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
for the common application properties).

# Test the app

1. Build and launch the app
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
