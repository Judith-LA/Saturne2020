function creerTest(ref){
    var selNot = document.getElementsByName("notation")[0];
    var selGro = document.getElementsByName("groupe")[0];
    var selQui = document.getElementsByName("quiz")[0];
    var indexNot = selNot.selectedIndex;
    var indexGro = selGro.selectedIndex;
    var indexQui = selQui.selectedIndex;
    $.ajax({
        url:"creerTest.do",
        data: {
            dateDeb: document.getElementsByName("dateDeb")[0].value,
            heureDeb: document.getElementsByName("heureDeb")[0].value,
            dateFin: document.getElementsByName("dateFin")[0].value,
            heureFin: document.getElementsByName("heureFin")[0].value,
            duree: document.getElementsByName("duree")[0].value,
            notationId: selNot.options[indexNot].value,
            groupeId: selNot.options[indexGro].value,
            quizId: selQui.options[indexQui].value
        },
        method: 'POST',
        success: function(result){
            var divRef = getNextParentTag(ref,"DIV");
            deleteAll(divRef);
            var h1 = document.createElement("H1");
            h1.appendChild(document.createTextNode("Choisissez le nombre de points à attribuer à chacune des questions de votre test"));
            divRef.appendChild(h1);
            var table = document.createElement("TABLE");
            for (let i = 0; i < result.questions.length; i++){
                var tr = document.createElement("TR");
                var td2 = document.createElement("TD");
                td2.appendChild(document.createTextNode(result.questions[i].enonce));
                var td3 = document.createElement("TD");
                var input = document.createElement("INPUT");
                input.setAttribute("id","cQ"+result.questions[i].contenuQuiz);
                input.setAttribute("value","0");
                input.setAttribute("name","pt");
                input.setAttribute("type","text");
                input.setAttribute("pattern","[0-9]{1,2}[.]{0,1}[0-9]{0,2}");
                input.setAttribute("size","5");
                input.setAttribute("onChange","calcul()");
                var cache = document.createElement("INPUT");
                cache.setAttribute("type","hidden");
                cache.setAttribute("value",result.questions[i].contenuQuiz);
                cache.setAttribute("name","idCQ");
                td3.appendChild(cache);
                td3.appendChild(input);
                td3.appendChild(document.createTextNode(" point(s)"));
                tr.appendChild(td2);
                tr.appendChild(td3);
                table.appendChild(tr);
            }
            divRef.appendChild(table);
            divRef.appendChild(document.createTextNode("Total sur "));
            var total = document.createElement("VAR");
            total.textContent="0";
            total.setAttribute("name","total");
            divRef.appendChild(total);
            divRef.appendChild(document.createTextNode(" points"));
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Valider";
            bouton.setAttribute("onClick","allouerPoints(this,"+result.questions.length+")");
            divRef.appendChild(document.createElement("BR"));
            divRef.appendChild(bouton);
            
        },
        error: function(resultat, statut, erreur){
            console.log(resultat.responseText);
            
        }
    });
}

function deleteAll(ref){
    while (ref.firstChild !== null){
        ref.removeChild(ref.firstChild);
    }
}

function getNextParentTag(currentElement, tagName) {
    // nodeType=1 is TAG = <p> <div> <tr> ..
    while ((currentElement !== null) && ((currentElement.nodeType !== 1 )
        || (currentElement.tagName!== tagName))){
            currentElement = currentElement.parentNode;
    }
    return currentElement;
}

function calcul(){
    var tot = document.getElementsByName("total")[0];
    var pts = document.getElementsByName("pt");
    var res = 0;
    for (let i = 0; i < pts.length; i++){
        res= res+parseFloat(pts[i].value);
    }
    tot.textContent=res;
}

function allouerPoints(ref,n){
    var contenuQuiz = document.getElementsByName("idCQ");
    var points = document.getElementsByName("pt");
    var listID = [];
    var listPt = [];
    for (let i = 0; i<n; i++){
        listID.push(contenuQuiz[i].value);
        listPt.push(points[i].value);
    }
    $.ajax({
        url: "allouerPts.do",
        data: {
            listePoints: JSON.stringify(listPt),
            listeIDs: JSON.stringify(listID)
        },
        method: "POST",
        success: function(result){
            var divRef = getNextParentTag(ref,"DIV");
            deleteAll(divRef);
            var form = document.createElement("FORM");
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Revenir au menu";
            form.setAttribute("action","valider.do");
            form.setAttribute("method","POST");
            form.appendChild(bouton);
            var h1 = document.createElement("H1");
            h1.textContent="Vous avez fini de créer votre session de test, pour revenir au menu appuyez sur le bouton ci dessous.";
            divRef.appendChild(h1);
            divRef.appendChild(form);
            console.log("Youpi");
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
        
    });
}