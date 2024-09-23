<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .details {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        .details label {
            display: block;
            font-weight: bold;
            margin-top: 10px;
        }
    </style>
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
