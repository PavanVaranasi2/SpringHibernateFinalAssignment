<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Booking</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        select, input[type="date"], button {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #2980b9;
        }
        .error-message {
            color: red;
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
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
