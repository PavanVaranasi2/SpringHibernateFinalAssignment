<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bookings List</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">

    <script type="text/javascript">
        function confirmDelete(bookingId) {
            if (confirm("Are you sure you want to delete this booking?")) {
                window.location.href = '${pageContext.request.contextPath}/bookings/final/delete/' + bookingId;
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
            <th>Hotel Name</th>
            <th>Room Details</th>
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
                <td>${booking.room.hotel.name}</td> <!-- Hotel Name -->
                <td>
                    Room No: ${booking.room.roomNumber},
                    Type: ${booking.room.roomType},
                    Capacity: ${booking.room.capacity}
                </td> <!-- Room Details -->
                <td>
                    <fmt:formatDate value="${booking.checkInDate}" pattern="dd/MM/yyyy" />
                </td>
                <td>
                    <fmt:formatDate value="${booking.checkOutDate}" pattern="dd/MM/yyyy" />
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/bookings/final/${booking.id}">View</a> |
                    <a href="javascript:void(0);" onclick="confirmDelete(${booking.id})">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/bookings/final/create">
    <button>Create New Booking</button>
</a>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/">
    <button>Back to Main Menu</button>
</a>

</body>
</html>
