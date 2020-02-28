/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.centrale.ldap.LDAPManager;
import org.centrale.ldap.LDAPUser;
import org.centrale.pgrou.saturne.items.Connexion;
import org.centrale.pgrou.saturne.items.Personne;
import org.centrale.pgrou.saturne.repositories.ConnexionRepository;
import org.centrale.pgrou.saturne.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author louis-alexandre
 */
@Controller
public class StartupController {
    
    @Autowired
    private ConnexionRepository connexionRepository;
    
    @Autowired
    private PersonneRepository personneRepository;
    
    @RequestMapping(value="index.do", method=RequestMethod.GET)
    public ModelAndView handleGet(){
        Security.check(connexionRepository);
        return new ModelAndView("index");
    }
    
    @RequestMapping(value="index.do", method=RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request){
        ModelAndView returned = new ModelAndView("index");
        Security.check(connexionRepository);
        
        String login = request.getParameter("user");
        String mdp = request.getParameter("password");
        
        Boolean identifiantsCorrects = false;
        
        if ((!login.isEmpty()) && (!mdp.isEmpty())){
            Personne person = personneRepository.findByLogin(login);
            
            //Règle 1 : utilisation du LDAP
            if((LDAPManager.identifyLDAP(login, mdp))) {
                identifiantsCorrects = true;
                if (person == null){
                    LDAPUser ldapUser = LDAPManager.searchLDAP(login, mdp);
                    person = new Personne();
                    person.setNom(ldapUser.getUserNom());
                    person.setPrenom(ldapUser.getUserPrenom());
                    person.setLogin(login);
                    if (ldapUser.getUserAffiliation()=="student"){
                        System.out.println("Ajouter role étudiant");
                    }
                    personneRepository.save(person);
                }
            }
            //Règle 2 : mot de passe dans la base de données
            else if((person != null)
                && (person.getMotdepasse()!= null) && (!person.getMotdepasse().isEmpty())
                && (Security.validatePassword(mdp, Security.encryptPassword(person.getMotdepasse())))) {
                identifiantsCorrects = true;
            }
            //Si les identifiants sont corrects, on connecte l'utilisateur
            if(identifiantsCorrects){
                Connexion connexion = connexionRepository.create(request, person);
            
                List<Personne> listPers = personneRepository.findAll();
                List<Connexion> listCo = connexionRepository.findAll();
                String code = connexion.getConnexionid();

                returned = new ModelAndView("enjoy");
                returned.addObject("listPers",listPers);
                returned.addObject("listCo",listCo);
                returned.addObject("code", code);
            }
        }
        
        return returned;
    }
    
    /**@RequestMapping(value="index.do", method=RequestMethod.POST)
    public ModelAndView handlePost(HttpServletRequest request, @ModelAttribute("User")User anUser){
        ModelAndView returned = new ModelAndView("index");
        Security.check(connexionRepository);
        
        if ((!anUser.getUser().isEmpty()) && (!anUser.getPasswd().isEmpty())){
            
            String login = anUser.getUser();
            String mdp = anUser.getPasswd();
            Boolean identifiantsCorrects = false;
            Personne person = personneRepository.findByLogin(login);
            
            //Règle 1 : utilisation du LDAP
            if((LDAPManager.identifyLDAP(login, mdp))) {
                identifiantsCorrects = true;
            }
            //Règle 2 : mot de passe dans la base de données
            else if((person != null)
                && (person.getMotdepasse()!= null) && (!person.getMotdepasse().isEmpty())
                && (Security.validatePassword(mdp, Security.encryptPassword(person.getMotdepasse())))) {
                identifiantsCorrects = true;
            }
            //Si les identifiants sont corrects, on connecte l'utilisateur
            if(identifiantsCorrects){
                if(person != null){
                    Connexion connexion = connexionRepository.create(request, person);
                }
            
                List<Personne> listPers = personneRepository.findAll();
                List<Connexion> listCo = connexionRepository.findAll();

                returned = new ModelAndView("enjoy");
                returned.addObject("listPers",listPers);
                returned.addObject("listCo",listCo);
            }
            
            if((anUser.getUser().equals("ju")) && (anUser.getPasswd().equals("ju"))){
                //Personne person = personneRepository.findByLogin(anUser.getUser());
                Connexion connexion = connexionRepository.create(request, person);
            
                List<Personne> listPers = personneRepository.findAll();
                List<Connexion> listCo = connexionRepository.findAll();

                returned = new ModelAndView("enjoy");
                returned.addObject("listPers",listPers);
                returned.addObject("listCo",listCo);
            }
        }
        
        return returned;
    }**/
}
