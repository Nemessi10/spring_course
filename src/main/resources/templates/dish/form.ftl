<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Dish</title>
</head>
<body>
<h1>Create New Dish</h1>
<form method="post" enctype="multipart/form-data" action="/new">
    <input type="hidden" name="${_csrf.tokenName}" value="${_csrf.token}">

    <label for="name">Dish Name:</label>
    <input type="text" id="name" name="dishDto.name" required><br>

    <label for="ingredients">Ingredients (comma separated):</label>
    <textarea id="ingredients" name="dishDto.ingredients" rows="4" required></textarea><br>

    <label for="recipe">Recipe:</label>
    <textarea id="recipe" name="dishDto.recipe" rows="10" required></textarea><br>

    <label for="category">Category:</label>
    <select id="category" name="dishDto.category" required>
        <option value="MAIN_COURSE">Main Course</option>
        <option value="SIDE_DISH">Side Dish</option>
        <option value="DESSERT">Dessert</option>
    </select><br>

    <label for="imageFile">Image:</label>
    <input type="file" id="imageFile" name="imageFile" accept="image/*"><br>

    <button type="submit">Create Dish</button>
</form>
</body>
</html>
