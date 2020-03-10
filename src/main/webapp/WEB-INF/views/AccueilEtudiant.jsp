<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
	<title>Ca marche !</title>
        <meta charset="UTF-8"/> 
        <link rel="stylesheet" type="text/css" media="screen" href="css/main.css"/>
    </head>
    <body>
        <header>
            </br>
            <a href="https://www.ec-nantes.fr/version-francaise/">
                <img src="img/LogoCN_Blanc.png" style="height: 75px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            </a>
            <a href="index.do">
                <h1><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  Saturne</h1>
            </a>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#">${nom} ${prenom} &ensp;</a>
                        <ul class="sous">
                            <li><a href="#">Historique des auto-évaluations</a></li>
                            <li><a href="#">Paramètres</a></li>
                            <li><a href="disconnect.do">Déconnexion</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            </br>
        </header>
        <div id="haut">
            <h2>Évaluations à compléter</h2>
            <table>
                <tr>
                    <th style="width: 100px">Nom</th>
                    <th style="width: 150px">Date de début</th>
                    <th style="width: 150px">Date de fin</th>
                    <th style="width: 80px">Durée</th>
                    <th style="width: 40px"></th>
                </tr>
                <c:forEach var="test" items="${listTests}">
                    <td>${test[1]}</td>
                    <td>${test[2]}</td>
                    <td>${test[3]}</td>
                    <td>${test[4]}</td>
                    <td>
                        <form action="repondre.do" method="POST">
                            <input type="hidden" name="id" value="${test[0]}">
                            <input type="hidden" name="personneId" value="${persId}">
                            <input type="hidden" name="code" value="${code}">
                            <button><img src="img/edit.png" height="20"></button>
                        </form>
                    </td>
                </c:forEach>
            </table>
        </div>
        <div id="bas">
            <h2>S'entraîner</h2>
        </div>
        <div id="droite">
            <h2>Résultats des dernières évaluations</h2>
        </div>
    </body>
</html>