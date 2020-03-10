/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

/**
 *
 * @author Marion PGROU
 */
public class TestAff {
    private int testId;
    private String nomQuiz;
    private String dureeStr;
    private int dureeInt;

    public TestAff(int testId, String nomQuiz) {
        this.testId = testId;
        this.nomQuiz = nomQuiz;
    }

    public TestAff() {
    }

    
    
    
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getNomQuiz() {
        return nomQuiz;
    }

    public void setNomQuiz(String nomQuiz) {
        this.nomQuiz = nomQuiz;
    }

    public String getDureeStr() {
        return dureeStr;
    }

    public void setDureeStr(String dureeStr) {
        this.dureeStr = dureeStr;
    }

    public int getDureeInt() {
        return dureeInt;
    }

    public void setDureeInt(int dureeInt) {
        this.dureeInt = dureeInt;
    }
            
    public int stringToInt(String duree){
        String heure = duree.substring(0, 2);
        int iHeure = Integer.parseInt(heure);
        String min = duree.substring(3, 5);
        int iMin = Integer.parseInt(min);
        return iHeure*3600+iMin*60;
    }
    
}
