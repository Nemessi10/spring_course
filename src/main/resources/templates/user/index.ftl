<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <#list userEntities as userEntity>
        <tr>
            <td>${userEntity.id}</td>
            <td>${userEntity.username}</td>
            <td>${userEntity.email}</td>
            <td>${userEntity.role}</td>
            <td>
                <form action="/userEntities/${userEntity.id}" method="post">
                    <button type="submit" onclick="return confirm('Are you sure you want to delete this userEntity?')">Delete User</button>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
