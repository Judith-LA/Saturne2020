/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.Date;
import java.util.List;
import org.centrale.pgrou.saturne.items.Qcm;
import org.centrale.pgrou.saturne.items.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public interface QcmRepository extends JpaRepository<Qcm,Integer>{
    @Query(value="SELECT * FROM public.qcm \n" +
            "WHERE questionid=?1",nativeQuery=true)
    //List<Test> findWithParameters(@Param("date")Date date,@Param("personne")Personne personne);
    public Qcm findWithParameters(int id);
}
