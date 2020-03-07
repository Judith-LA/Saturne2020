/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import org.centrale.pgrou.saturne.items.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author louis-alexandre
 */
public interface PersonneRepository extends JpaRepository<Personne, Integer>, PersonneCustomRepository{
    @Query(name = "Personne.findByLogin")
    public Collection<Personne> findByPersonLogin(@Param("login")String personneLogin);
    
    @Query(value="SELECT p FROM Personne WHERE p.nom = ?1 AND p.prenom = ?2", nativeQuery=true)
    public Collection<Personne> findByPersonNomPrenom(@Param("nom")String nom, @Param("prenom")String prenom);
}
