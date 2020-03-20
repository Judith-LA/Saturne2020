/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import org.centrale.pgrou.saturne.items.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author louis-alexandre
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, RoleCustomRepository{
    @Query(name = "Role.findByLibelle")
    public Collection<Role> findbyRoleLabel(@Param("libelle")String label);
}
