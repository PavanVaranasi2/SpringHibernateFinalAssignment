<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hotel Details</title>
    <meta content="text/html; charset=utf-8" />

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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: white;
            background-color: #3498db;
            padding: 10px 20px;
            border-radius: 5px;
        }

        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <h1>Hotel Details</h1>

    <h2>${hotel.name}</h2>
    <p><strong>Location:</strong> ${hotel.location}</p>
    <p><strong>Reception Contact:</strong> ${hotel.receptionContactNumber}</p>
    <p><strong>Email:</strong> ${hotel.email}</p>
    <p><strong>Star Rating:</strong> ${hotel.starRating}</p>
    <p><strong>Total Rooms:</strong> ${hotel.totalRooms}</p>
    <p><strong>Amenities:</strong> ${hotel.amenities}</p>

    <h2>Rooms Available</h2>
    <table>
        <thead>
            <tr>
                <th>Room Type</th>
                <th>Room Number</th>
                <th>Price</th>
                <th>Capacity</th>
                <th>Availability</th>
                <th>Facilities</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.roomType}</td>
                    <td>${room.roomNumber}</td>
                    <td>${room.price}</td>
                    <td>${room.capacity}</td>
                    <td>
                        <c:choose>
                            <c:when test="${room.availability}">Available</c:when>
                            <c:otherwise>Unavailable</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${room.facilities}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/hotels/">Back to Hotel List</a>
</body>
</html>
