<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to the Application</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #3498db;
            padding: 10px 0;
            text-align: center;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            padding: 10px 20px;
            display: inline-block;
            font-size: 18px;
        }

        .navbar a:hover {
            background-color: #2980b9;
            border-radius: 5px;
        }

        .container {
            padding: 20px;
            text-align: center;
        }

        h2 {
            color: #333;
        }
    </style>
</head>
<body>

    <div class="navbar">
        <a href="${pageContext.request.contextPath}/traveler/list">Check Traveler List</a>
        <!-- <a href="${pageContext.request.contextPath}/bookings/">Check Booking List</a> -->
        <a href="${pageContext.request.contextPath}/bookings/final">Check Final Booking List</a>
        <a href="${pageContext.request.contextPath}/hotels/">Check Hotel List</a>
        <a href="${pageContext.request.contextPath}/rooms/">Check Rooms List</a>
    </div>

    <div class="container">
        <h2>Hello World!</h2>
        <p>Welcome to the application. Use the navigation bar to access different sections.</p>
    </div>

</body>
</html>
