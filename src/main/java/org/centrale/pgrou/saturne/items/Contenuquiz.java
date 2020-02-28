/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "contenuquiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenuquiz.findAll", query = "SELECT c FROM Contenuquiz c"),
    @NamedQuery(name = "Contenuquiz.findByContenuquizid", query = "SELECT c FROM Contenuquiz c WHERE c.contenuquizid = :contenuquizid"),
    @NamedQuery(name = "Contenuquiz.findByNombrepoints", query = "SELECT c FROM Contenuquiz c WHERE c.nombrepoints = :nombrepoints")})
public class Contenuquiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contenuquizid")
    private Integer contenuquizid;
    @Column(name = "nombrepoints")
    private Integer nombrepoints;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contenuquiz")
    private Collection<Evaluationquestion> evaluationquestionCollection;
    @JoinColumn(name = "questionid", referencedColumnName = "questionid")
    @ManyToOne(optional = false)
    private Question questionid;
    @JoinColumn(name = "quizid", referencedColumnName = "quizid")
    @ManyToOne(optional = false)
    private Quiz quizid;

    public Contenuquiz() {
    }

    public Contenuquiz(Integer contenuquizid) {
        this.contenuquizid = contenuquizid;
    }

    public Integer getContenuquizid() {
        return contenuquizid;
    }

    public void setContenuquizid(Integer contenuquizid) {
        this.contenuquizid = contenuquizid;
    }

    public Integer getNombrepoints() {
        return nombrepoints;
    }

    public void setNombrepoints(Integer nombrepoints) {
        this.nombrepoints = nombrepoints;
    }

    @XmlTransient
    public Collection<Evaluationquestion> getEvaluationquestionCollection() {
        return evaluationquestionCollection;
    }

    public void setEvaluationquestionCollection(Collection<Evaluationquestion> evaluationquestionCollection) {
        this.evaluationquestionCollection = evaluationquestionCollection;
    }

    public Question getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Question questionid) {
        this.questionid = questionid;
    }

    public Quiz getQuizid() {
        return quizid;
    }

    public void setQuizid(Quiz quizid) {
        this.quizid = quizid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contenuquizid != null ? contenuquizid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenuquiz)) {
            return false;
        }
        Contenuquiz other = (Contenuquiz) object;
        if ((this.contenuquizid == null && other.contenuquizid != null) || (this.contenuquizid != null && !this.contenuquizid.equals(other.contenuquizid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Contenuquiz[ contenuquizid=" + contenuquizid + " ]";
    }
    
}
