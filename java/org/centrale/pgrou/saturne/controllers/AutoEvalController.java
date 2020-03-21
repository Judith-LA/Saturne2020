/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.items.*;
import org.centrale.pgrou.saturne.repositories.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marion
 */
@Controller
public class AutoEvalController {
    @Autowired
    private MotcleRepository motcleRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private PersonneRepository personneRepository;
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private ContenuquizRepository contenuquizRepository;
    
    @Autowired
    private ReponseRepository reponseRepository;
    
    @Autowired
    private QcmrepRepository qcmrepRepository;
    
    @Autowired
    private QcmRepository qcmRepository;
    
    @RequestMapping(value="select.do",method=RequestMethod.POST)
    public ModelAndView afficheMots (HttpServletRequest request){
        String element = request.getParameter("element");
        JSONObject returnedObject = new JSONObject();
        ModelAndView returned = new ModelAndView("ajax"); 
        List<Motcle> mots =  motcleRepository.findWithMot(element);
        if (mots.isEmpty()){
            JSONArray returnedArray = new JSONArray();
            return returned.addObject("theResponse",returnedArray.toString());
        }else{
            JSONArray motscle = new JSONArray();
            for (Motcle mot : mots) {
                JSONObject jsonMot = new JSONObject();
                jsonMot.accumulate("id", mot.getMotcleid());
                jsonMot.accumulate("name", mot.getMot());
                motscle.put(jsonMot);
            }
            returnedObject.put("motscle", motscle);
            JSONArray returnedArray = new JSONArray();
            returnedArray.put(returnedObject);
            returned.addObject("theResponse",returnedArray.toString());
            return returned;
        }
        
    }
    
    //afficheNombre.do
    @RequestMapping(value="afficheNombre.do",method=RequestMethod.POST)
    public ModelAndView afficheNombre (HttpServletRequest request){
        String element = request.getParameter("idMot");
        int idMot = Integer.parseInt(element);
        JSONObject returnedObject = new JSONObject();
        ModelAndView returned = new ModelAndView("ajax"); 
        int nombre = motcleRepository.findNombreWithId(idMot);
        JSONArray listNombres = new JSONArray();
        for (int i=1;i<=nombre;i++){
            JSONObject nombreObject = new JSONObject();
            nombreObject.accumulate("id", i);
            nombreObject.accumulate("name", i);
            listNombres.put(nombreObject);
        }
        returnedObject.put("nombres", listNombres);
        returned.addObject("theResponse",returnedObject.toString());
        return returned;
        
    }
    
    //g
    @RequestMapping(value="genererQuestion.do",method=RequestMethod.POST)
    public ModelAndView genererQuestion (HttpServletRequest request){
        String element = request.getParameter("idMot");
        int idMot = Integer.parseInt(element);
        String nbreStr = request.getParameter("nbre");
        int nbre = Integer.parseInt(nbreStr);
        JSONObject returnedObject = new JSONObject();
        ModelAndView returned = new ModelAndView("ajax"); 
        
        List<Question> listQuesTot = questionRepository.findWithMotCle(idMot);
        JSONArray listQuesRand = new JSONArray();
        
        Optional<Motcle> motCleOpt = motcleRepository.findById(idMot);
        String mot = motCleOpt.get().getMot();
        int size = listQuesTot.size();
        Random rand = new Random();
        for(int k=0; k<nbre;k++){
            int index =rand.nextInt(size-k);
            JSONObject question = new JSONObject();
            question.accumulate("enonce", listQuesTot.get(index).getEnonce());
            question.accumulate("questionid", listQuesTot.get(index).getQuestionid());
            listQuesRand.put(question);
            listQuesTot.remove(index);
        }
        String listQuesStr = listQuesRand.toString();
        JSONArray questions = new JSONArray(listQuesStr);
        
        
        returnedObject.put("questions", questions);
        returnedObject.put("motCle", mot);
        returned.addObject("theResponse",returnedObject.toString());
        return returned;
        
    }
    
