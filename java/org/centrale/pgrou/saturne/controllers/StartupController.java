/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.centrale.ldap.LDAPManager;
import org.centrale.ldap.LDAPUser;
import org.centrale.pgrou.saturne.items.*;
import org.centrale.pgrou.saturne.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private TestRepository testRepository;
    
    @RequestMapping(value="index.do", method=RequestMethod.GET)
    public ModelAndView handleGet(HttpServletRequest request){
        ModelAndView returned = new ModelAndView("index");
        String code = request.getParameter("code");
        if(code!=null){
            Optional<Connexion> coResult = connexionRepository.findById(code);
            if(!coResult.isEmpty()){
                Connexion connexion = coResult.get();
                Personne pers = connexion.getPersonneid();
                Role admin = roleRepository.findOneById(1);
                Role teacher = roleRepository.findOneById(2);
                if(pers.getRoleCollection().contains(admin)||pers.getRoleCollection().contains(teacher)){
                    returned = new ModelAndView("AccueilProfesseur");
                } else {
                    returned = new ModelAndView("AccueilEtudiant");
                }
                Security.setDefaultData(returned, connexion);
            }
        } else {
            ApplicationInitializer.init(personneRepository, roleRepository, menuRepository);
            Security.check(connexionRepository);
        }
        Security.check(connexionRepository);
        return returned;
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
                    if (ldapUser.getUserAffiliation().equals("student")){
                        Role role = roleRepository.findOneByLabel("Student");
                        Collection<Role> roleCollection = new ArrayList();
                        roleCollection.add(role);
                        person.setRoleCollection(roleCollection);
                        Collection<Personne> persCollection = new ArrayList();
                        persCollection.add(person);
                        role.setPersonneCollection(persCollection);
                    }
                    personneRepository.save(person);
                }
            }
            //Règle 2 : mot de passe dans la base de données
            else if((person != null)
                && (person.getMotdepasse()!= null) && (!person.getMotdepasse().isEmpty())
                && (Security.validatePassword(mdp, person.getMotdepasse()))) {
                identifiantsCorrects = true;
            }
            //Règle 3 :l'administrateur doit être dans la base de données
            else if((person == null)
                && (login.equals(ApplicationInitializer.TRAPLOGIN)) 
                && (mdp.equals(ApplicationInitializer.TRAPPASS))) {
                ApplicationInitializer.createDefaultLogin(personneRepository, roleRepository);
                person = personneRepository.findByLogin(login);
                identifiantsCorrects = true;
            }
            //Si les identifiants sont corrects, on connecte l'utilisateur
            if(identifiantsCorrects){
                Connexion connexion = connexionRepository.create(request, person);
                
                Role admin = roleRepository.findOneById(1);
                Role teacher = roleRepository.findOneById(2);
                if (person != null && person.getRoleCollection() != null){
                    if(person.getRoleCollection().contains(admin) || person.getRoleCollection().contains(teacher)){
                        returned = new ModelAndView("AccueilProfesseur");
                    } else {
                        returned = new ModelAndView("AccueilEtudiant");
                        returned.addObject("listTests",testRepository.affichageProchainsTests(login));
                    }
                }
                Security.setDefaultData(returned, connexion);
            }
        }
        
        return returned;
    }
}
