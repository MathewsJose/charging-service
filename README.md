Charging Service Application
============================

This application is designed to handle asynchronous communication between a REST API and an internal authorization service using RabbitMQ. It allows drivers to start a charging session at a specific station, and the authorization decision is processed asynchronously. The result is sent to a callback URL provided by the client.

* * * * *

**Features**
------------

1.  **REST API**:

    -   Accepts requests to start a charging session.

    -   Validates input and responds with an acknowledgment.

2.  **Asynchronous Processing**:

    -   Uses RabbitMQ to forward requests to an internal authorization service.

    -   Processes the request asynchronously and sends the result to a callback URL.

3.  **Persistence**:

    -   Stores authorization decisions in an H2 in-memory database for debugging purposes.

4.  **Scalability**:

    -   Protects the internal service from overload by using a message queue.

* * * * *

**Technologies Used**
---------------------

-   **Kotlin**: Primary programming language.

-   **Spring Boot**: Framework for building the REST API and integrating with RabbitMQ.

-   **RabbitMQ**: Message broker for asynchronous communication.

-   **Docker**: Containerization for easy deployment and running of the application.

* * * * *

**Getting Started**
-------------------

Follow these steps to set up and run the application.

* * * * *

### **Prerequisites**

1.  **Docker**: Install Docker from [here](https://docs.docker.com/get-docker/).

2.  **Docker Compose**: Install Docker Compose from [here](https://docs.docker.com/compose/install/).

3.  **Java 17**: Ensure Java 17 is installed on your system.

* * * * *

### **Step 1: Clone the Repository**

Clone the repository to your local machine:

```
git clone <repository-url>
cd charging-service
```
* * * * *

### **Step 2: Build the Application**

Build the application using Gradle:

```
./gradlew build
```

This will compile the Kotlin code and create a JAR file.

* * * * *

### **Step 3: Start the Application with Docker Compose**

Run the application and RabbitMQ using Docker Compose:

```
docker-compose up --build
```

This will:

1.  Build the Docker image for the Spring Boot application.

2.  Start the RabbitMQ container.

3.  Start the Spring Boot application container.

* * * * *

### **Step 4: Verify the Services**

1.  **Spring Boot Application**:

    -   Access the application at `http://localhost:8080`.

    -   Check the logs to ensure the application started successfully.

2.  **RabbitMQ**:

    -   Access the RabbitMQ management UI at `http://localhost:15672`.

    -   Log in with the default credentials:

        -   **Username**: `guest`

        -   **Password**: `guest`

* * * * *

Testing the Application with Postman
--------------------------------

Follow these steps to test the `/startChargingSession` endpoint using **Postman**:

### Step 1: Install Postman

If you don't have Postman installed, download and install it from [here](https://www.postman.com/downloads/).

### Step 2: Create a New Request

1.  Open Postman and create a new request.

2.  Set the request method to `POST`.

3.  Enter the URL: `http://localhost:8080/startChargingSession`.

### Step 3: Set the Request Body

1.  Go to the **Body** tab.

2.  Select **raw** and choose **JSON** from the dropdown.

3.  Enter the following JSON payload:

    ```
    {
      "stationId": "123e4567-e89b-12d3-a456-426614174000",
      "driverToken": "validDriverToken123",
      "callbackUrl": "http://example.com/callback"
    }
    ```

### Step 4: Send the Request

1.  Click the **Send** button.

2.  Verify the response in the **Response** section. You should see:

    ```
    {
      "status": "accepted",
      "message": "Request is being processed asynchronously. The result will be sent to the provided callback URL."
    }
    ```

* * * * *

### **Step 6: Stop the Application**

When you're done testing, stop the containers:

```
docker-compose down
```

* * * * *

**Application Flow**
--------------------

1.  **Request**:

    -   A driver sends a request to start a charging session at a specific station.

    -   The request includes:

        -   `stationId`: UUID of the station.

        -   `driverToken`: Token identifying the driver.

        -   `callbackUrl`: URL where the result will be sent.

2.  **Acknowledgment**:

    -   The API validates the request and responds with an acknowledgment.

3.  **Asynchronous Processing**:

    -   The request is forwarded to RabbitMQ.

    -   The internal authorization service processes the request and makes a decision.

* * * * *

**Folder Structure**
--------------------

```
charging-service/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── chargepoint/
│   │   │           ├── config/
│   │   │           │   └── RabbitMQConfig.kt
│   │   │           ├── controller/
│   │   │           │   └── ChargingSessionController.kt
│   │   │           ├── model/
│   │   │           │   ├── ChargingSessionRequest.kt
│   │   │           │   ├── AcknowledgmentResponse.kt
│   │   │           │   └── CallbackPayload.kt
│   │   │           ├── service/
│   │   │           │   └── AuthorizationServiceConsumer.kt
│   │   │           └── ChargingServiceApplication.kt
│   │   └── resources/
│   │       ├── application.yml
│   │       └── data.sql
│   └── test/
│       └── kotlin/
│           └── com/
│               └── chargepoint/
│                   └── ChargingServiceApplicationTests.kt
├── build.gradle.kts
├── settings.gradle.kts
├── Dockerfile
└── docker-compose.yml
```
* * * * *

**Contact**
-----------

For questions or feedback, please contact me at [mathewsfrj@gmail.com](mailto:mathewsfrj@gmail.com).