CREATE TABLE roles (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       user_id INT
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role_id INT,
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE user_roles (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            role_id INT,
                            user_id BIGINT,
                            FOREIGN KEY (role_id) REFERENCES roles(id),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE dishes (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        image VARCHAR(255),
                        ingredients TEXT,
                        recipe TEXT,
                        category VARCHAR(255)
);

CREATE TABLE menus (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT,
                       breakfast_id BIGINT,
                       lunch_id BIGINT,
                       dinner_id BIGINT,
                       date DATE,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (breakfast_id) REFERENCES dishes(id),
                       FOREIGN KEY (lunch_id) REFERENCES dishes(id),
                       FOREIGN KEY (dinner_id) REFERENCES dishes(id)
);

CREATE TABLE favourite_menus (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 user_id BIGINT,
                                 menu_id BIGINT,
                                 FOREIGN KEY (user_id) REFERENCES users(id),
                                 FOREIGN KEY (menu_id) REFERENCES menus(id)
);

CREATE TABLE favourite_dishes (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  user_id BIGINT,
                                  dish_id BIGINT,
                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                  FOREIGN KEY (dish_id) REFERENCES dishes(id)
);
