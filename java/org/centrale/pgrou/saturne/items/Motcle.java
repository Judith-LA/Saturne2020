/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "motcle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motcle.findAll", query = "SELECT m FROM Motcle m"),
    @NamedQuery(name = "Motcle.findByMotcleid", query = "SELECT m FROM Motcle m WHERE m.motcleid = :motcleid"),
    @NamedQuery(name = "Motcle.findByMot", query = "SELECT m FROM Motcle m WHERE m.mot = :mot")})
public class Motcle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "motcleid")
    private Integer motcleid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mot")
    private String mot;
    @JoinTable(name = "motclequestion", joinColumns = {
        @JoinColumn(name = "motcleid", referencedColumnName = "motcleid")}, inverseJoinColumns = {
        @JoinColumn(name = "questionid", referencedColumnName = "questionid")})
    @ManyToMany
    private Collection<Question> questionCollection;

    public Motcle() {
    }

    public Motcle(Integer motcleid) {
        this.motcleid = motcleid;
    }

    public Motcle(Integer motcleid, String mot) {
        this.motcleid = motcleid;
        this.mot = mot;
    }

    public Integer getMotcleid() {
        return motcleid;
    }

    public void setMotcleid(Integer motcleid) {
        this.motcleid = motcleid;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (motcleid != null ? motcleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motcle)) {
            return false;
        }
        Motcle other = (Motcle) object;
        if ((this.motcleid == null && other.motcleid != null) || (this.motcleid != null && !this.motcleid.equals(other.motcleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Motcle[ motcleid=" + motcleid + " ]";
    }
    
}
