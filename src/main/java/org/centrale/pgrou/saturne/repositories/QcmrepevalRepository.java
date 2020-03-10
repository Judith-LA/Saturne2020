/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.List;
import org.centrale.pgrou.saturne.items.Qcmrepeval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface QcmrepevalRepository extends JpaRepository<Qcmrepeval,Integer>{
    @Query(value="SELECT cochee FROM reponse INNER JOIN qcmrep USING(reponseid) \n" +
"INNER JOIN qcmrepeval USING (qcmrepid) INNER JOIN evaluationreponse USING (evaluationreponseid) \n" +
"WHERE questionid=?1  AND evaluationid=?2 ORDER BY reponseid;",nativeQuery=true)
    public List<Boolean> findCochees(int idQuestion,int idEval);
}
