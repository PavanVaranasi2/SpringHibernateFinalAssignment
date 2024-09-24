<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Room</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Update Room Information</h1>

    <form action="${pageContext.request.contextPath}/rooms/update" method="post">
        <input type="hidden" name="id" value="${room.id}" />

        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" name="roomType" value="${room.roomType}" required />

        <label for="roomNumber">Room Number:</label>
        <input type="number" id="roomNumber" name="roomNumber" value="${room.roomNumber}" required />

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" value="${room.price}" required />

        <label for="capacity">Capacity:</label>
        <input type="number" id="capacity" name="capacity" value="${room.capacity}" required />

        <!-- Radio buttons for availability (displayed inline) -->
        <label for="availability">Availability:</label>
        <div class="radio-group">
            <label><input type="radio" id="available" name="availability" value="true" ${room.availability ? 'checked' : ''} />Available</label>
            <label><input type="radio" id="unavailable" name="availability" value="false" ${!room.availability ? 'checked' : ''} />Unavailable</label>
        </div>

        <label for="facilities">Facilities:</label>
        <input type="text" id="facilities" name="facilities" value="${room.facilities}" required />

        <button type="submit">Update Room</button>
    </form>

    <a href="${pageContext.request.contextPath}/rooms/">Back to Room List</a>
</body>
</html>
