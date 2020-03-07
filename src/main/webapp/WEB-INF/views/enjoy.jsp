<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
	<title>Ca marche !</title>
        <meta charset="UTF-8"/> 
        <link rel="stylesheet" type="text/css" media="screen" href="css/main.css"/>
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    </head>
    <body>
        <header>
            </br>
            <img src="img/LogoCN.jpg" style="height: 55px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            <h1><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  Saturne</h1>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#">Nom Prénom &ensp;</a>
                        <ul class="sous">
                            <li><a href="#">Résultats des évaluations</a></li>
                            <li><a href="#">Historique des auto-évaluations</a></li>
                            <li><a href="#">Paramètres</a></li>
                            <form method="POST" action="Disconnect.do">
                                <input type="hidden" name="code" id="code" value="${code}"/>
                                <li><a href="#">Déconnexion</a></li>
                            </form>
                        </ul>
                    </li>
                </ul>
            </nav>
            </br>
        </header>
	<h2>ENJOY Students!</h2>
        <footer></footer>
    </body>
</html>