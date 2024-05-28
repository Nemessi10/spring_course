<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #212121;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            gap: 10px;
            background-color: rgb(241, 241, 130);
            padding: 60px;
            padding-inline: 28px;
            border-radius: 20px;
            -webkit-box-shadow: 4px 4px rgb(0, 2, 65);
            box-shadow: 4px 4px rgb(0, 2, 65);
            font-family: cursive;
        }

        .heading {
            font-size: 25px;
            text-align: center;
            font-weight: 600;
            font-family: cursive;
        }

        .form button {
            /* align-self: flex-end; */
            padding: 10px;
            border-radius: 10px;
            margin-top: 20px;
            background-color: rgb(241, 241, 130);
            -webkit-box-shadow: 2px 3px rgb(0, 2, 65);
            box-shadow: 2px 3px rgb(0, 2, 65);

            color: rgb(0, 0, 0);
            font-size: medium;
            font-weight: 600;
            -webkit-transition: 400ms;
            transition: 400ms;
            font-family: cursive;
        }

        .form button:hover {
            background-color: rgb(235, 255, 59);
            -webkit-transition: 400ms;
            transition: 400ms;
            cursor: pointer;
        }

        .input {
            padding: 10px;
            width: 280px;
            border-radius: 10px;
            border-style: double;
            border-color: black;
            font-size: 15px;
            -webkit-box-shadow: 2px 3px rgb(0, 2, 65);
            box-shadow: 2px 3px rgb(0, 2, 65);

            font-family: cursive;
        }

        .register,
        a {
            padding-top: 10px;
            color: rgba(0, 0, 0, 0.994);
            font-family: cursive;
            text-decoration: none;
            -webkit-transition: 400ms;
            transition: 400ms;
        }
        a:hover {
            color: rgba(0, 0, 0, 0.703);
            text-decoration: underline;
            -webkit-transition: 400ms;
            transition: 400ms;
        }

        .Username,
        .Password {
            font-size: 16px;
            font-family: cursive;
        }

    </style>
</head>
<body>
<form class="form" action="/auth/login" method="post">
    <span class="heading">Log In</span>

    <span class="Username">Username</span>
    <input placeholder="Enter Username" type="text" name="username" class="input" />
    <span class="Password">Password</span>
    <input placeholder="Enter Password" type="password" name="password" class="input" />
    <span class="register">Don't have an account? <a href="/auth/register">Sign up</a></span>
    <button type="submit">Let's go!</button>
</form>

</body>
</html>