<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler List</title>
    <meta content="text/html; charset=utf-8" />
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 10px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
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
    </style>
</head>
<body>
    <h2>Traveler List</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="traveler" items="${travelers}">
            <tr>
                <td>${traveler.id}</td>
                <td>${traveler.name}</td>
                <td>${traveler.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/traveler/details/${traveler.id}">Details</a> |
                    <a href="${pageContext.request.contextPath}/traveler/showFormForUpdate/${traveler.id}">Update</a> |
                    <a href="${pageContext.request.contextPath}/traveler/delete/${traveler.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/TravelApp/traveler/showFormForAdd">
        <button>Create New Traveler</button>
    </a>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/">
        <button>Back to Main Menu</button>
    </a>
</body>
</html>
