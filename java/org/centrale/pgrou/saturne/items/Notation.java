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
import javax.persistence.Id;
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
@Table(name = "notation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notation.findAll", query = "SELECT n FROM Notation n"),
    @NamedQuery(name = "Notation.findByNotationid", query = "SELECT n FROM Notation n WHERE n.notationid = :notationid"),
    @NamedQuery(name = "Notation.findByLibelle", query = "SELECT n FROM Notation n WHERE n.libelle = :libelle")})
public class Notation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "notationid")
    private Integer notationid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notationid")
    private Collection<Test> testCollection;

    public Notation() {
    }

    public Notation(Integer notationid) {
        this.notationid = notationid;
    }

    public Notation(Integer notationid, String libelle) {
        this.notationid = notationid;
        this.libelle = libelle;
    }

    public Integer getNotationid() {
        return notationid;
    }

    public void setNotationid(Integer notationid) {
        this.notationid = notationid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        hash += (notationid != null ? notationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notation)) {
            return false;
        }
        Notation other = (Notation) object;
        if ((this.notationid == null && other.notationid != null) || (this.notationid != null && !this.notationid.equals(other.notationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Notation[ notationid=" + notationid + " ]";
    }
    
}
