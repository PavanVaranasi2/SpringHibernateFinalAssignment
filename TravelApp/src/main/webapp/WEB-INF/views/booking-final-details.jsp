<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking Details</title>
    <meta content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Booking Details</h1>

    <div class="details">
        <label for="travelerName">Traveler Name:</label>
        <input type="text" id="travelerName" value="${traveler.name}" readonly>

        <label for="travelerEmail">Traveler Email:</label>
        <input type="email" id="travelerEmail" value="${traveler.email}" readonly>

        <label for="travelerPhone">Traveler Phone:</label>
        <input type="tel" id="travelerPhone" value="${traveler.phoneNumber}" readonly>

        <label for="hotelName">Hotel Name:</label>
        <input type="text" id="hotelName" value="${hotel.name}" readonly>

        <label for="hotelLocation">Hotel Location:</label>
        <input type="text" id="hotelLocation" value="${hotel.location}" readonly>

        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" value="${room.roomType}" readonly>

        <label for="roomPrice">Room Price:</label>
        <input type="text" id="roomPrice" value="${room.price}" readonly>

        <label for="checkInDate">Check-In Date:</label>
        <input type="date" id="checkInDate" value="${booking.checkInDate}" readonly>

        <label for="checkOutDate">Check-Out Date:</label>
        <input type="date" id="checkOutDate" value="${booking.checkOutDate}" readonly>

    </div>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/bookings/final">
        <button>Back to List</button>
    </a>
</body>
</html>
