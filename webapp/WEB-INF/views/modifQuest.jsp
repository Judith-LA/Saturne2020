<%-- 
    Document   : modifQuest
    Created on : 2 mars 2020, 13:00:34
    Author     : Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer une question </title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/questionRep.js"></script>
        <script type="text/javascript" src="js/utilities.js"></script>
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
            <h1> Modifier une question </h1>
            <table>
                <tr> 
                    <td>Question :</td>
                    <td><input name="enonceQues" type="text" value="${question.enonce}"/>
                        <input name="idQues" type="hidden" value="${question.questionid}"/>
                        <input name="type" type="hidden" value="${typeQues}"/></td>
                </tr>
                <tr>
                    <td>La question est elle privée ? </td>
                    <td>
                        <input type="radio" name="estPrivee" value=true checked="${question.estprivee}">
                        <label for="yes">Oui</label>
                        <input type="radio" name="estPrivee" value=false checked="${!question.estprivee}">
                        <label for="no">Non</label>
                    </td>
                </tr>
                <tr>
                    <td>Réponses:</td>
                    <td></td>
                </tr>
                <c:forEach var="qcmrep" items="${listReponses}">
                    <tr><td></td>
                        <td>
                        <c:if test="${type.repunique}">
                                <input name="idRepUni" type="hidden" value="${qcmrep.qcmrepid}"/>
                                <input name="enonceRepUni" type="text" value="${qcmrep.enonce}"/>
                                <c:choose>
                                    <c:when test="${qcmrep.reponseid.correcte}">
                                        <input type="radio" name="correctesUni" value=true checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="correctesUni" value=true>
                                    </c:otherwise>
                                </c:choose>
                                <button onclick="deleteRep(this);">X</button>
                        </c:if>
                        <c:if test="${!type.repunique}">
                                <input name="idRepMulti" type="hidden" value="${qcmrep.qcmrepid}"/>
                                <input name="enonceRepMulti" type="text" value="${qcmrep.enonce}"/>
                                <c:choose>
                                    <c:when test="${qcmrep.reponseid.correcte}">
                                        <input type="checkbox" name="correctesMulti" value=true checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="correctesMulti" value=true>
                                    </c:otherwise>
                                </c:choose>
                                <button onclick="deleteRep(this);">X</button>
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <c:if test="${type.repunique}">
                    <td name="QcmRepUni" >
                        <button onclick="ajouterRep(this,1);">Ajouter une réponse</button>
                    </td>
                    </c:if>
                    <c:if test="${!type.repunique}">
                    <td name="QcmRepMulti">
                        <button onclick="ajouterRep(this,2);">Ajouter une réponse</button>
                    </td>
                    </c:if>
                </tr>
                <tr>
                    <td>Mots-clés:</td>
                    <td>
                        <c:forEach var="motCle" items="${listMotsCles}">
                            <input name="motCle" type="text" value="${motCle.mot}"/>
                            <button onclick="deleteMotCle(this);">X</button>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button onclick="ajouterMotCle(this);">Ajouter un mot clé</button>
                    </td>
                </tr>
            </table>
            <button onClick="validerModif(this);">Valider les modifications</button>
        </div>
    </body>
</html>

