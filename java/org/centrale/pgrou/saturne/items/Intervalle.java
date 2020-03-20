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
@Table(name = "intervalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intervalle.findAll", query = "SELECT i FROM Intervalle i"),
    @NamedQuery(name = "Intervalle.findByIntervalleid", query = "SELECT i FROM Intervalle i WHERE i.intervalleid = :intervalleid")})
public class Intervalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intervalleid")
    private Integer intervalleid;
    @JoinColumn(name = "questionid", referencedColumnName = "questionid")
    @ManyToOne(optional = false)
    private Question questionid;

    public Intervalle() {
    }

    public Intervalle(Integer intervalleid) {
        this.intervalleid = intervalleid;
    }

    public Integer getIntervalleid() {
        return intervalleid;
    }

    public void setIntervalleid(Integer intervalleid) {
        this.intervalleid = intervalleid;
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
        hash += (intervalleid != null ? intervalleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervalle)) {
            return false;
        }
        Intervalle other = (Intervalle) object;
        if ((this.intervalleid == null && other.intervalleid != null) || (this.intervalleid != null && !this.intervalleid.equals(other.intervalleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Intervalle[ intervalleid=" + intervalleid + " ]";
    }
    
}
