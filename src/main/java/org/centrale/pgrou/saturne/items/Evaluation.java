/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "evaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e"),
    @NamedQuery(name = "Evaluation.findByEvaluationid", query = "SELECT e FROM Evaluation e WHERE e.evaluationid = :evaluationid"),
    @NamedQuery(name = "Evaluation.findByDatedebutevaluation", query = "SELECT e FROM Evaluation e WHERE e.datedebutevaluation = :datedebutevaluation"),
    @NamedQuery(name = "Evaluation.findByDureeevaluation", query = "SELECT e FROM Evaluation e WHERE e.dureeevaluation = :dureeevaluation"),
    @NamedQuery(name = "Evaluation.findByNote", query = "SELECT e FROM Evaluation e WHERE e.note = :note")})
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "evaluationid")
    private Integer evaluationid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datedebutevaluation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedebutevaluation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dureeevaluation")
    @Temporal(TemporalType.TIME)
    private Date dureeevaluation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "note")
    private Float note;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluation")
    private Collection<Evaluationquestion> evaluationquestionCollection;
    @JoinColumn(name = "personneid", referencedColumnName = "personneid")
    @ManyToOne(optional = false)
    private Personne personneid;
    @JoinColumn(name = "testid", referencedColumnName = "testid")
    @ManyToOne(optional = false)
    private Test testid;

    public Evaluation() {
    }

    public Evaluation(Integer evaluationid) {
        this.evaluationid = evaluationid;
    }

    public Evaluation(Integer evaluationid, Date datedebutevaluation, Date dureeevaluation) {
        this.evaluationid = evaluationid;
        this.datedebutevaluation = datedebutevaluation;
        this.dureeevaluation = dureeevaluation;
    }

    public Integer getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(Integer evaluationid) {
        this.evaluationid = evaluationid;
    }

    public Date getDatedebutevaluation() {
        return datedebutevaluation;
    }

    public void setDatedebutevaluation(Date datedebutevaluation) {
        this.datedebutevaluation = datedebutevaluation;
    }

    public Date getDureeevaluation() {
        return dureeevaluation;
    }

    public void setDureeevaluation(Date dureeevaluation) {
        this.dureeevaluation = dureeevaluation;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @XmlTransient
    public Collection<Evaluationquestion> getEvaluationquestionCollection() {
        return evaluationquestionCollection;
    }

    public void setEvaluationquestionCollection(Collection<Evaluationquestion> evaluationquestionCollection) {
        this.evaluationquestionCollection = evaluationquestionCollection;
    }

    public Personne getPersonneid() {
        return personneid;
    }

    public void setPersonneid(Personne personneid) {
        this.personneid = personneid;
    }

    public Test getTestid() {
        return testid;
    }

    public void setTestid(Test testid) {
        this.testid = testid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationid != null ? evaluationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.evaluationid == null && other.evaluationid != null) || (this.evaluationid != null && !this.evaluationid.equals(other.evaluationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Evaluation[ evaluationid=" + evaluationid + " ]";
    }
    
}
