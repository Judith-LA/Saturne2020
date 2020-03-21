/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.items.*;
import org.centrale.pgrou.saturne.repositories.*;
import org.centrale.pgrou.saturne.tools.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Pierre
 */
@Controller
public class QuizController {
    @Autowired
    private ConnexionRepository connexionRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private ContenuquizRepository contenuquizRepository;
    @Autowired
    private QcmRepository qcmRepository;
    @Autowired
    private QcmrepRepository qcmrepRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    
	
    @RequestMapping(value="versCreerQuiz.do",method=RequestMethod.GET)
    public ModelAndView creerQuiz(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("quiz");
        String code = request.getParameter("code");
        Optional<Connexion> coResult = connexionRepository.findById(code);
        if(!coResult.isEmpty()){
            Connexion connexion = coResult.get();
            Security.setDefaultData(returned, connexion);
            Personne pers = connexion.getPersonneid();
            List<Quiz> listQuiz = quizRepository.findWithPersonne(pers.getPersonneid());
            returned.addObject("listQuiz",listQuiz);
        }
        return returned;
    }
    
    @RequestMapping(value="selectQuestion.do",method=RequestMethod.POST)
    public ModelAndView creerListeQuestions(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONArray returnedObject = new JSONArray();
        String idMotStr = request.getParameter("idMot");
        int idMot = Integer.parseInt(idMotStr);
        List<Question> questions = questionRepository.findWithMotCleProf(idMot);
        for(int i=0; i<questions.size(); i++){
            JSONObject question = new JSONObject();
            question.put("quesId", questions.get(i).getQuestionid());
            question.put("enonce", questions.get(i).getEnonce());
            returnedObject.put(question);
        }
        
        return returned.addObject("theResponse",returnedObject.toString());
    }
    
    @RequestMapping(value="afficherQuestion.do",method=RequestMethod.POST)
    public ModelAndView afficherQuestion(HttpServletRequest request) throws ParseException{
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        String quesIdStr = request.getParameter("quesId");
        int quesId = Integer.parseInt(quesIdStr);
        Optional<Question> ques = questionRepository.findById(quesId);
        if(ques.isPresent()){
            Question question1 = ques.get();
            Qcm qcmQues = qcmRepository.findWithParameters(question1.getQuestionid());
            List<Reponse> repQues = reponseRepository.findWithParameter(question1);
            JSONArray listReponses = new JSONArray();
            for(Reponse r: repQues){
                JSONObject rep = new JSONObject();
                rep.put("repId", r.getReponseid());
                rep.put("estCorrecte", r.getCorrecte());
                List<Qcmrep> qcmrep = qcmrepRepository.findWithParameter(r);
                for(Qcmrep qr:qcmrep){
                    rep.put("enonceRep", qr.getEnonce());
                }
                listReponses.put(rep);
            }
            object.put("quesId", question1.getQuestionid());
            object.put("enonce", question1.getEnonce());
            object.put("repUnique", qcmQues.getRepunique());
            object.put("listeReponses", listReponses);
        }
        
        return returned.addObject("theResponse",object.toString());
    }
    
    @RequestMapping(value="saveQuiz.do",method=RequestMethod.POST)
    public ModelAndView sauvegarderQuiz(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONArray returnedObject = new JSONArray();
        
        Quiz quiz = new Quiz();
        Collection<Contenuquiz> listQues = new ArrayList();;
        
        String nomQuiz = request.getParameter("nomQuiz");
        quiz.setNomquiz(nomQuiz);
        
        quiz.setDatecreationquiz(Utilities.getCurrentDate());
        
        String persIdStr = request.getParameter("persId");
        int persId = Integer.parseInt(persIdStr);
        Optional<Personne> pers = personneRepository.findById(persId);
        quiz.setPersonneid(pers.get());
        Quiz newQuiz = quizRepository.save(quiz);
        
        String quesIdStr = request.getParameter("listQues");
        JSONArray listQuesId = new JSONArray(quesIdStr);
        for(int i=0; i<listQuesId.length(); i++){
            String quesStr = listQuesId.getString(i);
            int quesId = Integer.parseInt(quesStr);
            Optional<Question> ques = questionRepository.findById(quesId);
            if(ques.isPresent()){
                Contenuquiz contenuquiz = new Contenuquiz();
                contenuquiz.setQuestionid(ques.get());
                contenuquiz.setQuizid(quiz);
                contenuquiz = contenuquizRepository.save(contenuquiz);
                listQues.add(contenuquiz);
            }
        }
        newQuiz.setContenuquizCollection(listQues);
        Quiz lastQuiz = quizRepository.save(quiz);
        
        return returned.addObject("theResponse",returnedObject.toString());
    }
    
    @RequestMapping(value="deleteQuiz.do",method=RequestMethod.POST)
    public ModelAndView supprimerQuiz(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("quiz");
        String idStr = request.getParameter("quizid");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Optional<Quiz> quiz = quizRepository.findById(id);
            if(quiz.isPresent()){
                Quiz leQuiz = quiz.get();
                List<Contenuquiz> contenuquizList = contenuquizRepository.findWithQuizId(leQuiz.getQuizid());
                for(Contenuquiz l:contenuquizList){
                    contenuquizRepository.delete(l);
                }
                quizRepository.delete(leQuiz);
            }
        }
        
        String code = request.getParameter("code");
        Optional<Connexion> coResult = connexionRepository.findById(code);
        if(!coResult.isEmpty()){
            Connexion connexion = coResult.get();
            Security.setDefaultData(returned, connexion);
            Collection<Quiz> listQuiz = quizRepository.findWithPersonne(connexion.getPersonneid().getPersonneid());
            returned.addObject("listQuiz",listQuiz);
        }
        
        return returned;
    }
}