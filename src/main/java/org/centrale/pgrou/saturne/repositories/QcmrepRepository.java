/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Date;
import java.util.List;
import org.centrale.pgrou.saturne.items.Qcmrep;
import org.centrale.pgrou.saturne.items.Question;
import org.centrale.pgrou.saturne.items.Reponse;
import org.centrale.pgrou.saturne.items.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface QcmrepRepository extends JpaRepository<Qcmrep,Integer>{
    @Query(value="SELECT * FROM qcmrep JOIN reponse USING (reponseid) WHERE questionid=?;",nativeQuery=true)
    //List<Test> findWithParameters(@Param("date")Date date,@Param("personne")Personne personne);
    public List<Qcmrep> findWithParameters(int idQues);
    @Query(value="SELECT r FROM Qcmrep r WHERE r.reponseid=:reponseid")
    List<Qcmrep> findWithParameter(@Param("reponseid")Reponse reponseid);
}
