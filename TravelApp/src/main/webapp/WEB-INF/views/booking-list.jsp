<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bookings List</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">

    <script type="text/javascript">
        function confirmDelete(bookingId) {
            if (confirm("Are you sure you want to delete this booking?")) {
                window.location.href = '${pageContext.request.contextPath}/bookings/delete/' + bookingId;
            }
        }
    </script>
</head>
<body>

<h2>All Bookings</h2>

<table>
    <thead>
        <tr>
            <th>Booking ID</th>
            <th>Traveler Name</th>
            <th>Check-In Date</th>
            <th>Check-Out Date</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.traveler.name}</td>
                <td>${booking.checkInDate}</td>
                <td>${booking.checkOutDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/bookings/${booking.id}">View</a> |
                    <a href="${pageContext.request.contextPath}/bookings/update/${booking.id}">Edit</a> |
                    <button class="delete-button" onclick="confirmDelete(${room.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/bookings/create">
    <button>Create New Booking</button>
</a>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/">
    <button>Back to Main Menu</button>
</a>

</body>
</html>
