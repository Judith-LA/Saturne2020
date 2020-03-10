/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.Date;
import org.centrale.pgrou.saturne.items.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author louis-alexandre
 */
public interface TestRepository extends JpaRepository<Test, Integer>, TestCustomRepository {
    
    @Query(value="SELECT * FROM Test "
            + "INNER JOIN Contenugroupe USING (groupeid) "
            + "INNER JOIN Personne USING (personneid) "
            + "WHERE Personne.login = ?1", nativeQuery=true)
    public Collection<Test> getAllTestByLogin(String loginPers);
    
    @Query(value="SELECT * FROM Test "
            + "INNER JOIN Contenugroupe USING (groupeid) "
            + "INNER JOIN Personne USING (personneid) "
            + "WHERE Personne.login = ?1 "
            + "AND (Test.datedebuttest <= ?2 AND Test.datefintest > ?2)", nativeQuery=true)
    public Collection<Test> getNextTestsByLogin(String loginPers, Date date);
    
    @Query(value="SELECT nomquiz FROM Quiz "
            + "INNER JOIN Test USING (quizid) "
            + "WHERE Test.testid = ?1", nativeQuery=true)
    public String getNomTest(Integer testId);
}
