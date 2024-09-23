<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Traveler Check</title>
    <style>
        input[type="text"], input[type="tel"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Enter Your Email and Phone Number</h2>

    <form action="${pageContext.request.contextPath}/checkTraveler" method="POST">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br/>

        <label for="phone">Phone Number:</label>
        <input type="tel" id="phone" name="phone" required><br/>

        <input type="submit" value="Check Traveler">
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

</body>
</html>
