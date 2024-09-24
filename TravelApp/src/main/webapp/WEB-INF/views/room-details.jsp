<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Room Details</title>
    <meta content="text/html; charset=utf-8" />

    <!-- Internal CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1, h2 {
            color: #333;
            text-align: center;
        }

        p {
            font-size: 16px;
            color: #555;
        }

        strong {
            color: #333;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: white;
            background-color: #3498db;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }

        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

    <h1>Room Details</h1>

    <div class="container">
        <h2>Room: ${room.roomType}</h2>
        <p><strong>Room Number:</strong> ${room.roomNumber}</p>
        <p><strong>Price:</strong> $${room.price}</p>
        <p><strong>Capacity:</strong> ${room.capacity}</p>
        <p><strong>Availability:</strong>
            <c:choose>
                <c:when test="${room.availability}">Available</c:when>
                <c:otherwise>Unavailable</c:otherwise>
            </c:choose>
        </p>
        <p><strong>Facilities:</strong> ${room.facilities}</p>

        <h2>Hotel Details</h2>
        <p><strong>Hotel Name:</strong> ${room.hotel.name}</p>
        <p><strong>Location:</strong> ${room.hotel.location}</p>
        <p><strong>Contact Number:</strong> ${room.hotel.receptionContactNumber}</p>
        <p><strong>Email:</strong> ${room.hotel.email}</p>
        <p><strong>Star Rating:</strong> ${room.hotel.starRating}</p>
        <p><strong>Amenities:</strong> ${room.hotel.amenities}</p>

        <a href="${pageContext.request.contextPath}/rooms">Back to Room List</a>
    </div>

</body>
</html>
