/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import javax.servlet.http.HttpServletRequest;
import org.centrale.pgrou.saturne.items.Connexion;
import org.centrale.pgrou.saturne.items.Personne;

/**
 *
 * @author louis-alexandre
 */
public interface ConnexionCustomRepository {
    
    /**
     * Création d'une nouvelle connexion pour une personne donnée
     * @param request
     * @param person
     * @return 
     */
    public Connexion create(HttpServletRequest request, Personne person);
    
    /**
     * Supprime les anciennes données
     */
    public void removeOld();
    
    /**
     * Décale la date de fin de connexion
     * @param connection 
     */
    public void expand(Connexion connection);
    
}
