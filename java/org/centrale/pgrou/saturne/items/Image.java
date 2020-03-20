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
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByImageid", query = "SELECT i FROM Image i WHERE i.imageid = :imageid"),
    @NamedQuery(name = "Image.findByNomimage", query = "SELECT i FROM Image i WHERE i.nomimage = :nomimage")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "imageid")
    private Integer imageid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nomimage")
    private String nomimage;
    @OneToMany(mappedBy = "imageid")
    private Collection<Question> questionCollection;
    @OneToMany(mappedBy = "imageid")
    private Collection<Qcmrep> qcmrepCollection;

    public Image() {
    }

    public Image(Integer imageid) {
        this.imageid = imageid;
    }

    public Image(Integer imageid, String nomimage) {
        this.imageid = imageid;
        this.nomimage = nomimage;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getNomimage() {
        return nomimage;
    }

    public void setNomimage(String nomimage) {
        this.nomimage = nomimage;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @XmlTransient
    public Collection<Qcmrep> getQcmrepCollection() {
        return qcmrepCollection;
    }

    public void setQcmrepCollection(Collection<Qcmrep> qcmrepCollection) {
        this.qcmrepCollection = qcmrepCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageid != null ? imageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imageid == null && other.imageid != null) || (this.imageid != null && !this.imageid.equals(other.imageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Image[ imageid=" + imageid + " ]";
    }
    
}
