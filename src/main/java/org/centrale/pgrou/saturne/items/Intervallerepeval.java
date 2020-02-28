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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "intervallerepeval")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intervallerepeval.findAll", query = "SELECT i FROM Intervallerepeval i"),
    @NamedQuery(name = "Intervallerepeval.findByIntervallerepevalid", query = "SELECT i FROM Intervallerepeval i WHERE i.intervallerepevalid = :intervallerepevalid"),
    @NamedQuery(name = "Intervallerepeval.findByValeurrep", query = "SELECT i FROM Intervallerepeval i WHERE i.valeurrep = :valeurrep")})
public class Intervallerepeval implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intervallerepevalid")
    private Integer intervallerepevalid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valeurrep")
    private Float valeurrep;
    @JoinColumn(name = "evaluationreponseid", referencedColumnName = "evaluationreponseid")
    @ManyToOne(optional = false)
    private Evaluationreponse evaluationreponseid;
    @JoinColumn(name = "intervallerepid", referencedColumnName = "intervallerepid")
    @ManyToOne(optional = false)
    private Intervallerep intervallerepid;

    public Intervallerepeval() {
    }

    public Intervallerepeval(Integer intervallerepevalid) {
        this.intervallerepevalid = intervallerepevalid;
    }

    public Integer getIntervallerepevalid() {
        return intervallerepevalid;
    }

    public void setIntervallerepevalid(Integer intervallerepevalid) {
        this.intervallerepevalid = intervallerepevalid;
    }

    public Float getValeurrep() {
        return valeurrep;
    }

    public void setValeurrep(Float valeurrep) {
        this.valeurrep = valeurrep;
    }

    public Evaluationreponse getEvaluationreponseid() {
        return evaluationreponseid;
    }

    public void setEvaluationreponseid(Evaluationreponse evaluationreponseid) {
        this.evaluationreponseid = evaluationreponseid;
    }

    public Intervallerep getIntervallerepid() {
        return intervallerepid;
    }

    public void setIntervallerepid(Intervallerep intervallerepid) {
        this.intervallerepid = intervallerepid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intervallerepevalid != null ? intervallerepevalid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervallerepeval)) {
            return false;
        }
        Intervallerepeval other = (Intervallerepeval) object;
        if ((this.intervallerepevalid == null && other.intervallerepevalid != null) || (this.intervallerepevalid != null && !this.intervallerepevalid.equals(other.intervallerepevalid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Intervallerepeval[ intervallerepevalid=" + intervallerepevalid + " ]";
    }
    
}
