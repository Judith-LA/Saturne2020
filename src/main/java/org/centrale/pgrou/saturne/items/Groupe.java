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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "groupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByGroupeid", query = "SELECT g FROM Groupe g WHERE g.groupeid = :groupeid"),
    @NamedQuery(name = "Groupe.findByNomgroupe", query = "SELECT g FROM Groupe g WHERE g.nomgroupe = :nomgroupe"),
    @NamedQuery(name = "Groupe.findByEstvalide", query = "SELECT g FROM Groupe g WHERE g.estvalide = :estvalide")})
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupeid")
    private Integer groupeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nomgroupe")
    private String nomgroupe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estvalide")
    private boolean estvalide;
    @JoinTable(name = "contenugroupe", joinColumns = {
        @JoinColumn(name = "groupeid", referencedColumnName = "groupeid")}, inverseJoinColumns = {
        @JoinColumn(name = "personneid", referencedColumnName = "personneid")})
    @ManyToMany
    private Collection<Personne> personneCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupeid")
    private Collection<Test> testCollection;

    public Groupe() {
    }

    public Groupe(Integer groupeid) {
        this.groupeid = groupeid;
    }

    public Groupe(Integer groupeid, String nomgroupe, boolean estvalide) {
        this.groupeid = groupeid;
        this.nomgroupe = nomgroupe;
        this.estvalide = estvalide;
    }

    public Integer getGroupeid() {
        return groupeid;
    }

    public void setGroupeid(Integer groupeid) {
        this.groupeid = groupeid;
    }

    public String getNomgroupe() {
        return nomgroupe;
    }

    public void setNomgroupe(String nomgroupe) {
        this.nomgroupe = nomgroupe;
    }

    public boolean getEstvalide() {
        return estvalide;
    }

    public void setEstvalide(boolean estvalide) {
        this.estvalide = estvalide;
    }

    @XmlTransient
    public Collection<Personne> getPersonneCollection() {
        return personneCollection;
    }

    public void setPersonneCollection(Collection<Personne> personneCollection) {
        this.personneCollection = personneCollection;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupeid != null ? groupeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.groupeid == null && other.groupeid != null) || (this.groupeid != null && !this.groupeid.equals(other.groupeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Groupe[ groupeid=" + groupeid + " ]";
    }
    
}
