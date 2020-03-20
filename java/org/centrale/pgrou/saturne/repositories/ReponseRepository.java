/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.List;
import org.centrale.pgrou.saturne.items.Question;
import org.centrale.pgrou.saturne.items.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Integer>{
    @Query(value="SELECT r FROM Reponse r WHERE r.questionid=:questionid")
    List<Reponse> findWithParameter(@Param("questionid")Question questionid);
    
    @Query(value="SELECT correcte FROM reponse WHERE questionid=?1 ORDER BY reponseid;",nativeQuery=true)
    public List<Boolean> findCorrectes(int idQuestion);
}
