function afficherRep(ref){
    //alert("Ca marche!!!");
    var tdRef = getNextParentTag(ref,"TD");
    var value = ref.options[ref.selectedIndex].value;
    
    convertElementToText(tdRef);
    addHidden(tdRef, "type", value);
    if (value=="1"){
        var td = document.getElementsByName("QcmRepUni")[0];
        td.style.display="inline";
        var td = document.getElementsByName("QcmRepUni")[1];
        td.style.display="inline";
    }else{
        var td = document.getElementsByName("QcmRepMulti")[0];
        td.style.display="inline";
        var td = document.getElementsByName("QcmRepMulti")[1];
        td.style.display="inline";
    }
    
}

function ajouterRep(ref,type){
    if (type==1){
        var inputText = document.createElement("INPUT");
        inputText.setAttribute("type", "text");
        inputText.setAttribute("name", "enonceRepUni");
        inputText.setAttribute("value", "Entrez l'énoncé ici");
        var inputRadio = document.createElement("INPUT");
        inputRadio.setAttribute("type", "radio");
        inputRadio.setAttribute("name", "correctesUni");
        inputRadio.setAttribute("value", "true");
        var button = document.createElement("BUTTON");
        button.setAttribute("onclick","deleteRep(this);");
        button.textContent="X";
        var trRef = getNextParentTag(ref,"TR");
        var tableRef = trRef.parentNode;
        var tr = document.createElement("TR");
        var td = document.createElement("TD");
        td.appendChild(document.createTextNode(""));
        tr.appendChild(td);
        var td = document.createElement("TD");
        td.appendChild(inputText);
        td.appendChild(inputRadio);
        td.appendChild(button);
        tr.appendChild(td);
        var insertedNode = tableRef.insertBefore(tr,trRef);
        ref.setAttribute("onClick","ajouterRep(this,1);");
    }else if (type==2){
        var inputText = document.createElement("INPUT");
        inputText.setAttribute("type", "text");
        inputText.setAttribute("name", "enonceRepMulti");
        inputText.setAttribute("value", "Entrez l'énoncé ici");
        var inputRadio = document.createElement("INPUT");
        inputRadio.setAttribute("type", "checkbox");
        inputRadio.setAttribute("name", "correctesMulti");
        inputRadio.setAttribute("value", "true");
        var button = document.createElement("BUTTON");
        button.setAttribute("onclick","deleteRep(this);");
        button.textContent="X";
        var trRef = getNextParentTag(ref,"TR");
        var tableRef = trRef.parentNode;
        var tr = document.createElement("TR");
        var td = document.createElement("TD");
        td.appendChild(document.createTextNode(""));
        tr.appendChild(td);
        var td = document.createElement("TD");
        td.appendChild(inputText);
        td.appendChild(inputRadio);
        td.appendChild(button);
        tr.appendChild(td);
        var insertedNode = tableRef.insertBefore(tr, trRef);
        ref.setAttribute("onClick","ajouterRep(this,2);");
    }
    
}

function ajouterMotCle(ref){
    var inputText = document.createElement("INPUT");
    inputText.setAttribute("type", "text");
    inputText.setAttribute("name", "motCle");
    var button = document.createElement("BUTTON");
    button.setAttribute("onclick","deleteMotCle(this);");
    button.textContent="X";
    var trRef = getNextParentTag(ref,"TR");
    var tableRef = trRef.parentNode;
    var tr = document.createElement("TR");
    var td = document.createElement("TD");
    td.appendChild(document.createTextNode(""));
    tr.appendChild(td);
    var td = document.createElement("TD");
    td.appendChild(inputText);
    td.appendChild(button);
    tr.appendChild(td);
    var insertedNode = tableRef.insertBefore(tr,trRef);
}


function valider(ref){
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
    
    var question ={ "enonce":enonce,"estPrivee":estPrivee};
    var listRep = [];
    for (let j= 0; j<(listEnonce.length); j++){
        var enonceRep = listEnonce[j].value;
        var estCorrecte = false;
        if (listCorr[j].checked ==true ){
            estCorrecte=true;
        }
        var reponse = { "enonce":enonceRep,"estCorrecte":estCorrecte};
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
            var divRef = getNextParentTag(ref,"DIV");
            deleteAll(divRef);
            var form = document.createElement("FORM");
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Revenir au menu";
            form.setAttribute("action","versCreerQues.do");
            form.setAttribute("method","GET");
            
            var hidden = document.createElement("INPUT");
            hidden.setAttribute("name","code");
            hidden.type="hidden";
            hidden.value=document.getElementsByName("code")[0].value;
            form.appendChild(hidden);
            form.appendChild(bouton);
            var h1 = document.createElement("H1");
            h1.textContent="Vous avez fini de créer votre question, pour revenir au menu de création appuyez sur le bouton ci dessous.";
            divRef.appendChild(h1);
            divRef.appendChild(form);
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
    });
        
}

function deleteMotCle(ref){
    var aSupr = getPreviousTag(ref,"INPUT");
    aSupr.parentNode.removeChild(aSupr);
    ref.parentNode.removeChild(ref);
}

function deleteRep(ref){
    var aSupr = getPreviousTag(ref,"INPUT");
    aSupr.parentNode.removeChild(aSupr);
    var aSupr = getPreviousTag(ref,"INPUT");
    aSupr.parentNode.removeChild(aSupr);
    ref.parentNode.removeChild(ref);
}

function validerModif(ref){
    var type = document.getElementsByName("type")[0].value; //Type de question
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
    
    var question ={ "enonce":enonce,"estPrivee":estPrivee};
    var listRep = [];
    for (let j= 0; j<(listEnonce.length); j++){
        var enonceRep = listEnonce[j].value;
        var estCorrecte = false;
        if (listCorr[j].checked ==true ){
            estCorrecte=true;
        }
        var reponse = { "enonce":enonceRep,"estCorrecte":estCorrecte};
        listRep.push(reponse);
    }
    var listMotsCles = [];
    for (let j= 0; j<(listMC.length); j++){
        var enonceMC = listMC[j].value;
        var motCle = { "enonce":enonceMC};
        listMotsCles.push(motCle);
    }
    console.log("coucou");
    $.ajax({
        url:"modifQuesRep.do",
        data: {
            "type": type,
            "question": JSON.stringify(question),
            "reponses": JSON.stringify(listRep),
            "motsCles": JSON.stringify(listMotsCles),
            "idQues": document.getElementsByName("idQues")[0].value
        },
        method: 'POST',
        success: function(result){var divRef = getNextParentTag(ref,"DIV");
            deleteAll(divRef);
            var form = document.createElement("FORM");
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Revenir au menu";
            form.setAttribute("action","versCreerQues.do");
            form.setAttribute("method","GET");
            
            var hidden = document.createElement("INPUT");
            hidden.setAttribute("name","code");
            hidden.type="hidden";
            hidden.value=document.getElementsByName("code")[0].value;
            form.appendChild(hidden);
            form.appendChild(bouton);
            var h1 = document.createElement("H1");
            h1.textContent="Vous avez fini de modifier votre question, pour revenir au menu de création appuyez sur le bouton ci dessous.";
            divRef.appendChild(h1);
            divRef.appendChild(form);
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
    });
        
}