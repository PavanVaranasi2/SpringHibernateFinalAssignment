<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .details {
            margin-bottom: 20px;
        }
        h3 {
            color: #333;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h2>Booking Details</h2>

<div class="details">
    <h3>Traveler Information</h3>
    <p><strong>Name:</strong> ${booking.traveler.name}</p>
    <p><strong>Email:</strong> ${booking.traveler.email}</p>
    <p><strong>Phone Number:</strong> ${booking.traveler.phoneNumber}</p>
</div>

<div class="details">
    <h3>Booking Information</h3>
    <p><strong>Booking ID:</strong> ${booking.id}</p>
    <p><strong>Check-In Date:</strong> ${booking.checkInDate}</p>
    <p><strong>Check-Out Date:</strong> ${booking.checkOutDate}</p>
</div>

<a href="${pageContext.request.contextPath}/bookings">Back to List</a>

</body>
</html>
