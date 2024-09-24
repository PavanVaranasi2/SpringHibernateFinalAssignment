<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Booking</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>

<h2>Update Booking</h2>

<form action="${pageContext.request.contextPath}/bookings/update/${booking.id}" method="POST">

    <!-- Hidden input for traveler ID -->
    <input type="hidden" name="travelerId" value="${booking.traveler.id}">

    <!-- Traveler Information (Read-only) -->
    <h3>Traveler Information</h3>
    <p><strong>Name:</strong> ${booking.traveler.name}</p>
    <p><strong>Email:</strong> ${booking.traveler.email}</p>
    <p><strong>Phone Number:</strong> ${booking.traveler.phoneNumber}</p>

    <!-- Booking Information -->
    <h3>Booking Information</h3>
    <label for="checkInDate">Check-In Date:</label>
    <input type="date" id="checkInDate" name="checkInDate" value="${booking.checkInDate}" required>

    <br />

    <label for="checkOutDate">Check-Out Date:</label>
    <input type="date" id="checkOutDate" name="checkOutDate" value="${booking.checkOutDate}" required>

    <br />
    <input type="submit" value="Update Booking">

</form>

<a href="${pageContext.request.contextPath}/bookings">Back to List</a>

</body>
</html>