    //enregAutoEval.do
    @RequestMapping(value="enregAutoEval.do",method=RequestMethod.POST)
    public ModelAndView enregAutoEval (HttpServletRequest request){
        String nom = request.getParameter("nameQuiz"); 
        String persIdStr = request.getParameter("personneId");
        //int persId = Integer.parseInt(persIdStr);
        int persId = 5; //mets la ligne au dessus quand tu récupèreras l'id de la personne
        String[] listQuesStr = request.getParameterValues("idQues");
        Quiz unQuiz = new Quiz();
        unQuiz.setNomquiz(nom);
        unQuiz.setPersonneid(personneRepository.findById(persId).get());
        unQuiz.setDatecreationquiz(new Date());
        Quiz leQuiz = quizRepository.save(unQuiz);
        for (int i=0;i<listQuesStr.length;i++){
            String idQuesStr = listQuesStr[i];
            int idQues = Integer.parseInt(idQuesStr);
            Optional<Question> ques = questionRepository.findById(idQues);
            Contenuquiz contenu = new Contenuquiz();
            contenu.setQuestionid(ques.get());
            contenu.setQuizid(leQuiz);
            contenuquizRepository.save(contenu);
        }
        ModelAndView returned = new ModelAndView("AccueilEtudiant");
        return returned;
    }
    
    
    /**@RequestMapping(value="partiAutoEval.do",method=RequestMethod.POST)
    public ModelAndView participerAutoEval(HttpServletRequest request) {
        ModelAndView returned;
        List<QuesRepQCM> listQuesRep = new ArrayList();
        
        String nom = request.getParameter("nameQuiz"); 
        String persIdStr = request.getParameter("personneId");
        //int persId = Integer.parseInt(persIdStr);
        int persId = 5; //mets la ligne au dessus quand tu récupèreras l'id de la personne
        String[] listQuesStr = request.getParameterValues("idQues");
        Quiz unQuiz = new Quiz();
        unQuiz.setNomquiz(nom);
        unQuiz.setPersonneid(personneRepository.findById(persId).get());
        unQuiz.setDatecreationquiz(new Date());
        Quiz leQuiz = quizRepository.save(unQuiz);
        for (int i=0;i<listQuesStr.length;i++){
            String idQuesStr = listQuesStr[i];
            int idQues = Integer.parseInt(idQuesStr);
            Optional<Question> ques = questionRepository.findById(idQues);
            Contenuquiz contenu = new Contenuquiz();
            contenu.setQuestionid(ques.get());
            contenu.setQuizid(leQuiz);
            contenuquizRepository.save(contenu);
        }
        
        returned = new ModelAndView("repAutoEval");
        TestAff unTestAff = new TestAff(unQuiz.getQuizid(),unQuiz.getNomquiz());
        returned.addObject("test",unTestAff);
        List<Contenuquiz> quizCont = contenuquizRepository.findWithParameter(unQuiz);
        Integer i = 1;
        for (Contenuquiz c: quizCont){
            Question question = c.getQuestionid();
            List<Reponse> reponses = reponseRepository.findWithParameter(question);
            List<ReponseQCM> listRepQCM = new ArrayList();
            for (Reponse rep: reponses){
                Qcmrep qr = qcmrepRepository.findWithReponseId(rep);
                ReponseQCM repQCM = new ReponseQCM(qr.getEnonce(),qr.getQcmrepid(),rep.getCorrecte());
                listRepQCM.add(repQCM);                    
            }
            List<ReponseQCM> listRepQCMRand = new ArrayList();
            int size = listRepQCM.size();
            Random randRep = new Random();
            for(int k=0; k<size;k++){
                int index =randRep.nextInt(size-k);
                listRepQCMRand.add(listRepQCM.get(index));
                listRepQCM.remove(index);
            }
            Qcm qcm = qcmRepository.findWithParameters(question.getQuestionid());
            Boolean repUni= qcm.getRepunique();
            QuesRepQCM ques = new QuesRepQCM(question.getEnonce(),c.getContenuquizid(),listRepQCMRand, repUni, i);
            listQuesRep.add(ques);
            i=i+1;
        }
        List<QuesRepQCM> listQuesRepRand= new ArrayList();
        int size = i-1;
        Random rand = new Random();
        for(int k=0; k<(i-1);k++){
            int index =rand.nextInt(size-k);
            listQuesRepRand.add(listQuesRep.get(index));
            listQuesRep.remove(index);
        }
        returned.addObject("nombre",i-1);
        returned.addObject("quesRep", listQuesRepRand);
        returned.addObject("personneId", persIdStr);
        return returned;
    }**/


