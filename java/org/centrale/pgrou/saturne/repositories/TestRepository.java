/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    
    @Query(value="SELECT * FROM public.test \n" +
            "INNER JOIN contenugroupe\n" +
            "USING (groupeid)\n" +
            "WHERE personneid=?2 AND datedebuttest<=?1 AND datefintest>=?1;",nativeQuery=true)
    //List<Test> findWithParameters(@Param("date")Date date,@Param("personne")Personne personne);
    public List<Test> findWithParameters(Date date,int personne);
//Rajouter le groupe: donc le récupérer -> voir comment faire puisqu'une personne a plusieurs groupe mais 1 test en a 1 

    @Query(value="SELECT * FROM Test "
            + "INNER JOIN Quiz USING (quizid) "
            + "WHERE Quiz.personneid = ?1", nativeQuery=true)
    public List<Test> findWithPersonne(Integer personneid);
}
