<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Hotel</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
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
