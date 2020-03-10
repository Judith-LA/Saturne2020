/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;


import org.centrale.pgrou.saturne.items.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface NotationRepository extends JpaRepository<Notation,Integer>{
    @Query(value="SELECT notationid FROM evaluation INNER JOIN test USING (testid) WHERE evaluationid =?1;",nativeQuery=true)
    public int findNotationid(int idEval);
}
