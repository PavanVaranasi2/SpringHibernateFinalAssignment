<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Room</title>

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
            background-color: #f9f9f9;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"], input[type="number"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .radio-group {
            margin-bottom: 20px;
        }

        .radio-group label {
            display: inline-block;
            margin-right: 15px;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        button {
            background-color: #3498db;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #2980b9;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
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
