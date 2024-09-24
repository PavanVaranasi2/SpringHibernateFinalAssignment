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
        <label>Traveler Name:</label>
        <p>${traveler.name}</p>

        <label>Traveler Email:</label>
        <p>${traveler.email}</p>

        <label>Traveler Phone:</label>
        <p>${traveler.phoneNumber}</p>

        <label>Hotel Name:</label>
        <p>${hotel.name}</p>

        <label>Hotel Location:</label>
        <p>${hotel.location}</p>

        <label>Room Type:</label>
        <p>${room.roomType}</p>

        <label>Room Price:</label>
        <p>${room.price}</p>

        <label>Check-In Date:</label>
        <p>${booking.checkInDate}</p>

        <label>Check-Out Date:</label>
        <p>${booking.checkOutDate}</p>
    </div>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/bookings/final">
        <button>Back to List</button>
    </a>
</body>
</html>
