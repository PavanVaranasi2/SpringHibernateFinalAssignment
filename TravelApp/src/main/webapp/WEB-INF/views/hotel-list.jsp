<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hotel List</title>
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

        .actions a {
            margin-right: 10px;
        }

        button {
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #2980b9;
        }

        .create-hotel {
            text-align: center;
        }
    </style>

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
                        <a href="javascript:void(0);" onclick="confirmDelete(${hotel.id})">Delete</a>
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
