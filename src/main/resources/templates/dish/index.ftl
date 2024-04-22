<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dish List</title>
</head>
<body>
<h1>Dish List</h1>
<#list dishes as dish>
    <p><strong>ID:</strong></p>
    <p>${dish.id}</p>
    <p><strong>Name:</strong></p>
    <p>${dish.name}</p>
    <p><strong>Image:</strong></p>
    <p>${dish.image}</p>
    <p><strong>Ingredients:</strong></p>
    <p>${dish.ingredients}</p>
    <p><strong>Recipe:</strong></p>
    <p>${dish.recipe}</p>
    <p><strong>Category:</strong></p>
    <p>${dish.category}</p>
</#list>
</body>
</html>
