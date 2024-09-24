<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler Form</title>
    <meta content="text/html; charset=utf-8" />
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            margin: 20px 0;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Create Traveler</h2>
    <form:form action="${pageContext.request.contextPath}/traveler/saveTraveler" modelAttribute="traveler">
        <!-- Hidden field for traveler ID (used for update operations) -->
        <form:hidden path="id"/>

        <div>
            <label for="name">Name:</label>
            <form:input path="name" />
        </div>

        <div>
            <label for="email">Email:</label>
            <form:input path="email" />
        </div>

        <div>
            <label for="phoneNumber">Phone Number:</label>
            <form:input path="phoneNumber" />
        </div>

        <div>
            <input type="submit" value="Save Traveler" />
        </div>
    </form:form>

    <a href="${pageContext.request.contextPath}/traveler/list">Back to List</a>
</body>
</html>
