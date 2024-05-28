"use strict"

//Визначення девайсу

const isMobile = {
    Android: function () {
        return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function () {
        return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function () {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function () {
        return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function () {
        return navigator.userAgent.match(/IEMobile/i);
    },
    any: function () {
        return (
            isMobile.Android() ||
            isMobile.BlackBerry() ||
            isMobile.iOS() ||
            isMobile.Opera() ||
            isMobile.Windows());
    }
};

if (isMobile.any()) {
    document.body.classList.add('_touch');

    let menuArrows = document.querySelectorAll('.menu__arrow');
    if (menuArrows.length > 0) {
        for (let index = 0; index < menuArrows.length; index++) {
            const menuArrow = menuArrows[index];
            menuArrow.addEventListener("click", function (e) {
                menuArrow.parentElement.classList.toggle('_active');
            });
        }
    }

} else {
    document.body.classList.add('_pc');
}

// Меню бургер
const iconMenu = document.querySelector('.menu__icon')
const menuBody = document.querySelector('.menu__body')
if (iconMenu) {
    iconMenu.addEventListener("click", function (e) {
        document.body.classList.toggle('_lock');
        iconMenu.classList.toggle('_active');
        menuBody.classList.toggle('_active');
    });
}


// Fetch user data from 'api/users' endpoint
fetch('/api/users')
    .then(response => response.json())
    .then(users => {
        const userListContainer = document.querySelector('.block__text__sub-title-users');

        if (Array.isArray(users)) {
            users.forEach(user => {
                const userElement = document.createElement('div');
                userElement.classList.add('user-item');

                const userId = document.createElement('span');
                userId.classList.add('user-id');
                userId.textContent = `ID: ${user.id} | `;

                const usernameSpan = document.createElement('span');
                usernameSpan.classList.add('username'); // Add a class for styling

                const usernameText = document.createTextNode('Username: '); // Create a text node for the prefix
                const usernameAnchor = document.createElement('a');
                usernameAnchor.classList.add('inline-text'); // Add a class for styling
                usernameAnchor.textContent = user.username;
                usernameAnchor.href = `/users/user/${user.id}`;
                const randomText = document.createTextNode(' | ');

                usernameSpan.appendChild(usernameText); // Append the prefix text
                usernameSpan.appendChild(usernameAnchor); // Append the anchor
                usernameSpan.appendChild(randomText); // Append the anchor

                const roles = user.userRoles ? user.userRoles.map(role => role.name).join(', ') : '';

                const rolesElement = document.createElement('span');
                rolesElement.classList.add('roles');
                rolesElement.textContent = `Roles: ${roles}`;

                userElement.appendChild(userId);
                userElement.appendChild(usernameSpan); // Append the username span with the anchor inside
                userElement.appendChild(rolesElement);

                userListContainer.appendChild(userElement);
            });
        } else {
            console.error('Error fetching user data:', users);
        }
    })
    .catch(error => console.error('Error fetching user data:', error));



// Fetch menus data from 'api/menus' endpoint
fetch('/api/menus')
    .then(response => response.json())
    .then(menus => {
        const menuListContainer = document.querySelector('.block__text__sub-title-menus');

        if (Array.isArray(menus)) {
            menus.forEach(menu => {
                const menuElement = document.createElement('div');
                menuElement.classList.add('menu-item');

                const menuDate = document.createElement('span');
                menuDate.classList.add('menu-date');
                menuDate.textContent = `Date: ${menu.date}`;

                const breakfast = document.createElement('span');
                breakfast.classList.add('menu-item__dish');
                breakfast.textContent = `Breakfast: ${menu.breakfast ? menu.breakfast.name : 'N/A'}`;

                const lunch = document.createElement('span');
                lunch.classList.add('menu-item__dish');
                lunch.textContent = `Lunch: ${menu.lunch ? menu.lunch.name : 'N/A'}`;

                const dinner = document.createElement('span');
                dinner.classList.add('menu-item__dish');
                dinner.textContent = `Dinner: ${menu.dinner ? menu.dinner.name : 'N/A'}`;

                menuElement.appendChild(menuDate);
                menuElement.appendChild(breakfast);
                menuElement.appendChild(lunch);
                menuElement.appendChild(dinner);

                menuListContainer.appendChild(menuElement);
            });
        } else {
            console.error('Error fetching user data:', menus);
        }
    })
    .catch(error => console.error('Error fetching user data:', error));

// Fetch menus data from 'api/dishes' endpoint
fetch('/api/dishes')
    .then(response => response.json())
    .then(dishes => {
        const dishListContainer = document.querySelector('.block__text__sub-title-dishes');

        if (Array.isArray(dishes)) {
            dishes.forEach(dish => {
                const dishElement = document.createElement('div');
                dishElement.classList.add('dish-item');

                const dishName = document.createElement('span');
                dishName.classList.add('dish-name');
                dishName.textContent = `Name: ${dish.name}`;

                const ingredients = document.createElement('span');
                ingredients.classList.add('dish-ingredients');
                ingredients.textContent = `Ingredients: ${dish.ingredients ? dish.ingredients : 'N/A'}`;

                const category = document.createElement('span');
                category.classList.add('dish-category');
                category.textContent = `Category: ${dish.category ? dish.category : 'N/A'}`;

                dishElement.appendChild(dishName);
                dishElement.appendChild(ingredients);
                dishElement.appendChild(category);

                dishListContainer.appendChild(dishElement);
            });
        } else {
            console.error('Error fetching dish data:', dishes);
        }
    })
    .catch(error => console.error('Error fetching dish data:', error));


// Replace '123' with the actual dish ID you want to retrieve
const dishId = 15;

fetch(`/api/dishes/${dishId}`)
    .then(response => response.json())
    .then(dish => {
        if (dish) {
            const dishContainer = document.querySelector('.dish-details');

            const dishImage = document.createElement('img');
            dishImage.classList.add('dish-image');
            dishImage.src = dish.image ? dish.image : 'placeholder.jpg'; // Set default image if not available

            const dishName2 = document.createElement('span');
            dishName2.classList.add('dish-name');
            dishName2.textContent = `Name: ${dish.name}`;

            const ingredients2 = document.createElement('span');
            ingredients2.classList.add('dish-ingredients');
            ingredients2.textContent = `Ingredients: ${dish.ingredients ? dish.ingredients : 'N/A'}`;

            const category2 = document.createElement('span');
            category2.classList.add('dish-category');
            category2.textContent = `Category: ${dish.category ? dish.category : 'N/A'}`;

            const description2 = document.createElement('p');
            description2.classList.add('dish-description');
            description2.textContent = `Description: ${dish.description ? dish.description : 'N/A'}`;

            dishContainer.appendChild(dishImage);
            dishContainer.appendChild(dishName2);
            dishContainer.appendChild(ingredients2);
            dishContainer.appendChild(category2);
            dishContainer.appendChild(description2);
        } else {
            console.error('Dish not found with ID:', dishId);
        }
    })
    .catch(error => console.error('Error fetching dish data:', error));

