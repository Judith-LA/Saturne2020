<%-- 
    Document   : repondre.jsp
    Created on : 21 févr. 2020, 18:09:35
    Author     : Marion PGROU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Liste des tests</title>
        <meta charset="UTF-8"/> 
        <link href="css/repondre.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/evaluation.js"></script>
        <script type="text/javascript" src="js/session.js"></script>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
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
        <div id="gauche" class="box"> <p id="compte_a_rebours"> </p>
            <script type="text/javascript">

            var diff = 50;
            var date_evenement = new Date((new Date()).getTime() + diff*1000);

            function compte_a_rebours()

            {

                var compte_a_rebours = document.getElementById("compte_a_rebours");
                var date_actuelle = new Date();
                var total_secondes = (date_evenement - date_actuelle) / 1000;

                var prefixe = "Temps restant : ";


                if (total_secondes > 0)
                {
                    var jours = Math.floor(total_secondes / (60 * 60 * 24));
                    var heures = Math.floor((total_secondes - (jours * 60 * 60 * 24)) / (60 * 60));
                    minutes = Math.floor((total_secondes - ((jours * 60 * 60 * 24 + heures * 60 * 60))) / 60);
                    secondes = Math.floor(total_secondes - ((jours * 60 * 60 * 24 + heures * 60 * 60 + minutes * 60)));

                    var et = ":";
                    var mot_jour = ":";
                    var prefixe_heure = '';
                    var mot_heure = ":";
                    var prefixe_minute = '';
                    var mot_minute = ":";
                    var prefixe_seconde = '';


                    if (jours == 0)
                    {
                        jours = '';
                        mot_jour = '';
                    }

                    if (heures == 0)
                    {
                        heures = '';
                        mot_heure = '';
                    }

                    else if (heures < 10)
                    {
                        prefixe_heures = '0';
                    }        

                    if (minutes < 10)
                    {
                        prefixe_minute = '0'
                    }


                    if (secondes < 10)

                    {
                        prefixe_seconde = '0'
                        et = '';
                    }

                    if (minutes == 0 && heures == 0 && jours == 0)
                    {
                        et = "";
                    }


                    compte_a_rebours.innerHTML = prefixe + jours + mot_jour + prefixe_heure + heures + mot_heure + prefixe_minute + minutes + mot_minute + prefixe_seconde +secondes;
                }

                else
                {
                    finTest(${nombre});
                    console.log("miaou");
                }

                var actualisation = setTimeout("compte_a_rebours();", 1000);
            }

            compte_a_rebours();

            

            </script>
        </div>
        <div id="centre" class="box">
            <h2 style="text-align: center;">${test.nomQuiz}</h2>
            <form action="envRep.do" method="POST">
                <input type="hidden" value="${nombre}" name="nombre"/>
                <input type="hidden" value="${test.testId}" name="testId"/>
                <c:forEach var="question" items="${quesRep}">
                    <c:choose>
                        <c:when test= "${question.idQues%2==0}">
                            <table id="noir">
                        </c:when>
                        <c:otherwise>
                            <table>
                        </c:otherwise>    
                    </c:choose>
                                <tr>
                                    <td>${question.enonceQues}</td>
                                <input name="q${question.ordre}" type="hidden" value="${question.idQues}"/>
                                </tr>
                                 <c:forEach var="reponse" items="${question.listeReponses}">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test = "${question.repUni == true}">
                                                    <input name="r${question.idQues}" type="radio" value="${reponse.id}"/>
                                                    <label for="${question.idQues}">${reponse.ennonce}</label>
                                                </c:when>
                                                <c:otherwise>
                                                    <input name="r${question.idQues}" type="checkbox" value="${reponse.id}"/>
                                                    <label for="${question.idQues}">${reponse.ennonce}</label>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                        </table>
                </c:forEach> 
                <button type="submit">Valider</button>
            </form>
        </div>
    </body>
</html>

