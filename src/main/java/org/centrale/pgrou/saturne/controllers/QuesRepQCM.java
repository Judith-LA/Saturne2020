/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marion PGROU
 */
public class QuesRepQCM {
    private String enonceQues;
    private Integer ordre;
    private Integer idQues;
    private List<ReponseQCM> listeReponses;
    private Boolean repUni;

    public QuesRepQCM(String enonceQues, Integer idQues, List<ReponseQCM> listeReponses, Boolean repUni, Integer ordre) {
        this.enonceQues = enonceQues;
        this.idQues = idQues;
        this.listeReponses = listeReponses;
        this.repUni = repUni;
        this.ordre = ordre;
    }

    public QuesRepQCM() {
        this.listeReponses = new ArrayList();
    }

    public String getEnonceQues() {
        return enonceQues;
    }

    public void setEnonceQues(String enonceQues) {
        this.enonceQues = enonceQues;
    }

    public Integer getIdQues() {
        return idQues;
    }

    public void setIdQues(Integer idQues) {
        this.idQues = idQues;
    }

    public List<ReponseQCM> getListeReponses() {
        return listeReponses;
    }

    public void setListeReponses(List<ReponseQCM> listeReponses) {
        this.listeReponses = listeReponses;
    }

    public Boolean getRepUni() {
        return repUni;
    }

    public void setRepUni(Boolean repUni) {
        this.repUni = repUni;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }
    
    
    
}
