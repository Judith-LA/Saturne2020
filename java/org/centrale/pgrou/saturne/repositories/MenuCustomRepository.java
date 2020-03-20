/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import org.centrale.pgrou.saturne.items.Menu;
import org.centrale.pgrou.saturne.items.Personne;

/**
 *
 * @author louis-alexandre
 */
public interface MenuCustomRepository {
    /**
     * Create new
     * @param menuTitle
     * @return 
     */
    public Menu create(String menuTitle);
    
    /**
     * check acess
     * @param person
     * @param action
     * @return 
     */
    public Menu checkAccess(Personne person, String action);
}
