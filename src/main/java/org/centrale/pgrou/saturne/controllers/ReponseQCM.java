/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

/**
 *
 * @author Mario
 */
public class ReponseQCM {
    private String ennonce;
    private Integer id;
    private Boolean correcte;

    public ReponseQCM() {
    }

    public ReponseQCM(String ennonce, Integer id, Boolean correcte) {
        this.ennonce = ennonce;
        this.id = id;
        this.correcte = correcte;
    }

    public String getEnnonce() {
        return ennonce;
    }

    public void setEnnonce(String ennonce) {
        this.ennonce = ennonce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(Boolean correcte) {
        this.correcte = correcte;
    }
    
    
}
