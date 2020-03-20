<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer une session de test</title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/session.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    </head>
    <body>
        <header>
            </br>
            <a href="https://www.ec-nantes.fr/version-francaise/">
                <img src="img/LogoCN_Blanc.png" style="height: 75px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            </a>
            <form action="index.do" method="GET">
                <input type="hidden" name="code" value="${code}">
                <h2><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  
                    <input type="submit" class="titre" value="Saturne"></h2>
            </form>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#" class="nom">${nom} ${prenom} &ensp;</a>
                        <ul class="sous">
                            <form action="versCreerQues.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Créer une question"></li>
                            </form>
                            <form action="#" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Créer un quiz"></li>
                            </form>
                            <form action="versCreerTest.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Créer une session d'évaluation"></li>
                            </form>
                            <form action="#" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Paramètres"></li>
                            </form>
                            <form action="disconnect.do" method="GET">
                                <input type="hidden" name="code" value="${code}">
                                <li><input type="submit" class="menu" value="Déconnexion"></li>
                            </form>
                        </ul>
                    </li>
                </ul>
            </nav>
        </header>
        <div id="gauche" class="box"> 
            <h1> Liste des sessions déjà crées </h1>
            <table class="liste">
                <tr> 
                    <th> Intitulé test </th>
                    <th> Groupe concerné </th>
                    <th> Date début </th>
                </tr>
                <c:forEach var="test" items="${listTests}">
                    <tr>
                        <td>${test.quizid.nomquiz}</td>
                        <td>${test.groupeid.nomgroupe}</td>
                        <td>${test.datedebuttest}</td>
                        <td>
                            <form action="delete.do" method="POST">
                                <input type ="hidden" name="id" value="${test.testid}" />
                                <button><img src="img/delete.png" alt="delete" height="20" /></button>
                            </form>
                        </td>
                    </tr>                    
                </c:forEach>
            </table>
        </div>
        <div id="centre" class="box">
            <h1> Créer une session de tests </h1>
            <table>
                <tr> 
                    <td>Choisissez le quiz qui sera utilisé :</td>
                    <td><select name="quiz"> 
                            <c:forEach var="quiz" items="${listQuizs}">
                                    <option value="${quiz.quizid}">${quiz.nomquiz}</option>
                            </c:forEach>
                </select></td>
                </tr>
                <tr> 
                    <td>Choisissez la date de début de disponibilité :</td>
                    <td><input name="dateDeb" type="date"/></td>
                    <td><input name="heureDeb" type="time"/></td>
                </tr>
                <tr> 
                    <td>Choisissez la date de fin de disponibilité :</td>
                    <td><input name="dateFin" type="date"/></td>
                    <td><input name="heureFin" type="time"/></td>
                </tr>
                <tr> 
                    <td>Quelle est la durée du test ?</td>
                    <td><input name="duree" type="time"/></td>
                </tr>
                <tr> 
                    <td>Quelle notation voulez-vous utiliser ?</td>
                    <td><select name="notation"> 
                            <c:forEach var="notation" items="${listNotations}">
                                <option value="${notation.notationid}">${notation.libelle}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr> 
                    <td>Quel(s) groupe(s) doit(devront) passer ce test ?</td>
                    <td><select name="groupe"> 
                            <c:forEach var="groupe" items="${listGroupes}">
                                <c:if test="${groupe.estvalide}" >
                                    <option value="${groupe.groupeid}">${groupe.nomgroupe}</option>
                                </c:if>
                                
                            </c:forEach>
                </select></td>
                </tr>
            </table>
            <button onClick="creerTest(this);">Continuer et choisir le nombre de points par question</button>
        </div>
    </body>
</html>