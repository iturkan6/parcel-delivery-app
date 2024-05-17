# Parcel Delivery App

## Overview

The Parcel Delivery App is a microservices-based application designed to manage parcel deliveries efficiently. The application consists of three main microservices:

1. **Auth-Service**: Handles user authentication and authorization.
2. **Order-Service**: Manages orders, including creation, status updates, and assignments based on user roles (USER, COURIER, ADMIN).
3. **Notification-Service**: Sends email notifications to users about the status changes of their parcels using Kafka.

## Microservices

**OpenAPI Access:**
- `http://localhost:{PORT}/v1/api-docs/swagger-ui.html`

### Auth-Service

The Auth-Service is responsible for user authentication using Spring Security.

**Environment Variables:**
- `PORT`: The port on which the service runs.
- `JDBC_URL`: The JDBC URL for the datasource.
- `JDBC_USERNAME`: The username for the datasource.
- `JDBC_PASSWORD`: The password for the datasource.

**Endpoints:**
- `/register`: User registration.
- `/authentication`: User login.



### Order-Service

The Order-Service handles order creation and management, with different endpoints available based on user roles.

**Environment Variables:**
- `PORT`: The port on which the service runs.
- `JDBC_URL`: The JDBC URL for the datasource.
- `JDBC_USERNAME`: The username for the datasource.
- `JDBC_PASSWORD`: The password for the datasource.

**Endpoints by Role:**

**ADMIN:**
- `/status`: Change order status.
- `/courier`: Create courier account.
- `/assign`: Assign parcel to a courier.
- `/couriers`: Get a list of all couriers.

**COURIER:**
- `/orders`: Get a list of orders assigned to the courier.
- `/order/status`: Change the status of an order.
- `/order/{id}`: Retrieve one order.

**USER:**
- `/order`: Create an order.
- `/order/destination`: Change order destination.
- `/order/{id}/cancel`: Cancel an order.
- `/order/{id}`: Retrieve one order.
- `/orders`: Get all orders for this user.

### Notification-Service

The Notification-Service sends email notifications to users about parcel status changes using Kafka.

**Kafka Consumer:**
- `localhost:29092`

**Environment Variables:**
- `PORT`: The port on which the service runs.
- `MAIL_HOST`: The mail server host.
- `MAIL_PORT`: The mail server port.
- `MAIL_USERNAME`: The mail server username.
- `MAIL_PASSWORD`: The mail server password.
- `JDBC_URL`: The JDBC URL for the datasource.
- `JDBC_USERNAME`: The username for the datasource.
- `JDBC_PASSWORD`: The password for the datasource.

## Getting Started

1. Set the environment variables for each microservice as specified above.
2. Start each microservice on their respective ports.
3. Access the OpenAPI documentation at `http://localhost:{PORT}/v1/api-docs/swagger-ui.html` for detailed API information.
4. Use the endpoints provided to interact with the application based on your role.

## P.S.

### Follow and Star the Repository

Thank you for your interest in my Parcel Delivery App!

[Follow me on GitHub](https://github.com/iturkan6)

[Star the repository](https://github.com/iturkan6/parcel-delivery-app)

