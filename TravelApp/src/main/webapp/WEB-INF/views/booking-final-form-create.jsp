<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Booking</title>
    <meta content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Create Booking</h1>

    <form action="${pageContext.request.contextPath}/bookings/final/create" method="post">
        <!-- Traveler selection -->
        <label for="travelerSelect">Traveler:</label>
        <select id="travelerSelect" name="travelerId" required>
            <c:forEach var="traveler" items="${travelers}">
                <option value="${traveler.id}">${traveler.name}</option>
            </c:forEach>
        </select>

        <!-- Hotel and Room selection -->
        <label for="hotelRoomSelect">Hotel and Room:</label>
        <select id="hotelRoomSelect" name="roomId" required>
            <c:forEach var="hotelWithRooms" items="${hotelsWithRooms}">
                <!-- Group rooms under hotel optgroup -->
                <optgroup label="${hotelWithRooms.id} - ${hotelWithRooms.name}">
                    <c:forEach var="room" items="${hotelWithRooms.rooms}">
                        <option value="${room.id}">
                            ${room.id} - ${room.roomType} - ${room.price}
                        </option>
                    </c:forEach>
                </optgroup>
            </c:forEach>
        </select>

        <!-- Check-in date -->
        <label for="checkInDate">Check-In Date:</label>
        <input type="date" id="checkInDate" name="checkInDate" required min="${todayDate}">

        <!-- Check-out date -->
        <label for="checkOutDate">Check-Out Date:</label>
        <input type="date" id="checkOutDate" name="checkOutDate" required min="${todayDate}">

        <!-- Error message display -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <!-- Submit button -->
        <button type="submit">Create Booking</button>
    </form>
</body>
</html>
