<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
<body>
<h1>User Details</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${userEntity.id}</td>
        <td>${userEntity.username}</td>
        <td>${userEntity.email}</td>
        <td>${userEntity.role}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
