/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.List;

/**
 *
 * @author louis-alexandre
 */
public interface TestCustomRepository {
    
    /**
     * Renvoie la liste des tests disponibles à passer
     * @param loginPers
     * @return 
     */
    public List<List<String>> affichageProchainsTests(String loginPers);

}
