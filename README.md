# expense
A demo project

This demo project main purpose is to manage expenses.

Basically, the application allow users to create an expense informing a label (category) an amount and a date. 
The application is not complete, and the idea is to allow the users to query for expenses grouping by category.

```Currently the application ```

# Quick start

Make sure you have JDK8 or higher and run the following command in the root folder of the project:

Linux/Mac

```./gradlew bootRun```

Windows

```gradlew bootRun```

# Creating an expense

```
curl -v POST http://localhost:8080/api/expenses \
-H "Content-Type: application/json" \
-d '{"amount": 35.20, "date": "2020-12-17T20:42:20.845+01:00", "category": "Travel"}'
```

You should see the response headers with the location to retrieve the newly created expense and the ETag.

# Docker

Build the project

```
./gradlew clean build
```

Create the image

```
docker build --rm -t expense .
```

Run the container

```
docker run --rm -p 8080:8080 expense
```
