<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer une question </title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/questionRep.js"></script>
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
                            <form action="versCreerQuiz.do" method="GET">
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
            </br>
        </header>
        <div id="total" class="box">
            <h1> Liste des questions déjà crées </h1>
            <table class="liste">
                <tr> 
                    <th> Enoncé </th>
                    <th> Date de création de la question </th>
                    <th> Privée </th>
                </tr>
                <c:forEach var="question" items="${listQuestion}">
                    <tr>
                        <td>${question.enonce}</td>
                        <td>${question.datecreationquestion}</td>
                        <td>${question.estprivee}</td>
                        <td>
                            <form action="deleteQuestion.do" method="POST">
                                <input type ="hidden" name="code" value="${code}" />
                                <input type ="hidden" name="id" value="${question.questionid}" />
                                <button><img src="img/delete.png" alt="delete" height="20" /></button>
                            </form>
                            <form action="modifQuestion.do" method="POST">
                                <input type ="hidden" name="code" value="${code}" />
                                <input type ="hidden" name="id" value="${question.questionid}" />
                                <button><img src="img/edit.png" alt="delete" height="20" /></button>
                            </form>
                        </td>
                    </tr>                   
                </c:forEach>
            </table>
            <form action="versCreerQues.do" method="GET">
                <input type="hidden" name="code" value="${code}">
                <input type="submit" value="Créer une question">
            </form>
        </div>
    </body>
</html>