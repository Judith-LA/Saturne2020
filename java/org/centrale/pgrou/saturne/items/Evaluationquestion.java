/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "evaluationquestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluationquestion.findAll", query = "SELECT e FROM Evaluationquestion e"),
    @NamedQuery(name = "Evaluationquestion.findByEvaluationid", query = "SELECT e FROM Evaluationquestion e WHERE e.evaluationquestionPK.evaluationid = :evaluationid"),
    @NamedQuery(name = "Evaluationquestion.findByContenuquizid", query = "SELECT e FROM Evaluationquestion e WHERE e.evaluationquestionPK.contenuquizid = :contenuquizid"),
    @NamedQuery(name = "Evaluationquestion.findByNotequestion", query = "SELECT e FROM Evaluationquestion e WHERE e.notequestion = :notequestion")})
public class Evaluationquestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluationquestionPK evaluationquestionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "notequestion")
    private Float notequestion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluationquestion")
    private Collection<Evaluationreponse> evaluationreponseCollection;
    @JoinColumn(name = "contenuquizid", referencedColumnName = "contenuquizid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Contenuquiz contenuquiz;
    @JoinColumn(name = "evaluationid", referencedColumnName = "evaluationid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evaluation evaluation;

    public Evaluationquestion() {
    }

    public Evaluationquestion(EvaluationquestionPK evaluationquestionPK) {
        this.evaluationquestionPK = evaluationquestionPK;
    }

    public Evaluationquestion(int evaluationid, int contenuquizid) {
        this.evaluationquestionPK = new EvaluationquestionPK(evaluationid, contenuquizid);
    }

    public EvaluationquestionPK getEvaluationquestionPK() {
        return evaluationquestionPK;
    }

    public void setEvaluationquestionPK(EvaluationquestionPK evaluationquestionPK) {
        this.evaluationquestionPK = evaluationquestionPK;
    }

    public Float getNotequestion() {
        return notequestion;
    }

    public void setNotequestion(Float notequestion) {
        this.notequestion = notequestion;
    }

    @XmlTransient
    public Collection<Evaluationreponse> getEvaluationreponseCollection() {
        return evaluationreponseCollection;
    }

    public void setEvaluationreponseCollection(Collection<Evaluationreponse> evaluationreponseCollection) {
        this.evaluationreponseCollection = evaluationreponseCollection;
    }

    public Contenuquiz getContenuquiz() {
        return contenuquiz;
    }

    public void setContenuquiz(Contenuquiz contenuquiz) {
        this.contenuquiz = contenuquiz;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationquestionPK != null ? evaluationquestionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluationquestion)) {
            return false;
        }
        Evaluationquestion other = (Evaluationquestion) object;
        if ((this.evaluationquestionPK == null && other.evaluationquestionPK != null) || (this.evaluationquestionPK != null && !this.evaluationquestionPK.equals(other.evaluationquestionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Evaluationquestion[ evaluationquestionPK=" + evaluationquestionPK + " ]";
    }
    
}
