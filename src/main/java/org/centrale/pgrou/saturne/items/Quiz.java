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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "quiz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q"),
    @NamedQuery(name = "Quiz.findByQuizid", query = "SELECT q FROM Quiz q WHERE q.quizid = :quizid"),
    @NamedQuery(name = "Quiz.findByNomquiz", query = "SELECT q FROM Quiz q WHERE q.nomquiz = :nomquiz"),
    @NamedQuery(name = "Quiz.findByDatecreationquiz", query = "SELECT q FROM Quiz q WHERE q.datecreationquiz = :datecreationquiz")})
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "quizid")
    private Integer quizid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nomquiz")
    private String nomquiz;
    @Column(name = "datecreationquiz")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreationquiz;
    @JoinColumn(name = "personneid", referencedColumnName = "personneid")
    @ManyToOne(optional = false)
    private Personne personneid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quizid")
    private Collection<Test> testCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quizid")
    private Collection<Contenuquiz> contenuquizCollection;

    public Quiz() {
    }

    public Quiz(Integer quizid) {
        this.quizid = quizid;
    }

    public Quiz(Integer quizid, String nomquiz) {
        this.quizid = quizid;
        this.nomquiz = nomquiz;
    }

    public Integer getQuizid() {
        return quizid;
    }

    public void setQuizid(Integer quizid) {
        this.quizid = quizid;
    }

    public String getNomquiz() {
        return nomquiz;
    }

    public void setNomquiz(String nomquiz) {
        this.nomquiz = nomquiz;
    }

    public Date getDatecreationquiz() {
        return datecreationquiz;
    }

    public void setDatecreationquiz(Date datecreationquiz) {
        this.datecreationquiz = datecreationquiz;
    }

    public Personne getPersonneid() {
        return personneid;
    }

    public void setPersonneid(Personne personneid) {
        this.personneid = personneid;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @XmlTransient
    public Collection<Contenuquiz> getContenuquizCollection() {
        return contenuquizCollection;
    }

    public void setContenuquizCollection(Collection<Contenuquiz> contenuquizCollection) {
        this.contenuquizCollection = contenuquizCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quizid != null ? quizid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.quizid == null && other.quizid != null) || (this.quizid != null && !this.quizid.equals(other.quizid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Quiz[ quizid=" + quizid + " ]";
    }
    
}
