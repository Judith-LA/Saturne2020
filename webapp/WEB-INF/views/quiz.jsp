<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Créer un quiz </title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/quiz.js" ></script>
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
        <div id="gauche" class="box"> 
            <h1>Liste des quiz déjà créés</h1>
            <table class="liste">
                <tr>
                    <th>Nom du quiz</th>
                    <th>Date de création</th>
                    <th></th>
                </tr>
                <c:forEach var="quiz" items="${listQuiz}">
                    <tr>
                        <td>${quiz.nomquiz}</td>
                        <td>${quiz.datecreationquiz}</td>
                        <td>
                            <form action="deleteQuiz.do" method="POST">
                                <input type ="hidden" name="code" value="${code}" />
                                <input type ="hidden" name="quizid" value="${quiz.quizid}" />
                                <button><img src="img/delete.png" alt="delete" height="20" /></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="droite" class="box">
            <h1>Créer un quiz</h1>
            <table>
                <tr>
                    <td></td>
                    <td>Nom du quiz :</td>
                    <td>
                        <input name="nomQuiz" type="text" value="Entrez le nom du quiz ici">
                        <input type="submit" onclick="afficherNom(this)" value="Valider">
                    </td>
                </tr>
                <tr name="questions" style="display:none">
                    <td>
                        <table name="tableQuestions" class="liste">
                            <tr>
                                <th style="display: none">id</th>
                                <th>Type</th>
                                <th>Énoncé</th>
                                <th>Réponses</th>
                                <th>Bonne(s) réponse(s)</th>
                                <th></th>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr name="nouvelleQuestion" style="display:none">
                    <td></td>
                    <td>
                        <table>
                            <tr>
                                <td>Type :</td>
                                <td name="typeSelect"><select name="type" onChange="afficherRep(this);">
                                        <option value="0">-</option>
                                        <option value="1">Qcm a réponse unique</option>
                                        <option value="2">Qcm normal</option>
                                    </select>
                                </td>
                            </tr>
                            <tr> 
                                <td>Question :</td>
                                <td><input name="enonceQues" type="text" value="Entrez l'énonce ici"/></td>

                            </tr>
                            <tr>
                                <td>La question est elle privée ? </td>
                                <td>
                                    <input type="radio" name="estPrivee" value=true checked style="inline">
                                    <label for="yes">Oui</label>
                                    <input type="radio" name="estPrivee" value=false style="inline">
                                    <label for="no">Non</label>
                                </td>
                            </tr>
                            <tr name="firstRep">
                                <td>Réponses :</td>
                                <td name="QcmRepUni" style="display:none">
                                    <input name="enonceRepUni" type="text" value="Entrez l'énoncé ici"/>
                                    <input type="radio" name="correctesUni" value=true>
                                    <button onclick="deleteRep(this);">X</button>
                                </td>
                                <td name="QcmRepMulti" style="display:none">
                                    <input name="enonceRepMulti" type="text" value="Entrez l'énoncé ici"/>
                                    <input type="checkbox" name="correctesMulti" value=true>
                                    <button onclick="deleteRep(this);">X</button>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td name="QcmRepUni" style="display:none">
                                    <button onclick="ajouterRep(this,1);">Ajouter une réponse</button>
                                </td>
                                <td name="QcmRepMulti" style="display:none">
                                    <button onclick="ajouterRep(this,2);">Ajouter une réponse</button>
                                </td>
                            </tr>
                            <tr name="firstMotCle">
                                <td>Mots-clés :</td>
                                <td>
                                    <input name="motCle" type="text" />
                                    <button onclick="deleteMotCle(this);">X</button>
                                </td>
                            </tr>
                            <tr name="ajoutMotCle">
                                <td></td>
                                <td>
                                    <button onclick="ajouterMotCle(this);">Ajouter un mot clé</button>
                                </td>
                            </tr>
                        </table>
                        <input type="hidden" name="personneId" value="${persId}">
                        <button onClick="validerQues();">Ajouter la question</button>
                    </td>
                </tr>
                <tr name="ancienneQuestion" style="display:none">
                    <td></td>
                    <td>Entrez un mot-clé pour trouver des questions : </td>
                    <td>
                        <input name="mot" type="text" value="Entrez votre mot-clé ici"/>
                        <input type="submit" onclick="afficheMotsCles(this)" value="Valider">
                    </td>
                </tr>
                <tr name="boutonsAjoutQues" style="display:none">
                    <td></td>
                    <td><button onclick="creerQues()">Ajouter une nouvelle question</button></td>
                    <td><button onclick="importerQues()">Importer une question existante</button></td>
                </tr>
                <tr name="boutonValidationQuiz" style="display: none">
                    <td></td>
                    <td>
                        <input type="hidden" name="personneId" value="${persId}">
                        <button onclick="saveQuiz(this)">Créer le quiz</button>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>