/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function affiche(ref) {
    var refPar = ref.parentNode;
    var elem = ref.value;
    $.ajax({
       url: "select.do",
       data: {
           "element": elem,
       },
       method: "POST",
       success: function(result){
            var select = document.getElementsByName("motsCle")[0];
            deleteAll(select);
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
            select.style.display="inline";
       },
       error: function(res,stat,err){
           console.log(res.responseText);
           console.log("nique ta mère");
           var select = ref.nextSibling.nextSibling;
           select.style.display="none";
       }
    });
}

function nombreMots(ref){
    var index = ref.selectedIndex;
    var idMot=ref.options[index].value;
    $.ajax({
        url: "afficheNombre.do",
        data: {
            "idMot": idMot
        },
        method: "POST",
        success: function(result){
            //console.log(result.nombre);
            var nombres = result.nombres;
            var tempSel = document.getElementsByName("choixNombre");
            if (tempSel.length>0){
                ref.parentNode.removeChild(tempSel[0]);
            }     
            var tempBut = document.getElementsByName("ajouterQues");
            if (tempBut.length>0){
                ref.parentNode.removeChild(tempBut[0]);
            }  
            console.log(nombres[0]);
            var select = addSelect(ref.parentNode, "choixNombre", nombres);
            
            var button = addButton(ref.parentNode, "", "ajouter", "genererQuest(this,"+idMot+")", true);
            button.setAttribute("name","ajouterQues");
            
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
    })
            
}

function genererQuest(ref,idMot){
    var selec = document.getElementsByName("choixNombre")[0];
    var index = selec.selectedIndex;
    var nbre=selec.options[index].value;
    $.ajax({
        url: "genererQuestion.do",
        data: {
            "idMot": idMot,
            "nbre": nbre
        },
        method: "POST",
        success: function(result){
            //console.log(result.nombre);
            var listQues =result.questions;
            var mot = result.motCle;
            
            var select = document.getElementsByName("choixNombre")[0];
            select.parentNode.removeChild(select);
            var button = document.getElementsByName("ajouterQues")[0];
            button.parentNode.removeChild(button);
            
            var formEnreg = document.getElementsByName("enregistrer")[0];
            formEnreg.style.display="inline";
            var formParti = document.getElementsByName("participer")[0];
            formParti.style.display="inline";
            
            var table = document.getElementsByName("tableQuestions")[0];
            table.style.display="inline";
            for (let i = 0; i < listQues.length; i++){
                var tr = document.createElement("TR");
                //tr.setAttribute("name","ques"+listQues[i].questionid);
                var td1 = document.createElement("TD");
                td1.appendChild(document.createTextNode(listQues[i].enonce));
                tr.appendChild(td1);
                var td2 = document.createElement("TD");
                td2.appendChild(document.createTextNode(mot));
                tr.appendChild(td2);
                addHidden(formEnreg, "idQues", listQues[i].questionid);
                addHidden(formParti, "idQues", listQues[i].questionid);
                table.firstChild.nextSibling.appendChild(tr); //Y a un décalage à cause de tbody
            }
            
        },
        error: function(res,stat,err){
            console.log(res.responseText);
        }
    })
}

function donneNom(ref){
    var nom = ref.value;
    var listInput = document.getElementsByName("nameQuiz");
    listInput[0].value=nom;
    listInput[1].value=nom;
}