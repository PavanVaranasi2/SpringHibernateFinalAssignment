<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler Form</title>
    <meta content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h2>Create Traveler</h2>
    <form:form action="${pageContext.request.contextPath}/traveler/saveTraveler" modelAttribute="traveler">
        <!-- Hidden field for traveler ID (used for update operations) -->
        <form:hidden path="id"/>

        <div>
            <label for="name">Name:</label>
            <form:input path="name" />
        </div>

        <div>
            <label for="email">Email:</label>
            <form:input path="email" />
        </div>

        <div>
            <label for="phoneNumber">Phone Number:</label>
            <form:input path="phoneNumber" />
        </div>

        <div>
            <input type="submit" value="Save Traveler" />
        </div>
    </form:form>

    <a href="${pageContext.request.contextPath}/traveler/list">Back to List</a>
</body>
</html>
