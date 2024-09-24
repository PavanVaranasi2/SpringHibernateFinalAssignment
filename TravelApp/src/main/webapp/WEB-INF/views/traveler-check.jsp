<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler Check</title>
    <meta content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h2>Enter Your Email and Phone Number</h2>

    <form action="${pageContext.request.contextPath}/checkTraveler" method="POST">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br/>

        <label for="phone">Phone Number:</label>
        <input type="tel" id="phone" name="phone" required><br/>

        <input type="submit" value="Check Traveler">
    </form>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

</body>
</html>
