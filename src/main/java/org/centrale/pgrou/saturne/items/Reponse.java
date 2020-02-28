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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "reponse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reponse.findAll", query = "SELECT r FROM Reponse r"),
    @NamedQuery(name = "Reponse.findByReponseid", query = "SELECT r FROM Reponse r WHERE r.reponseid = :reponseid"),
    @NamedQuery(name = "Reponse.findByCorrecte", query = "SELECT r FROM Reponse r WHERE r.correcte = :correcte")})
public class Reponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reponseid")
    private Integer reponseid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correcte")
    private boolean correcte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reponseid")
    private Collection<Intervallerep> intervallerepCollection;
    @JoinColumn(name = "questionid", referencedColumnName = "questionid")
    @ManyToOne(optional = false)
    private Question questionid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reponseid")
    private Collection<Qcmrep> qcmrepCollection;

    public Reponse() {
    }

    public Reponse(Integer reponseid) {
        this.reponseid = reponseid;
    }

    public Reponse(Integer reponseid, boolean correcte) {
        this.reponseid = reponseid;
        this.correcte = correcte;
    }

    public Integer getReponseid() {
        return reponseid;
    }

    public void setReponseid(Integer reponseid) {
        this.reponseid = reponseid;
    }

    public boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(boolean correcte) {
        this.correcte = correcte;
    }

    @XmlTransient
    public Collection<Intervallerep> getIntervallerepCollection() {
        return intervallerepCollection;
    }

    public void setIntervallerepCollection(Collection<Intervallerep> intervallerepCollection) {
        this.intervallerepCollection = intervallerepCollection;
    }

    public Question getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Question questionid) {
        this.questionid = questionid;
    }

    @XmlTransient
    public Collection<Qcmrep> getQcmrepCollection() {
        return qcmrepCollection;
    }

    public void setQcmrepCollection(Collection<Qcmrep> qcmrepCollection) {
        this.qcmrepCollection = qcmrepCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reponseid != null ? reponseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reponse)) {
            return false;
        }
        Reponse other = (Reponse) object;
        if ((this.reponseid == null && other.reponseid != null) || (this.reponseid != null && !this.reponseid.equals(other.reponseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Reponse[ reponseid=" + reponseid + " ]";
    }
    
}
