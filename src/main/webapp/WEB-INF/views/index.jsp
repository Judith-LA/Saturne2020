<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
        <title>Connexion Saturne</title>
        <meta charset="UTF-8"/> 
        <link rel="stylesheet" type="text/css" media="screen" href="css/login.css"/>
    </head>
    <body>
        <header>
            <img src="img/LogoCN.jpg" style="height: 55px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            <h1><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  Saturne</h1>
        </header>
            <form action="index.do" method="POST" id="centre" class="box">
                <h2>Identifiants</h2>
                <p><input type="login" name="user" placeholder="Login"></p>
                <p><input type="password" name="password" placeholder="Password"></p>
                <p><button type="submit">Login</button></p>
            </form>
        <footer></footer>
    </body>
</html>

