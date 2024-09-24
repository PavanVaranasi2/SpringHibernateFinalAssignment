<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Room</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Create New Room</h1>

    <form action="${pageContext.request.contextPath}/rooms/create" method="post">

        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" name="roomType" required />

        <label for="roomNumber">Room Number:</label>
        <input type="number" id="roomNumber" name="roomNumber" required />

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" required />

        <label for="capacity">Capacity:</label>
        <input type="number" id="capacity" name="capacity" required />

        <!-- Radio buttons for availability -->
        <label for="availability">Availability:</label>
        <div class="radio-group">
            <label><input type="radio" id="available" name="availability" value="true" checked />Available</label>
            <label><input type="radio" id="unavailable" name="availability" value="false" />Unavailable</label>
        </div>

        <label for="facilities">Facilities:</label>
        <input type="text" id="facilities" name="facilities" required />

        <!-- Dropdown for selecting hotel -->
        <label for="hotelId">Hotel:</label>
        <select id="hotelId" name="hotelId" required>
            <option value="">Select Hotel</option>
            <c:forEach var="hotel" items="${hotels}">
                <option value="${hotel.id}">${hotel.name} (${hotel.location})</option>
            </c:forEach>
        </select>

        <button type="submit">Create Room</button>
    </form>

    <a href="${pageContext.request.contextPath}/rooms/">Back to Room List</a>
</body>
</html>
