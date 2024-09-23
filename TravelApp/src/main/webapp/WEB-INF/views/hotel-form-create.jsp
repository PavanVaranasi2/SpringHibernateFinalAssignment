<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Hotel</title>

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

        input[type="text"], input[type="email"], input[type="number"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
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
    <h1>Create New Hotel</h1>

    <form action="${pageContext.request.contextPath}/hotels/create" method="post">

        <label for="name">Hotel Name:</label>
        <input type="text" id="name" name="name" required />

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required />

        <label for="receptionContactNumber">Reception Contact Number:</label>
        <input type="text" id="receptionContactNumber" name="receptionContactNumber" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required />

        <label for="starRating">Star Rating:</label>
        <input type="number" id="starRating" name="starRating" min="0" max="5" />

        <label for="totalRooms">Total Rooms:</label>
        <input type="number" id="totalRooms" name="totalRooms" />

        <label for="amenities">Amenities (comma separated):</label>
        <input type="text" id="amenities" name="amenities" />

        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea>

        <button type="submit">Create Hotel</button>
    </form>

    <a href="${pageContext.request.contextPath}/hotels/">Back to List</a>

</body>
</html>
