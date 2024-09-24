<!DOCTYPE html>
<html lang="en">
<head>
    <title>Traveler Details</title>
    <meta content="text/html; charset=utf-8" />
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        p {
            font-size: 16px;
            line-height: 1.5;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
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
