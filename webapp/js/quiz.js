var nbQuestions = 0;

function afficherNom(ref){
    var tdRef = getNextParentTag(ref,"TD");
    var value = document.getElementsByName('nomQuiz')[0].value;
    deleteAll(tdRef);
    addText(tdRef,value);
    tdRef.setAttribute('name','nomQuiz');
    var boutonsAjout = document.getElementsByName('boutonsAjoutQues')[0];
    boutonsAjout.style.display="inline";
}

function creerQues(){
    var ajoutQues = document.getElementsByName('nouvelleQuestion')[0];
    ajoutQues.style.display="inline";
    var boutonsAjout = document.getElementsByName('boutonsAjoutQues')[0];
    boutonsAjout.style.display="none";
    var boutonValidation = document.getElementsByName('boutonValidationQuiz')[0];
    boutonValidation.style.display="none";
}

function importerQues(){
    var ajoutQues = document.getElementsByName('ancienneQuestion')[0];
    ajoutQues.style.display="inline";
    var boutonsAjout = document.getElementsByName('boutonsAjoutQues')[0];
    boutonsAjout.style.display="none";
    var boutonValidation = document.getElementsByName('boutonValidationQuiz')[0];
    boutonValidation.style.display="none";
}

function reinitialiserNouv(){
    var QcmRepUni = document.getElementsByName('QcmRepUni')[1];
    QcmRepUni.style.display="none";
    var currentElement = (getNextParentTag(QcmRepUni,'TR')).previousElementSibling;
    while(currentElement.getAttribute('name') !== 'firstRep'){
        var aSupprimer = currentElement;
        currentElement = currentElement.previousElementSibling;
        aSupprimer.remove();
    }
    var enonceRepUni1 = document.getElementsByName('enonceRepUni')[0];
    enonceRepUni1.value = "Entrez l'énoncé ici";
    var QcmRepUni = document.getElementsByName('QcmRepUni')[0];
    QcmRepUni.style.display="none";
    
    var QcmRepMulti = document.getElementsByName('QcmRepMulti')[1];
    QcmRepMulti.style.display="none";
    var currentElement = (getNextParentTag(QcmRepMulti,'TR')).previousElementSibling;
    while(currentElement.getAttribute('name') !== 'firstRep'){
        var aSupprimer = currentElement;
        currentElement = currentElement.previousElementSibling;
        aSupprimer.remove();
    }
    var enonceRepMulti1 = document.getElementsByName('enonceRepMulti')[0];
    enonceRepMulti1.value = "Entrez l'énoncé ici";
    var QcmRepMulti = document.getElementsByName('QcmRepMulti')[0];
    QcmRepMulti.style.display="none";
    
    var ajoutMotCle = document.getElementsByName('ajoutMotCle')[0];
    var currentElement = ajoutMotCle.previousElementSibling;
    while(currentElement.getAttribute('name') !== 'firstMotCle'){
        var aSupprimer = currentElement;
        currentElement = currentElement.previousElementSibling;
        aSupprimer.remove();
    }
    var motCle = document.getElementsByName('motCle')[0];
    motCle.value = "";
    
    var enonceQues = document.getElementsByName('enonceQues')[0];
    enonceQues.value = "Entrez l'énonce ici";
    
    var options = [{'id':0,'name':'-'},{'id':1,'name':'Qcm a réponse unique'},{'id':2,'name':'Qcm normal'}];
    var typeSelect = document.getElementsByName('typeSelect')[0];
    var type = convertTextToSelect(typeSelect,'type',options,0);
    type.setAttribute("onChange","afficherRep(this);");

    var nouvelleQuestion = document.getElementsByName('nouvelleQuestion')[0];
    nouvelleQuestion.style.display="none";
    
    var boutonsAjout = document.getElementsByName('boutonsAjoutQues')[0];
    boutonsAjout.style.display="inline";
    var boutonValidation = document.getElementsByName('boutonValidationQuiz')[0];
    boutonValidation.style.display="inline";
}

