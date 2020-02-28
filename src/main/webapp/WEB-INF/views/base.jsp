<%@page contentType="text/html" pageEncoding="UTF-8"%>
{% block tags %}{% endblock %}
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
    	<meta charset="UTF-8"/>
    	<title>{% block title %}{% endblock %}</title>
    	{% block stylesheets %}{% endblock %}
    	{% block scripts %}{% endblock %}
    </head>
    <body>
    	<header>
    		</br>
            <h1><img src="img/s2.jpg" style="height: 35px;" alt="Saturne">  Saturne</h1>
            <span style="float: right; text-align: right">Se déconnecter</span>
            </br>
    	</header>
    	{% block body %}{% endblock %}
    	<footer></footer>
    </body>
</html>