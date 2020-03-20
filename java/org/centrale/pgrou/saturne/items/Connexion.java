/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "connexion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connexion.findAll", query = "SELECT c FROM Connexion c"),
    @NamedQuery(name = "Connexion.findByConnexionid", query = "SELECT c FROM Connexion c WHERE c.connexionid = :connexionid"),
    @NamedQuery(name = "Connexion.findByDebutconnexion", query = "SELECT c FROM Connexion c WHERE c.debutconnexion = :debutconnexion"),
    @NamedQuery(name = "Connexion.findByFinconnexion", query = "SELECT c FROM Connexion c WHERE c.finconnexion = :finconnexion"),
    @NamedQuery(name = "Connexion.findByIp", query = "SELECT c FROM Connexion c WHERE c.ip = :ip")})
public class Connexion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "connexionid")
    private String connexionid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "debutconnexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date debutconnexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finconnexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finconnexion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ip")
    private String ip;
    @JoinColumn(name = "personneid", referencedColumnName = "personneid")
    @ManyToOne(optional = false)
    private Personne personneid;

    public Connexion() {
    }

    public Connexion(String connexionid) {
        this.connexionid = connexionid;
    }

    public Connexion(String connexionid, Date debutconnexion, Date finconnexion, String ip) {
        this.connexionid = connexionid;
        this.debutconnexion = debutconnexion;
        this.finconnexion = finconnexion;
        this.ip = ip;
    }

    public String getConnexionid() {
        return connexionid;
    }

    public void setConnexionid(String connexionid) {
        this.connexionid = connexionid;
    }

    public Date getDebutconnexion() {
        return debutconnexion;
    }

    public void setDebutconnexion(Date debutconnexion) {
        this.debutconnexion = debutconnexion;
    }

    public Date getFinconnexion() {
        return finconnexion;
    }

    public void setFinconnexion(Date finconnexion) {
        this.finconnexion = finconnexion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Personne getPersonneid() {
        return personneid;
    }

    public void setPersonneid(Personne personneid) {
        this.personneid = personneid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (connexionid != null ? connexionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connexion)) {
            return false;
        }
        Connexion other = (Connexion) object;
        if ((this.connexionid == null && other.connexionid != null) || (this.connexionid != null && !this.connexionid.equals(other.connexionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Connexion[ connexionid=" + connexionid + " ]";
    }
    
}
