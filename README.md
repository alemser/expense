# expense
A demo project

This demo project main purpose is to manage expenses.

Basically, there is a Expense with a label (category) and a value. 
The user should be able to query for expenses grouping by categories and rank expenses by category.

# Quick start

Make sure you have JDK8 or higher and run the following command in the root folder of the project:

Linux/Mac

```./gradlew bootRun```

Windows

```gradlew bootRun```

# Creating a expense

```
curl -v POST http://localhost:8080/api/expenses \
-H "Content-Type: application/json" \
-d '{"amount": 35.20, "category": "Travel"}'
```

You should see the response headers with the location to retrieve the newly created expense and the ETag.
