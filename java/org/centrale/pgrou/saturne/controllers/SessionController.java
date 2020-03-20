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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.controllers.*;
import org.centrale.pgrou.saturne.items.*;
import org.centrale.pgrou.saturne.repositories.*;
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
 * @author ECN
 */
@Controller
public class SessionController {
    @Autowired
    private NotationRepository notationRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ContenuquizRepository contenuquizRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ConnexionRepository connexionRepository;
    
    @RequestMapping(value="versCreerTest.do",method=RequestMethod.GET)
    public ModelAndView envoyerVersCreerTest(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("session");
        String code = request.getParameter("code");
        Optional<Connexion> coResult = connexionRepository.findById(code);
        if(!coResult.isEmpty()){
            Connexion connexion = coResult.get();
            Security.setDefaultData(returned, connexion);
            Personne pers = connexion.getPersonneid();
            List<Notation> listNotation = notationRepository.findAll();
            List<Groupe> listGroupe = groupeRepository.findAll();
            List<Quiz> listQuiz = quizRepository.findWithPersonne(pers.getPersonneid());
            List<Test> listTest = testRepository.findWithPersonne(pers.getPersonneid());
            returned.addObject("listNotations", listNotation);
            returned.addObject("listGroupes", listGroupe);
            returned.addObject("listQuizs",listQuiz);
            returned.addObject("listTests",listTest);
        }
        return returned;
    }
    
    @RequestMapping(value="creerTest.do",method=RequestMethod.POST)
    public ModelAndView creerTest1(HttpServletRequest request) throws ParseException {
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        JSONArray questions = new JSONArray();
        
        String dateDeb = request.getParameter("dateDeb");
        String heureDeb = request.getParameter("heureDeb");
        String dateFin = request.getParameter("dateFin");
        String heureFin = request.getParameter("heureFin");
        String duree = request.getParameter("duree");
        String notationId = request.getParameter("notationId");
        String groupeId = request.getParameter("groupeId");
        String quizId = request.getParameter("quizId");
        
        Test test = new Test();
        Optional<Groupe> groupe = groupeRepository.findById(Integer.parseInt(groupeId));
        Optional<Notation> notation = notationRepository.findById(Integer.parseInt(notationId));
        Optional<Quiz> quiz = quizRepository.findById(Integer.parseInt(quizId));
        
        if (groupe.isPresent()){
            Groupe unGroupe = groupe.get();
            test.setGroupeid(unGroupe);
        }
        if (notation.isPresent()){
            Notation uneNotation = notation.get();
            test.setNotationid(uneNotation);
        }
        if (quiz.isPresent()){
            Quiz unQuiz = quiz.get();
            test.setQuizid(unQuiz);
            List<Contenuquiz> quizCont = contenuquizRepository.findWithParameter(unQuiz);
            for (Contenuquiz c: quizCont){
                JSONObject jsonQuestion = new JSONObject();
                Question question = c.getQuestionid();
                jsonQuestion.accumulate("enonce", question.getEnonce());
                jsonQuestion.accumulate("contenuQuiz", c.getContenuquizid());
                questions.put(jsonQuestion);
            }
            object.put("questions",questions);
        }
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date timeDeb =  df.parse(dateDeb+" "+heureDeb);
        Date timeFin = df.parse(dateFin+" "+heureFin);
        
        DateFormat dfb = new SimpleDateFormat("HH:mm");
        Date timeDuree = dfb.parse(duree);
        
        test.setDatedebuttest(timeDeb);
        test.setDatefintest(timeFin);
        test.setDureemaxtest(timeDuree);
        
        Test test1 = testRepository.save(test);
        
        
        return returned.addObject("theResponse",object.toString());
        
    }
    
    @RequestMapping(value="allouerPts.do",method=RequestMethod.POST)
    public ModelAndView creerTest2(HttpServletRequest request){
        ModelAndView returned = new ModelAndView("ajax");
        JSONObject object = new JSONObject();
        
        String listePoints = request.getParameter("listePoints");
        JSONArray points = new JSONArray(listePoints);
        String listeIDs = request.getParameter("listeIDs");
        JSONArray contenuQuizIds = new JSONArray(listeIDs);
        
        for (int i=0; i<points.length();i++){
            Optional<Contenuquiz> c = contenuquizRepository.findById(Integer.parseInt(contenuQuizIds.getString(i)));
            if (c.isPresent()){
                Contenuquiz unContenuQuiz = c.get();
                unContenuQuiz.setNombrepoints(Integer.parseInt(points.getString(i)));
                contenuquizRepository.save(unContenuQuiz);
            }
        }
        
        object.put("res", contenuQuizIds);
        
        return returned.addObject("theResponse", object.toString());
    }

        
    @RequestMapping(value="valider.do",method=RequestMethod.POST)
    public ModelAndView handlePost2() {
        ModelAndView returned;
        List<Notation> listNotation = notationRepository.findAll();
        List<Groupe> listGroupe = groupeRepository.findAll();
        List<Quiz> listQuiz = quizRepository.findAll();
        List<Test> listTest = testRepository.findAll();
        returned = new ModelAndView("session");
        returned.addObject("listNotations", listNotation);
        returned.addObject("listGroupes", listGroupe);
        returned.addObject("listQuizs",listQuiz);
        returned.addObject("listTests",listTest);
        return returned;
    }
    
    @RequestMapping(value="delete.do",method=RequestMethod.POST)
    public ModelAndView handleDelete(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("session");
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Optional<Test> test = testRepository.findById(id);
            if (test.isPresent()){
                testRepository.delete(test.get());
            }
        }
        List<Notation> listNotation = notationRepository.findAll();
        List<Groupe> listGroupe = groupeRepository.findAll();
        List<Quiz> listQuiz = quizRepository.findAll();
        List<Test> listTest = testRepository.findAll();
        returned.addObject("listNotations", listNotation);
        returned.addObject("listGroupes", listGroupe);
        returned.addObject("listQuizs",listQuiz);
        returned.addObject("listTests",listTest);
        return returned;
    }

    
}
