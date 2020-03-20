/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.List;
import org.centrale.pgrou.saturne.items.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{
    
    @Query(value="SELECT * FROM Question "
            + "WHERE personneid = ?1", nativeQuery=true)
    public Collection<Question> findWithPersonne(Integer id);
    
    @Query(value="SELECT question.* FROM question INNER JOIN motclequestion "
            + "USING (questionid) "
            + "WHERE motcleid=?1 AND estprivee=false;", nativeQuery=true)
    public List<Question> findWithMotCle(int id);
    
    @Query(value="SELECT question.* FROM question INNER JOIN motclequestion "
            + "USING (questionid) "
            + "WHERE motcleid=?1", nativeQuery=true)
    public List<Question> findWithMotCleProf(int id);
}