function reinitialiserAnc(ref){
    var trRef = getNextParentTag(ref,'TR');
    deleteAll(trRef);
    var td0 = document.createElement('TD');
    trRef.appendChild(td0);
    var td1 = document.createElement('TD');
    td1.appendChild(document.createTextNode('Entrez un mot-clé pour trouver des questions : '));
    trRef.appendChild(td1);
    var td2 = document.createElement('TD');
    var input1 = document.createElement('INPUT');
    input1.setAttribute("name","mot");
    input1.setAttribute("type","text");
    input1.setAttribute("value","Entrez votre mot-clé ici");
    td2.appendChild(input1);
    var input2 = document.createElement('INPUT');
    input2.setAttribute("onclick","afficheMotsCles(this)");
    input2.setAttribute("type","submit");
    input2.setAttribute("value","Valider");
    td2.appendChild(input2);
    trRef.appendChild(td2);
    trRef.style.display = 'none';
    
    var ancienneQuestion = document.getElementsByName('ancienneQuestion')[0];
    ancienneQuestion.style.display="none";
    
    var boutonsAjout = document.getElementsByName('boutonsAjoutQues')[0];
    boutonsAjout.style.display="inline";
    var boutonValidation = document.getElementsByName('boutonValidationQuiz')[0];
    boutonValidation.style.display="inline";
}

