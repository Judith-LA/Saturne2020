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
@Table(name = "intervallerep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intervallerep.findAll", query = "SELECT i FROM Intervallerep i"),
    @NamedQuery(name = "Intervallerep.findByIntervallerepid", query = "SELECT i FROM Intervallerep i WHERE i.intervallerepid = :intervallerepid"),
    @NamedQuery(name = "Intervallerep.findByValeur", query = "SELECT i FROM Intervallerep i WHERE i.valeur = :valeur"),
    @NamedQuery(name = "Intervallerep.findByDelta", query = "SELECT i FROM Intervallerep i WHERE i.delta = :delta")})
public class Intervallerep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intervallerepid")
    private Integer intervallerepid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur")
    private float valeur;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "delta")
    private Float delta;
    @JoinColumn(name = "reponseid", referencedColumnName = "reponseid")
    @ManyToOne(optional = false)
    private Reponse reponseid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intervallerepid")
    private Collection<Intervallerepeval> intervallerepevalCollection;

    public Intervallerep() {
    }

    public Intervallerep(Integer intervallerepid) {
        this.intervallerepid = intervallerepid;
    }

    public Intervallerep(Integer intervallerepid, float valeur) {
        this.intervallerepid = intervallerepid;
        this.valeur = valeur;
    }

    public Integer getIntervallerepid() {
        return intervallerepid;
    }

    public void setIntervallerepid(Integer intervallerepid) {
        this.intervallerepid = intervallerepid;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Float getDelta() {
        return delta;
    }

    public void setDelta(Float delta) {
        this.delta = delta;
    }

    public Reponse getReponseid() {
        return reponseid;
    }

    public void setReponseid(Reponse reponseid) {
        this.reponseid = reponseid;
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
        hash += (intervallerepid != null ? intervallerepid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervallerep)) {
            return false;
        }
        Intervallerep other = (Intervallerep) object;
        if ((this.intervallerepid == null && other.intervallerepid != null) || (this.intervallerepid != null && !this.intervallerepid.equals(other.intervallerepid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Intervallerep[ intervallerepid=" + intervallerepid + " ]";
    }
    
}
