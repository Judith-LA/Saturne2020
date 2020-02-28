/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import org.centrale.pgrou.saturne.items.Role;

/**
 *
 * @author louis-alexandre
 */
public interface RoleCustomRepository {
    /**
     * Crée un nouveau rôle
     * @param nom
     * @return 
     */
    public Role create(String nom);
    
    /**
     * Lie un rôle et un menu
     * @param role
     * @param menu 
     */
    //public void linkRoleAndMenu(Role role, Menu menu);
    
    /**
     * Trouve un rôle à partir de son identifiant
     * @param id
     * @return 
     */
    public Role findOneById(Integer id);

    /**
     * Trouve un rôle à partir de son libellé
     * @param label
     * @return 
     */
    public Role findOneByLabel(String label);
}
