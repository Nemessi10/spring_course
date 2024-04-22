<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu List</title>
</head>
<body>
<h1>Menu List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Breakfast</th>
        <th>Lunch</th>
        <th>Dinner</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <#list menus as menu>
        <tr>
            <td>${menu.id}</td>
            <td>${menu.userEntity.username}</td>
            <td>${menu.breakfast}</td>
            <td>${menu.lunch}</td>
            <td>${menu.dinner}</td>
            <td>${menu.date}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
