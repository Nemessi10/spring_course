<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Favourite List</title>
</head>
<body>
<h1>Dish List</h1>
<#list favouriteMenus as favouriteMenu>
    <p><strong>ID:</strong></p>
    <p>${favouriteMenu.id}</p>
    <p><strong>Dish:</strong></p>
    <p>${favouriteMenu.dish}</p>
    <p><strong>Menu:</strong></p>
    <p>${favouriteMenu.menu}</p>
</#list>
</body>
</html>
