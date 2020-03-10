/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.Date;
import org.centrale.pgrou.saturne.items.Connexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author louis-alexandre
 */
public interface ConnexionRepository extends JpaRepository<Connexion, String>, ConnexionCustomRepository{
    @Query(value="SELECT * FROM Connexion WHERE finconnexion < ?1", nativeQuery=true)
    public Collection<Connexion> findAllExpireBefore(Date expireDate);
}
