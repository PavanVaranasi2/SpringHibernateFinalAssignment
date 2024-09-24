<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Hotel</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h1>Update Hotel Information</h1>

    <form action="${pageContext.request.contextPath}/hotels/update" method="post">
        <input type="hidden" name="id" value="${hotel.id}" />

        <label for="name">Hotel Name:</label>
        <input type="text" id="name" name="name" value="${hotel.name}" required />

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" value="${hotel.location}" required />

        <label for="receptionContactNumber">Reception Contact Number:</label>
        <input type="text" id="receptionContactNumber" name="receptionContactNumber" value="${hotel.receptionContactNumber}" required />

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${hotel.email}" required />

        <label for="starRating">Star Rating:</label>
        <input type="number" id="starRating" name="starRating" value="${hotel.starRating}" min="0" max="5" />

        <label for="totalRooms">Total Rooms:</label>
        <input type="number" id="totalRooms" name="totalRooms" value="${hotel.totalRooms}" />

        <label for="amenities">Amenities (comma separated):</label>
        <input type="text" id="amenities" name="amenities" value="${hotel.amenities}" />

        <label for="description">Description:</label>
        <textarea id="description" name="description">${hotel.description}</textarea>

        <button type="submit">Update Hotel</button>
    </form>

    <a href="${pageContext.request.contextPath}/hotels/">Back to list</a>
</body>
</html>
