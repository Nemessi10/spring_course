<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #212121;
            color: #f9f9f9;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #444;
            text-align: left;
        }
        th {
            background-color: #333;
        }
        tr:nth-child(even) {
            background-color: #2c2c2c;
        }
    </style>
</head>
<body>
<h1>User Details</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Roles</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>
                <#list user.userRoles as role>
                    ${role.name} <#if role_has_next>, </#if>
                </#list>
            </td>
        </tr>
    </tbody>
</table>
</body>
</html>
