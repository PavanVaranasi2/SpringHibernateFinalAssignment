<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hotel Details</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
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
