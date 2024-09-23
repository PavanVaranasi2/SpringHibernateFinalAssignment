<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Bookings List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin: 5px;
        }

        button:hover {
            background-color: #2980b9;
        }
    </style>

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
                    <a href="javascript:void(0);" onclick="confirmDelete(${booking.id})">Delete</a>
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
