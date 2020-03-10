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
            <img src="img/LogoCN_Blanc.png" style="height: 75px; position: absolute; top: 10px; left: 10px;" alt="LogoCN">
            <h1><img src="img/s2.jpg" style="height: 35px;"  alt="Saturne">  Saturne</h1>
            <nav>
                <ul>
                    <li class="deroulant"><a href="#">${nom} ${prenom} &ensp;</a>
                        <ul class="sous">
                            <li><a href="#">Créer une question</a></li>
                            <li><a href="#">Créer un quiz</a></li>
                            <li><a href="#">Créer une session d'évaluation</a></li>
                            <li><a href="#">Paramètres</a></li>
                            <li><a href="disconnect.do">Déconnexion</a></li>
                            </form>
                        </ul>
                    </li>
                </ul>
            </nav>
            </br>
        </header>
        <div id="droite">
            <h2>Liste des Groupes</h2>
        </div>
        <div id="haut">
            <h2>Prochaines évaluations</h2>
        </div>    
    </body>
</html>