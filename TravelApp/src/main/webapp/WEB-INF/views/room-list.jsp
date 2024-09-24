<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Room List</title>
    <meta content="text/html; charset=utf-8" />

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
        }

        a {
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
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
    </style>
    <script type="text/javascript">
        function confirmDelete(roomId) {
            var confirmation = confirm("Are you sure you want to delete this room?");
            if (confirmation) {
                window.location.href = '${pageContext.request.contextPath}/rooms/delete/' + roomId;
            }
        }
    </script>
</head>
<body>
    <h1>List of Rooms</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Room Type</th>
                <th>Room Number</th>
                <th>Price</th>
                <th>Capacity</th>
                <th>Availability</th>
                <th>Facilities</th>
                <th>Hotel Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.id}</td>
                    <td>${room.roomType}</td>
                    <td>${room.roomNumber}</td>
                    <td>${room.price}</td>
                    <td>${room.capacity}</td>
                    <td>
                        <c:choose>
                            <c:when test="${room.availability}">Available</c:when>
                            <c:otherwise>Unavailable</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${room.facilities}</td>
                    <td>${room.hotel.name}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/rooms/${room.id}">View Details</a> |
                        <a href="${pageContext.request.contextPath}/rooms/update/${room.id}">Edit</a> |
                        <a href="javascript:void(0);" onclick="confirmDelete(${room.id})">Delete</a>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/rooms/create">
        <button>Create New Room</button>
    </a>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/">
        <button>Back to Main Menu</button>
    </a>
</body>
</html>
