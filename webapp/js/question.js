function creerQuestion(ref){
    var listPrivee = document.getElementsByName("estPrivee");
    var estPrivee =false;
    for (let j= 0; j<(listPrivee.length); j++){
        if (listPrivee[j].checked ==true ){
            estPrivee = listPrivee[j].value;
        }
    }
    var listUnique = document.getElementsByName("repUni");
    var repUni =false;
    for (let j= 0; j<(listUnique.length); j++){
        if (listUnique[j].checked ==true ){
            repUni = listUnique[j].value;
        }
    }
    $.ajax({
        url:"creerQuestion.do",
        data: {
            enonce: document.getElementsByName("enonce")[0].value,
            estPrivee: estPrivee,
            repUni: repUni,
        },
        method: 'POST',
        success: function(result){
            var enonce = result.enonceQues;
            var id = result.idQues;
            var divRef = getNextParentTag(ref,"DIV");
            deleteAll(divRef);
            var h1 = document.createElement("H1");
            h1.appendChild(document.createTextNode(enonce));
            divRef.appendChild(h1);
            var table = document.createElement("TABLE");
            
            var tr1 = document.createElement("TR");
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode("Ecrivez l'énoncé:"));
            var td2 = document.createElement("TD");
            var inputEn = document.createElement("INPUT");
            inputEn.setAttribute("name","enonceReponse");
            inputEn.setAttribute("type","text");
            td2.appendChild(inputEn);
            tr1.appendChild(td1);
            tr1.appendChild(td2);
            
            var tr2 = document.createElement("TR");
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode("La réponse est elle juste?"));
            var td2 = document.createElement("TD");
            var inputCor = document.createElement("INPUT");
            inputCor.setAttribute("value","true");
            inputCor.setAttribute("name","correcte");
            inputCor.setAttribute("type","radio");
            var labelCor = document.createElement("LABEL");
            labelCor.appendChild(document.createTextNode("oui"));
            
            var inputIncor = document.createElement("INPUT");
            inputIncor.setAttribute("value","false");
            inputIncor.setAttribute("name","correcte");
            inputIncor.setAttribute("type","radio");
            var labelIncor = document.createElement("LABEL");
            labelIncor.appendChild(document.createTextNode("non"));
            td2.appendChild(inputCor);
            td2.appendChild(labelCor);
            td2.appendChild(inputIncor);
            td2.appendChild(labelIncor);
            tr2.appendChild(td1);
            tr2.appendChild(td2);
            
            
            var tr3 = document.createElement("TR");
            var td1 = document.createElement("TD");
            var bouton = document.createElement("BUTTON");
            bouton.textContent="Ajouter des réponses";
            bouton.setAttribute("onClick","creerReponse(this,"+id+")");
            td1.appendChild(bouton);
            tr3.appendChild(td1);
            
            table.appendChild(tr1);
            table.appendChild(tr2);
            table.appendChild(tr3);
            
//            for (let i = 0; i < result.questions.length; i++){
//                var tr = document.createElement("TR");
//                var td2 = document.createElement("TD");
//                var input = document.createElement("INPUT");
//                input.setAttribute("value","");
//                input.setAttribute("name","enonceReponse");
//                input.setAttribute("type","text");
//                var input = document.createElement("INPUT");
//                input.setAttribute("value","0");
//                input.setAttribute("name","correcte");
//                input.setAttribute("type","boolean");
//                var td3 = document.createElement("TD");
//                var bouton = document.createElement("BUTTON");
//                bouton.textContent="Ajouter des réponses";
//                bouton.setAttribute("onClick","creerReponse(this)");
                /*
                var cache = document.createElement("INPUT");
                cache.setAttribute("type","hidden");
                cache.setAttribute("value",result.questions[i].contenuQuiz);
                cache.setAttribute("name","idCQ");
                td3.appendChild(cache);
                td3.appendChild(input);
                 */
//                var divRef = getNextParentTag(ref,"DIV");
//                deleteAll(divRef);
//                var form = document.createElement("FORM");
//                var bouton2 = document.createElement("BUTTON");
//                bouton2.textContent="Valider";
//                form.setAttribute("action","valider.do");
//                form.setAttribute("method","POST");
//                form.appendChild(bouton);
//                var h1 = document.createElement("H1");
//                h1.textContent="Vous avez fini de créer votre question, pour revenir au menu appuyez sur le bouton ci dessous.";
//                divRef.appendChild(h1);
//                divRef.appendChild(form);
//                console.log("Youpi");
//            }
            divRef.appendChild(table);
            divRef.appendChild(document.createTextNode("Liste des réponses"));
            var tab = document.createElement("TABLE");
            tab.setAttribute("id","listeRepPre");
            divRef.appendChild(tab);
            var bouton = document.createElement("A");
            bouton.textContent="Valider toutes les réponses";
            bouton.setAttribute("href","terminerReponse.do");
            divRef.appendChild(bouton);
        },
        error: function(resultat, statut, erreur){
            console.log(resultat.responseText);
            
        }
    });
}

function creerReponse(ref,idQues){
    //Créer un QCMRep
    var enonceRep = document.getElementsByName("enonceReponse")[0].value;
    var listCorrecte = document.getElementsByName("correcte");
    var  estCorrecte ="false";
    for (let j= 0; j<(listCorrecte.length); j++){
        if (listCorrecte[j].checked ==true ){
            estCorrecte = listCorrecte[j].value;
        }
    }
    $.ajax({
        url:"creerReponse.do",
        data: {
            correcte: estCorrecte,
            enonceRep: enonceRep,
            idQues: idQues
        },
        method: 'POST',
        success: function(result){
            var table = document.getElementById("listeRepPre");
//            deleteAll(divRef);
//            var form = document.createElement("FORM");
//            var bouton = document.createElement("BUTTON");
//            bouton.textContent="Revenir au menu";
//            form.setAttribute("action","valider.do");
//            form.setAttribute("method","POST");
//            form.appendChild(/* ??? */);
//            var h1 = document.createElement("H1");
//            h1.textContent="Vous avez fini de créer votre réponse, pour revenir au menu appuyez sur le bouton ci dessous.";
//            divRef.appendChild(h1);
//            divRef.appendChild(form);
//            console.log("Youpi");
            var tr = document.createElement("TR");
            var td1 = document.createElement("TD");
            td1.appendChild(document.createTextNode(enonceRep));
            var td2 = document.createElement("TD");
            if (estCorrecte=="true"){
                td2.appendChild(document.createTextNode("Juste"));
            }else{
                td2.appendChild(document.createTextNode("Fause"));
            }
            tr.appendChild(td1);
            tr.appendChild(td2);
            table.appendChild(tr);
            
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