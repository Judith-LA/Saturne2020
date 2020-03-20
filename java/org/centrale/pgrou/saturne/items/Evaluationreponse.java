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
import javax.persistence.JoinColumns;
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
@Table(name = "evaluationreponse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluationreponse.findAll", query = "SELECT e FROM Evaluationreponse e"),
    @NamedQuery(name = "Evaluationreponse.findByEvaluationreponseid", query = "SELECT e FROM Evaluationreponse e WHERE e.evaluationreponseid = :evaluationreponseid"),
    @NamedQuery(name = "Evaluationreponse.findByJuste", query = "SELECT e FROM Evaluationreponse e WHERE e.juste = :juste")})
public class Evaluationreponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "evaluationreponseid")
    private Integer evaluationreponseid;
    @Column(name = "juste")
    private Boolean juste;
    @JoinColumns({
        @JoinColumn(name = "evaluationid", referencedColumnName = "evaluationid"),
        @JoinColumn(name = "contenuquizid", referencedColumnName = "contenuquizid")})
    @ManyToOne(optional = false)
    private Evaluationquestion evaluationquestion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluationreponseid")
    private Collection<Qcmrepeval> qcmrepevalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluationreponseid")
    private Collection<Intervallerepeval> intervallerepevalCollection;

    public Evaluationreponse() {
    }

    public Evaluationreponse(Integer evaluationreponseid) {
        this.evaluationreponseid = evaluationreponseid;
    }

    public Integer getEvaluationreponseid() {
        return evaluationreponseid;
    }

    public void setEvaluationreponseid(Integer evaluationreponseid) {
        this.evaluationreponseid = evaluationreponseid;
    }

    public Boolean getJuste() {
        return juste;
    }

    public void setJuste(Boolean juste) {
        this.juste = juste;
    }

    public Evaluationquestion getEvaluationquestion() {
        return evaluationquestion;
    }

    public void setEvaluationquestion(Evaluationquestion evaluationquestion) {
        this.evaluationquestion = evaluationquestion;
    }

    @XmlTransient
    public Collection<Qcmrepeval> getQcmrepevalCollection() {
        return qcmrepevalCollection;
    }

    public void setQcmrepevalCollection(Collection<Qcmrepeval> qcmrepevalCollection) {
        this.qcmrepevalCollection = qcmrepevalCollection;
    }

    @XmlTransient
    public Collection<Intervallerepeval> getIntervallerepevalCollection() {
        return intervallerepevalCollection;
    }

    public void setIntervallerepevalCollection(Collection<Intervallerepeval> intervallerepevalCollection) {
        this.intervallerepevalCollection = intervallerepevalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationreponseid != null ? evaluationreponseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluationreponse)) {
            return false;
        }
        Evaluationreponse other = (Evaluationreponse) object;
        if ((this.evaluationreponseid == null && other.evaluationreponseid != null) || (this.evaluationreponseid != null && !this.evaluationreponseid.equals(other.evaluationreponseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Evaluationreponse[ evaluationreponseid=" + evaluationreponseid + " ]";
    }
    
}
