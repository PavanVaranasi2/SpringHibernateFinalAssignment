<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create Booking</title>
    <meta content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">

    <script type="text/javascript">
        // Create a JavaScript array from the travelers list
        var travelers = [
            <c:forEach var="traveler" items="${travelers}">
                {
                    id: "${traveler.id}",
                    name: "${traveler.name}",
                    email: "${traveler.email}"
                },
            </c:forEach>
        ];

        function updateTravelerDetails() {
            var selectedTravelerId = document.getElementById("travelerSelect").value;

            // Find the selected traveler by ID
            var selectedTraveler = travelers.find(function(traveler) {
                return traveler.id == selectedTravelerId;
            });

            // If the traveler is found, update the form fields
            if (selectedTraveler) {
                document.getElementById("travelerName").value = selectedTraveler.name;
                document.getElementById("travelerEmail").value = selectedTraveler.email;
            } else {
                document.getElementById("travelerName").value = "";
                document.getElementById("travelerEmail").value = "";
            }
        }
    </script>

</head>
<body>

<h2>Create Booking</h2>

<form action="${pageContext.request.contextPath}/bookings/create" method="POST">

    <!-- Traveler selection dropdown -->
    <label for="travelerSelect">Traveler:</label>
    <select id="travelerSelect" name="travelerId" onchange="updateTravelerDetails()" required>
        <option value="">Select a Traveler</option>
        <c:forEach var="traveler" items="${travelers}">
            <option value="${traveler.id}">${traveler.name}</option>
        </c:forEach>
    </select>

    <!-- Traveler details (read-only fields) -->
    <label for="travelerName">Traveler Name:</label>
    <input type="text" id="travelerName" name="travelerName" readonly>

    <label for="travelerEmail">Traveler Email:</label>
    <input type="text" id="travelerEmail" name="travelerEmail" readonly>

    <!-- Booking dates -->
    <label for="checkInDate">Check-In Date:</label>
    <input type="date" id="checkInDate" name="checkInDate" required>

    <label for="checkOutDate">Check-Out Date:</label>
    <input type="date" id="checkOutDate" name="checkOutDate" required>

    <input type="submit" value="Create Booking">

</form>

<a href="${pageContext.request.contextPath}/bookings">Back to List</a>

</body>
</html>