function validerQues(){
    var type = document.getElementsByName("type")[0].value; //Type de question
    var persId = document.getElementsByName("personneId")[0].value;
    if (type=="1"){
        var listEnonce = document.getElementsByName("enonceRepUni"); //Liste des énoncés de réponse
        var listCorr = document.getElementsByName("correctesUni"); //Liste qui permet de sevoir quelles réponses sont correctes
    }else{
        var listEnonce = document.getElementsByName("enonceRepMulti");
        var listCorr = document.getElementsByName("correctesMulti");
    }
    var listPrivee = document.getElementsByName("estPrivee");
    var estPrivee =false;
    for (let j= 0; j<(listPrivee.length); j++){
        if (listPrivee[j].checked ==true ){
            estPrivee = listPrivee[j].value; //Booléen permettant de savoir si la question est privée ou pas
        }
    }
    var enonce = document.getElementsByName("enonceQues")[0].value; //Enoncé de la question
    var listMC = document.getElementsByName("motCle") //Liste des mots clés
    
    var question ={"enonce":enonce,"estPrivee":estPrivee};
    var listRep = [];
    for (let j= 0; j<(listEnonce.length); j++){
        var enonceRep = listEnonce[j].value;
        var estCorrecte = false;
        if (listCorr[j].checked ==true ){
            estCorrecte=true;
        }
        var reponse = {"enonce":enonceRep,"estCorrecte":estCorrecte};
        listRep.push(reponse);
    }
    var listMotsCles = [];
    for (let j= 0; j<(listMC.length); j++){
        var enonceMC = listMC[j].value;
        var motCle = {"enonce":enonceMC};
        listMotsCles.push(motCle);
    }
    $.ajax({
        url:"creerQuesRep.do",
        data: {
            "type": type,
            "personneId": persId,
            "question": JSON.stringify(question),
            "reponses": JSON.stringify(listRep),
            "motsCles": JSON.stringify(listMotsCles)
        },
        method: 'POST',
        success: function(result){
            reinitialiserNouv();
            var ligneQuestions = document.getElementsByName('questions')[0];
            ligneQuestions.style.display="inline";
            var tableQuestions = document.getElementsByName('tableQuestions')[0];
            var tr = document.createElement('TR');
            var td0 = document.createElement('TD');
            td0.appendChild(document.createTextNode(result.quesId));
            td0.setAttribute('name','id');
            td0.style.display = 'none';
            tr.appendChild(td0);
            var td1 = document.createElement('TD');
            if(result.repUnique){
                td1.appendChild(document.createTextNode('Réponse unique'));
            } else {
                td1.appendChild(document.createTextNode('Réponses multiples'));
            }
            tr.appendChild(td1);
            var td2 = document.createElement('TD');
            td2.appendChild(document.createTextNode(result.enonce));
            tr.appendChild(td2);
            var td3 = document.createElement('TD');
            var ul1 = document.createElement('UL');
            for(let i=0; i<(result.listeReponses).length; i++){
                var li = document.createElement('LI');
                li.appendChild(document.createTextNode(result.listeReponses[i].enonceRep));
                ul1.appendChild(li);
            }
            td3.appendChild(ul1);
            tr.appendChild(td3);
            var td4 = document.createElement('TD');
            var ul2 = document.createElement('UL');
            for(let i=0; i<(result.listeReponses).length; i++){
                if(result.listeReponses[i].estCorrecte){
                    var li = document.createElement('LI');
                    li.appendChild(document.createTextNode(result.listeReponses[i].enonceRep));
                    ul2.appendChild(li);
                }
            }
            td4.appendChild(ul2);
            tr.appendChild(td4);
            var td5 = document.createElement('TD');
            var button = document.createElement('BUTTON');
            button.setAttribute('onclick','deleteQues(this)');
            button.appendChild(document.createTextNode("X"));
            td5.appendChild(button);
            tr.appendChild(td5);
            tableQuestions.appendChild(tr);
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
    });
        
}

function afficheMotsCles(ref) {
    var tdRef = getNextParentTag(ref,"TD");
    var value = document.getElementsByName('mot')[0].value;
    $.ajax({
       url: "select.do",
       data: {
           "element": value
       },
       method: "POST",
       success: function(result){
            deleteAll(tdRef);
            var select = document.createElement("SELECT");
            var option = document.createElement("OPTION");
            option.setAttribute("value",-1);
            option.appendChild(document.createTextNode("-"));
            select.appendChild(option);
            if (result.length != 0){
                for (let i = 0; i < result[0].motscle.length; i++){
                    var option = document.createElement("OPTION");
                    option.setAttribute("value",result[0].motscle[i].id);
                    option.appendChild(document.createTextNode(result[0].motscle[i].name));
                    select.appendChild(option);
                }
            }
            select.setAttribute("name","selectMotCle");
            select.setAttribute("onchange","afficheQuestions(this)");
            tdRef.appendChild(select);
            
            var button = document.createElement("BUTTON");
            button.setAttribute("onclick","reinitialiserAnc(this)");
            button.appendChild(document.createTextNode("Retour"));
            var newTd = document.createElement("TD");
            newTd.appendChild(button);
            var trRef = getNextParentTag(tdRef,"TR");
            trRef.appendChild(newTd);
       },
       error: function(res,stat,err){
           console.log(res);
       }
    });
}

function afficheQuestions(ref) {
    var index = ref.selectedIndex;
    var idMot=ref.options[index].value;
    $.ajax({
        url: "selectQuestion.do",
        data: {
            "idMot": idMot
        },
        method: "POST",
        success: function(result){
            var tdRef = getNextParentTag(ref,"TD");
           
            tdRef = tdRef.previousElementSibling;
            deleteAll(tdRef);
            addText(tdRef,'Choisissez une question');
            
            tdRef = tdRef.nextElementSibling;
            deleteAll(tdRef);

            var select = document.createElement("SELECT");
            var option = document.createElement("OPTION");
            option.setAttribute("value",-1);
            option.appendChild(document.createTextNode("-"));
            select.appendChild(option);
            for (let i = 0; i < result.length; i++){
                 var option = document.createElement("OPTION");
                 option.setAttribute("value",result[i].quesId);
                 option.appendChild(document.createTextNode(result[i].enonce));
                 select.appendChild(option);
            }
            select.setAttribute("name","selectQuestion");
            select.setAttribute("onchange","ajouterQues(this)");
            tdRef.appendChild(select);
        },
        error: function(res,stat,err){
           console.log(res.responseText);
       }
   });
}

function ajouterQues(ref){
    var quesId = ref.options[ref.selectedIndex].value;
    $.ajax({
        url:"afficherQuestion.do",
        data: {
            "quesId": quesId
        },
        method: 'POST',
        success: function(result){
            reinitialiserAnc(ref);
            var ligneQuestions = document.getElementsByName('questions')[0];
            ligneQuestions.style.display="inline";
            var tableQuestions = document.getElementsByName('tableQuestions')[0];
            var tr = document.createElement('TR');
            var td0 = document.createElement('TD');
            td0.appendChild(document.createTextNode(result.quesId));
            td0.setAttribute('name','id');
            td0.style.display = 'none';
            tr.appendChild(td0);
            var td1 = document.createElement('TD');
            if(result.repUnique){
                td1.appendChild(document.createTextNode('Réponse unique'));
            } else {
                td1.appendChild(document.createTextNode('Réponses multiples'));
            }
            tr.appendChild(td1);
            var td2 = document.createElement('TD');
            td2.appendChild(document.createTextNode(result.enonce));
            tr.appendChild(td2);
            var td3 = document.createElement('TD');
            var ul1 = document.createElement('UL');
            for(let i=0; i<(result.listeReponses).length; i++){
                var li = document.createElement('LI');
                li.appendChild(document.createTextNode(result.listeReponses[i].enonceRep));
                ul1.appendChild(li);
            }
            td3.appendChild(ul1);
            tr.appendChild(td3);
            var td4 = document.createElement('TD');
            var ul2 = document.createElement('UL');
            for(let i=0; i<(result.listeReponses).length; i++){
                if(result.listeReponses[i].estCorrecte){
                    var li = document.createElement('LI');
                    li.appendChild(document.createTextNode(result.listeReponses[i].enonceRep));
                    ul2.appendChild(li);
                }
            }
            td4.appendChild(ul2);
            tr.appendChild(td4);
            var td5 = document.createElement('TD');
            var button = document.createElement('BUTTON');
            button.setAttribute('onclick','deleteQues(this)');
            button.appendChild(document.createTextNode("X"));
            td5.appendChild(button);
            tr.appendChild(td5);
            tableQuestions.appendChild(tr);
        }, error: function(res,stat,err){
            console.log(res.responseText);
        }
    });
    
}

function saveQuiz(ref){
    var persId = document.getElementsByName("personneId")[0].value;
    var nomQuiz = document.getElementsByName("nomQuiz")[0].firstChild.nodeValue;
    var questions = document.getElementsByName('id');
    var listQues = [];
    for (let j= 0; j<(questions.length); j++){
        listQues.push(questions[j].firstChild.nodeValue);
    }
    $.ajax({
        url:"saveQuiz.do",
        data: {
            "persId": persId,
            "nomQuiz": nomQuiz,
            "listQues": JSON.stringify(listQues)
        },
        method: 'POST',
        success: function(result){
            console.log(result);
            var divRef = getNextParentTag(ref,"DIV");
            deleteAll(divRef);
            var form = document.createElement("FORM");
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Revenir au menu";
            form.setAttribute("action","versCreerQuiz.do");
            form.setAttribute("method","GET");
            
            var hidden = document.createElement("INPUT");
            hidden.setAttribute("name","code");
            hidden.type="hidden";
            hidden.value=document.getElementsByName("code")[0].value;
            form.appendChild(hidden);
            form.appendChild(bouton);
            var h1 = document.createElement("H1");
            h1.textContent="Vous avez fini de créer votre quiz, pour revenir au menu de création appuyez sur le bouton ci dessous.";
            divRef.appendChild(h1);
            divRef.appendChild(form);
        },
        error: function(res,stat,err){
           console.log(res.responseText);
       }
   });
}

function deleteQues(ref){
    var trRef = getNextParentTag(ref,'TR');
    trRef.remove();
}

