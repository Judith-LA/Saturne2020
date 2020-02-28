/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.Optional;
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

    @Override
    public Role create(String nom) {
        if ((nom != null) && (!nom.isEmpty())) {
            Role role = new Role();
            role.setLibelle(nom);
            roleRepository.save(role);

            Optional<Role> resultRole = roleRepository.findById(role.getRoleid());
            if (resultRole.isPresent()) {
                return resultRole.get();
            }
        }
        return null;
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
