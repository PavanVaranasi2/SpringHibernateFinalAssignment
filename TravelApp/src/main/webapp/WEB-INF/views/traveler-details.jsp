<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler Details</title>
    <meta content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <h2>Traveler Details</h2>
    <p>ID: ${traveler.id}</p>
    <p>Name: ${traveler.name}</p>
    <p>Email: ${traveler.email}</p>
    <p>Phone Number: ${traveler.phoneNumber}</p>
    <a href="/TravelApp/traveler/list">Back to List</a>
</body>
</html>
