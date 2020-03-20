/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author louis-alexandre
 */
@Embeddable
public class EvaluationquestionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "evaluationid")
    private int evaluationid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contenuquizid")
    private int contenuquizid;

    public EvaluationquestionPK() {
    }

    public EvaluationquestionPK(int evaluationid, int contenuquizid) {
        this.evaluationid = evaluationid;
        this.contenuquizid = contenuquizid;
    }

    public int getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(int evaluationid) {
        this.evaluationid = evaluationid;
    }

    public int getContenuquizid() {
        return contenuquizid;
    }

    public void setContenuquizid(int contenuquizid) {
        this.contenuquizid = contenuquizid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) evaluationid;
        hash += (int) contenuquizid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationquestionPK)) {
            return false;
        }
        EvaluationquestionPK other = (EvaluationquestionPK) object;
        if (this.evaluationid != other.evaluationid) {
            return false;
        }
        if (this.contenuquizid != other.contenuquizid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.EvaluationquestionPK[ evaluationid=" + evaluationid + ", contenuquizid=" + contenuquizid + " ]";
    }
    
}
