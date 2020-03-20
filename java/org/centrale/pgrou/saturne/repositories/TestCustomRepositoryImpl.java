/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.centrale.pgrou.saturne.items.Test;
import org.centrale.pgrou.saturne.tools.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author louis-alexandre
 */
public class TestCustomRepositoryImpl implements TestCustomRepository {
    
    @Lazy
    @Autowired
    TestRepository testRepository;

    @Override
    public List<List<String>> affichageProchainsTests(String loginPers) {
        List<List<String>> returned = new ArrayList();
        
        Collection<Test> listTest = testRepository.getNextTestsByLogin(loginPers, Utilities.getCurrentDate());
        for(Test t:listTest){
            List<String> testText = new ArrayList();
            testText.add(t.getTestid().toString());
            testText.add(testRepository.getNomTest(t.getTestid()));
            testText.add(Utilities.asTimestamp(t.getDatedebuttest()));
            testText.add(Utilities.asTimestamp(t.getDatefintest()));
            testText.add(Utilities.getDate(t.getDureemaxtest(),"hh:mm:ss"));
            returned.add(testText);
        }
        return returned;
    }
    
}