    /**@RequestMapping(value="corrigerAutoEval.do",method=RequestMethod.POST)
    public ModelAndView envRep(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("corAutoEval");
        String personneIdStr = request.getParameter("personneId");
        int personneId = Integer.parseInt("5"); // Mettre personneIdStr
        
        List<QuesRepQCM> listQuesRep = new ArrayList();
        
        String testId = request.getParameter("testId");
        Optional<Quiz> unTest = quizRepository.findById(Integer.parseInt(testId));
        if (unTest.isPresent()){
            Quiz leTest = unTest.get();
            
            TestAff unTestAff = new TestAff(leTest.getQuizid(),leTest.getNomquiz());
            returned.addObject("test",unTestAff);
            
            int j =0;
            
            String strNombre = request.getParameter("nombre");
            Integer nombre = Integer.parseInt(strNombre);
            for (int i = 1; i <= nombre; i++){ 
                String strIdQues = request.getParameter("q"+i);
                Integer idQues = Integer.parseInt(strIdQues);
                Optional<Contenuquiz> aContQuiz= contenuquizRepository.findById(idQues); 
                if(aContQuiz.isPresent()){
                    Contenuquiz theContQuiz = aContQuiz.get(); 
                    Question question = theContQuiz.getQuestionid();
                    List<Reponse> reponses = reponseRepository.findWithParameter(question);
                    List<ReponseQCM> listRepQCM = new ArrayList();
                    for (Reponse rep: reponses){
                        Qcmrep qr = qcmrepRepository.findWithParameter(rep);
                        String[] strIdRep = request.getParameterValues("r"+idQues);//On récupère les réponses cochées
                        Boolean juste = false;
                        if (rep.getCorrecte()){
                            juste = true;
                        }                       
                        Boolean cochee = false;
                        if (strIdRep!=null){
                            for (int k =0; k<strIdRep.length; k++){ //Régler le pb si c'est vide?
                                int idRepCochee = Integer.parseInt(strIdRep[k]);
                                int idQCMRep = qr.getQcmrepid();
                                if (idRepCochee==idQCMRep){ //La réponse est cochée
                                    cochee = true;
                                }
                            }
                        }
                        ReponseQCMAuto repQCM = new ReponseQCMAuto(cochee,qr.getEnonce(),qr.getQcmrepid(),juste);
                        listRepQCM.add(repQCM);                   
                    }
                    List<ReponseQCM> listRepQCMRand = new ArrayList();
                    int size = listRepQCM.size();
                    Random randRep = new Random();
                    for(int k=0; k<size;k++){
                        int index =randRep.nextInt(size-k);
                        listRepQCMRand.add(listRepQCM.get(index));
                        listRepQCM.remove(index);
                    }
                    Qcm qcm = qcmRepository.findWithParameters(question.getQuestionid());
                    Boolean repUni= qcm.getRepunique();
                    QuesRepQCM ques = new QuesRepQCM(question.getEnonce(),theContQuiz.getContenuquizid(),listRepQCMRand, repUni, i);
                    listQuesRep.add(ques);
                    j=j+1;
                }
            }
            returned.addObject("nombre",j-1);
            returned.addObject("quesRep", listQuesRep);
            returned.addObject("personneId", personneId);
        }        
        return returned;
    }   **/   
}
        
        
