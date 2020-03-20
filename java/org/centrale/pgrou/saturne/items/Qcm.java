/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "qcm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qcm.findAll", query = "SELECT q FROM Qcm q"),
    @NamedQuery(name = "Qcm.findByQcmid", query = "SELECT q FROM Qcm q WHERE q.qcmid = :qcmid"),
    @NamedQuery(name = "Qcm.findByRepunique", query = "SELECT q FROM Qcm q WHERE q.repunique = :repunique")})
public class Qcm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qcmid")
    private Integer qcmid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "repunique")
    private boolean repunique;
    @JoinColumn(name = "questionid", referencedColumnName = "questionid")
    @ManyToOne(optional = false)
    private Question questionid;

    public Qcm() {
    }

    public Qcm(Integer qcmid) {
        this.qcmid = qcmid;
    }

    public Qcm(Integer qcmid, boolean repunique) {
        this.qcmid = qcmid;
        this.repunique = repunique;
    }

    public Integer getQcmid() {
        return qcmid;
    }

    public void setQcmid(Integer qcmid) {
        this.qcmid = qcmid;
    }

    public boolean getRepunique() {
        return repunique;
    }

    public void setRepunique(boolean repunique) {
        this.repunique = repunique;
    }

    public Question getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Question questionid) {
        this.questionid = questionid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qcmid != null ? qcmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qcm)) {
            return false;
        }
        Qcm other = (Qcm) object;
        if ((this.qcmid == null && other.qcmid != null) || (this.qcmid != null && !this.qcmid.equals(other.qcmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Qcm[ qcmid=" + qcmid + " ]";
    }
    
}
