/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.controllers.Security;
import org.centrale.pgrou.saturne.items.Connexion;
import org.centrale.pgrou.saturne.items.Personne;
import org.centrale.pgrou.saturne.tools.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author louis-alexandre
 */
@Repository
public class ConnexionCustomRepositoryImpl implements ConnexionCustomRepository {
    
    private static final long DELTA = 30 * 60 * 1000L;

    @Autowired
    @Lazy
    ConnexionRepository connexionRepository;

    private static String createCode(String login, Date now) {
        StringBuilder newCode = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

        for (int i = 0; i < 5; i++) {
            char c = (char) ('A' + (int) (Math.random() * 26));
            newCode.append(c);
        }
        newCode.append(login.toUpperCase().charAt(0));
        newCode.append(sdf.format(now));
        for (int i = 0; i < 3; i++) {
            char c = (char) ('A' + (int) (Math.random() * 26));
            newCode.append(c);
        }
        return newCode.toString();
    }

    @Override
    public Connexion create(HttpServletRequest request, Personne person) {
        Connexion connexion = null;
        
        Date now = Utilities.getCurrentTime();
        Date expire = Utilities.getCurrentTime();
        expire.setTime(now.getTime() + DELTA);
        
        String newCode = createCode(person.getLogin(), now);
        
        connexion = new Connexion();
        connexion.setConnexionid(newCode);
        connexion.setDebutconnexion(now);
        connexion.setFinconnexion(expire);
        connexion.setIp(Security.getClientIpAddress(request));
        connexion.setPersonneid(person);
        
        connexionRepository.save(connexion);
        
        return connexion;
    }

    @Override
    public void removeOld() {
        Collection<Connexion> liste = connexionRepository.findAllBefore(Utilities.getCurrentTime());
        connexionRepository.deleteAll(liste);
    }

    @Override
    public void expand(Connexion connexion) {
        Date now = Utilities.getCurrentTime();
        Date expire = Utilities.getCurrentTime();
        expire.setTime(now.getTime() + DELTA);
        
        if (expire.after(connexion.getFinconnexion())){
            connexion.setFinconnexion(expire);
            connexionRepository.save(connexion);
        }
    }
    
}
