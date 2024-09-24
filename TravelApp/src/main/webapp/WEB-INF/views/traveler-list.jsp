<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler List</title>
    <meta content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
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
