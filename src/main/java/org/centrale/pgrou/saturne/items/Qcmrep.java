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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "qcmrep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qcmrep.findAll", query = "SELECT q FROM Qcmrep q"),
    @NamedQuery(name = "Qcmrep.findByQcmrepid", query = "SELECT q FROM Qcmrep q WHERE q.qcmrepid = :qcmrepid"),
    @NamedQuery(name = "Qcmrep.findByEnonce", query = "SELECT q FROM Qcmrep q WHERE q.enonce = :enonce")})
public class Qcmrep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qcmrepid")
    private Integer qcmrepid;
    @Size(max = 2147483647)
    @Column(name = "enonce")
    private String enonce;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qcmrepid")
    private Collection<Qcmrepeval> qcmrepevalCollection;
    @JoinColumn(name = "imageid", referencedColumnName = "imageid")
    @ManyToOne
    private Image imageid;
    @JoinColumn(name = "reponseid", referencedColumnName = "reponseid")
    @ManyToOne(optional = false)
    private Reponse reponseid;

    public Qcmrep() {
    }

    public Qcmrep(Integer qcmrepid) {
        this.qcmrepid = qcmrepid;
    }

    public Integer getQcmrepid() {
        return qcmrepid;
    }

    public void setQcmrepid(Integer qcmrepid) {
        this.qcmrepid = qcmrepid;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    @XmlTransient
    public Collection<Qcmrepeval> getQcmrepevalCollection() {
        return qcmrepevalCollection;
    }

    public void setQcmrepevalCollection(Collection<Qcmrepeval> qcmrepevalCollection) {
        this.qcmrepevalCollection = qcmrepevalCollection;
    }

    public Image getImageid() {
        return imageid;
    }

    public void setImageid(Image imageid) {
        this.imageid = imageid;
    }

    public Reponse getReponseid() {
        return reponseid;
    }

    public void setReponseid(Reponse reponseid) {
        this.reponseid = reponseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qcmrepid != null ? qcmrepid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qcmrep)) {
            return false;
        }
        Qcmrep other = (Qcmrep) object;
        if ((this.qcmrepid == null && other.qcmrepid != null) || (this.qcmrepid != null && !this.qcmrepid.equals(other.qcmrepid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Qcmrep[ qcmrepid=" + qcmrepid + " ]";
    }
    
}
