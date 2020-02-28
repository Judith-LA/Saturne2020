/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import org.centrale.pgrou.saturne.controllers.User;
import org.centrale.pgrou.saturne.items.Personne;

/**
 *
 * @author louis-alexandre
 */
public interface PersonneCustomRepository {
    /**
     * Crée une nouvelle personne
     * @param nom
     * @param prenom
     * @return 
     */
    public Personne create(String nom, String prenom);
    
    /**
     * Crée une nouvelle personne
     * @param nom
     * @param prenom
     * @param login
     * @return 
     */
    public Personne create(String nom, String prenom, String login);
    
    /**
     * Crée une nouvelle personne
     * @param nom
     * @param prenom
     * @param login
     * @param motdepasse
     * @return 
     */
    public Personne create(String nom, String prenom, String login, String motdepasse);
    
    /**
     * Renvoie la personne correspondant à un login donné
     * @param login
     * @return 
     */
    public Personne findByLogin(String login);
    
    /**
     * Renvoie la personne correspondant aux nom et prénom donnés
     * @param nom
     * @param prenom
     * @return 
     */
    public Personne findByName(String nom, String prenom);
    
}
