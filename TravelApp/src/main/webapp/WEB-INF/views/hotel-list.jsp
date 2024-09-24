<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hotel List</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">

    <script>
        function confirmDelete(hotelId) {
            var confirmAction = confirm("Are you sure you want to delete this hotel?");
            if (confirmAction) {
                window.location.href = "${pageContext.request.contextPath}/hotels/delete/" + hotelId;
            }
        }
    </script>
</head>
<body>

    <h1>List of Hotels</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Location</th>
                <th>Reception Contact</th>
                <th>Email</th>
                <th>Star Rating</th>
                <th>Total Rooms</th>
                <th>Amenities</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hotel" items="${hotels}">
                <tr>
                    <td>${hotel.id}</td>
                    <td>${hotel.name}</td>
                    <td>${hotel.location}</td>
                    <td>${hotel.receptionContactNumber}</td>
                    <td>${hotel.email}</td>
                    <td>${hotel.starRating}</td>
                    <td>${hotel.totalRooms}</td>
                    <td>${hotel.amenities}</td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/hotels/${hotel.id}">View Details</a> |
                        <a href="${pageContext.request.contextPath}/hotels/update/${hotel.id}">Edit</a> |
                        <button class="delete-button" onclick="confirmDelete(${room.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="create-hotel">
        <a href="${pageContext.request.contextPath}/hotels/create">
            <button>Create New Hotel</button>
        </a>
        <br/>
        <br/>
        <a href="${pageContext.request.contextPath}/">
            <button>Back to Main Menu</button>
        </a>
    </div>

</body>
</html>
