/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.Optional;
import org.centrale.pgrou.saturne.controllers.Security;
//import org.centrale.pgrou.saturne.controllers.User;
import org.centrale.pgrou.saturne.items.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louis-alexandre
 */
@Repository
public class PersonneCustomRepositoryImpl implements PersonneCustomRepository {

    @Autowired
    @Lazy
    PersonneRepository personneRepository;

    @Override
    public Personne create(String nom, String prenom) {
        return create(nom,prenom,null,null);
    }

    @Override
    public Personne create(String nom, String prenom, String login) {
        return create(nom,prenom,login,null);
    }

    @Override
    public Personne create(String nom, String prenom, String login, String motdepasse) {
        if((nom != null) && (prenom != null) && (!nom.isEmpty())){
            Personne pers;
            if (nom.equals("Administrateur")){
                pers = new Personne(1);
            } else {
                pers = new Personne();
            }
            pers.setNom(nom);
            pers.setPrenom(prenom);
            if((login != null) && (!login.isEmpty())){
                pers.setLogin(login);
            }
            if((motdepasse != null) && (!motdepasse.isEmpty())){
                pers.setMotdepasse(Security.encryptPassword(motdepasse));
            }
            personneRepository.save(pers);
            Optional<Personne> resultPerson = personneRepository.findById(pers.getPersonneid());
            if (resultPerson.isPresent()) {
                return resultPerson.get();
            }
        }
        return null;
    }

    @Override
    public Personne findByLogin(String login) {
        Collection<Personne> personneListe = personneRepository.findByPersonLogin(login);
        
        if(personneListe.size()==1){
            return personneListe.iterator().next();
        }
        return null;
    }

    @Override
    public Personne findByName(String nom, String prenom) {
        Collection<Personne> personneListe = personneRepository.findByPersonNomPrenom(nom,prenom);
        
        if(personneListe.size()==1){
            return personneListe.iterator().next();
        }
        return null;
    }
    
}
