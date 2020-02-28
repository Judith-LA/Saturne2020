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
@Table(name = "qcmrepeval")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qcmrepeval.findAll", query = "SELECT q FROM Qcmrepeval q"),
    @NamedQuery(name = "Qcmrepeval.findByQcmrepevalid", query = "SELECT q FROM Qcmrepeval q WHERE q.qcmrepevalid = :qcmrepevalid"),
    @NamedQuery(name = "Qcmrepeval.findByCochee", query = "SELECT q FROM Qcmrepeval q WHERE q.cochee = :cochee")})
public class Qcmrepeval implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qcmrepevalid")
    private Integer qcmrepevalid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cochee")
    private boolean cochee;
    @JoinColumn(name = "evaluationreponseid", referencedColumnName = "evaluationreponseid")
    @ManyToOne(optional = false)
    private Evaluationreponse evaluationreponseid;
    @JoinColumn(name = "qcmrepid", referencedColumnName = "qcmrepid")
    @ManyToOne(optional = false)
    private Qcmrep qcmrepid;

    public Qcmrepeval() {
    }

    public Qcmrepeval(Integer qcmrepevalid) {
        this.qcmrepevalid = qcmrepevalid;
    }

    public Qcmrepeval(Integer qcmrepevalid, boolean cochee) {
        this.qcmrepevalid = qcmrepevalid;
        this.cochee = cochee;
    }

    public Integer getQcmrepevalid() {
        return qcmrepevalid;
    }

    public void setQcmrepevalid(Integer qcmrepevalid) {
        this.qcmrepevalid = qcmrepevalid;
    }

    public boolean getCochee() {
        return cochee;
    }

    public void setCochee(boolean cochee) {
        this.cochee = cochee;
    }

    public Evaluationreponse getEvaluationreponseid() {
        return evaluationreponseid;
    }

    public void setEvaluationreponseid(Evaluationreponse evaluationreponseid) {
        this.evaluationreponseid = evaluationreponseid;
    }

    public Qcmrep getQcmrepid() {
        return qcmrepid;
    }

    public void setQcmrepid(Qcmrep qcmrepid) {
        this.qcmrepid = qcmrepid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qcmrepevalid != null ? qcmrepevalid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qcmrepeval)) {
            return false;
        }
        Qcmrepeval other = (Qcmrepeval) object;
        if ((this.qcmrepevalid == null && other.qcmrepevalid != null) || (this.qcmrepevalid != null && !this.qcmrepevalid.equals(other.qcmrepevalid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Qcmrepeval[ qcmrepevalid=" + qcmrepevalid + " ]";
    }
    
}
