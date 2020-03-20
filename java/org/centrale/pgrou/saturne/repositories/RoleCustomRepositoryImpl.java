/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.centrale.pgrou.saturne.items.Menu;
import org.centrale.pgrou.saturne.items.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author louis-alexandre
 */
public class RoleCustomRepositoryImpl implements RoleCustomRepository {

    @Autowired
    @Lazy
    RoleRepository roleRepository;
    
    @Autowired
    MenuRepository menuRepository;

    @Override
    public Role create(String nom, Integer id) {
        if ((nom != null) && (!nom.isEmpty())) {
            Role role = new Role();
            role.setLibelle(nom);
            role.setRoleid(id);
            roleRepository.save(role);
            Optional<Role> resultRole = roleRepository.findById(role.getRoleid());
            if (resultRole.isPresent()) {
                return resultRole.get();
            }
        }
        return null;
    }

    @Override
    public void linkRoleAndMenu(Role role, Menu menu) {
        if((role != null) && (menu != null)){
            Collection<Role> roleCollection = menu.getRoleCollection();
            Collection<Menu> menuCollection = role.getMenuCollection();
            
            if(!menuCollection.contains(menu)){
                if(roleCollection != null){
                    roleCollection.add(role);
                } else {
                    Collection<Role> newRoleCollection = new ArrayList();
                    newRoleCollection.add(role);
                    menu.setRoleCollection(roleCollection);
                }
                if(menuCollection != null){
                    menuCollection.add(menu);
                } else {
                    Collection<Menu> newMenuCollection = new ArrayList();
                    newMenuCollection.add(menu);
                    role.setMenuCollection(menuCollection);
                }
                roleRepository.save(role);
                menuRepository.save(menu);
            }
        }
    }

    @Override
    public Role findOneById(Integer id) {
        Role returnResult = null;
        Optional<Role> roleAdminResult = roleRepository.findById(id);
        if (roleAdminResult.isPresent()) {
            returnResult = roleAdminResult.get();
        }
        return returnResult;
    }

    @Override
    public Role findOneByLabel(String label) {
        Collection<Role> roleList = roleRepository.findbyRoleLabel(label);

        if (roleList.size() == 1) {
            return roleList.iterator().next();
        }
        return null;
    }
    
}
