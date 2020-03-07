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
                            <li><a href="#">Déconnexion</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            </br>
        </header>
        <div>
	<h2>ENJOY Teachers!</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Login</th>
            </tr>
            <c:forEach var="pers" items="${listPers}">
                <tr>
                    <td>${pers.personneid}</td>
                    <td>${pers.nom}</td>
                    <td>${pers.prenom}</td>
                    <td>${pers.login}</td>
                </tr>
            </c:forEach>
        </table>
        </br>
        <table>
            <tr>
                <th>Id</th>
                <th>Date de début de connexion</th>
                <th>Date de fin de connexion</th>
                <th>Adresse IP</th>
            </tr>
            <c:forEach var="co" items="${listCo}">
                <tr>
                    <td>${co.connexionid}</td>
                    <td>${co.debutconnexion}</td>
                    <td>${co.finconnexion}</td>
                    <td>${co.ip}</td>
                </tr>
            </c:forEach>
        </table>
        <table>
            <c:forEach var="role" items="${listRole}">
                <tr>
                    <td>${role.libelle}</td>
                </tr>
            </c:forEach>
        </table>
        <footer></footer>
    </body>
</html>