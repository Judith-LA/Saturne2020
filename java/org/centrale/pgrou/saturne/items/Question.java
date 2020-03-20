/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.items;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQuestionid", query = "SELECT q FROM Question q WHERE q.questionid = :questionid"),
    @NamedQuery(name = "Question.findByEnonce", query = "SELECT q FROM Question q WHERE q.enonce = :enonce"),
    @NamedQuery(name = "Question.findByDatecreationquestion", query = "SELECT q FROM Question q WHERE q.datecreationquestion = :datecreationquestion"),
    @NamedQuery(name = "Question.findByEstprivee", query = "SELECT q FROM Question q WHERE q.estprivee = :estprivee")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "questionid")
    private Integer questionid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "enonce")
    private String enonce;
    @Column(name = "datecreationquestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreationquestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estprivee")
    private boolean estprivee;
    @ManyToMany(mappedBy = "questionCollection")
    private Collection<Motcle> motcleCollection;
    @JoinColumn(name = "imageid", referencedColumnName = "imageid")
    @ManyToOne
    private Image imageid;
    @JoinColumn(name = "personneid", referencedColumnName = "personneid")
    @ManyToOne(optional = false)
    private Personne personneid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionid")
    private Collection<Contenuquiz> contenuquizCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionid")
    private Collection<Intervalle> intervalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionid")
    private Collection<Reponse> reponseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionid")
    private Collection<Qcm> qcmCollection;

    public Question() {
    }

    public Question(Integer questionid) {
        this.questionid = questionid;
    }

    public Question(Integer questionid, String enonce, boolean estprivee) {
        this.questionid = questionid;
        this.enonce = enonce;
        this.estprivee = estprivee;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public Date getDatecreationquestion() {
        return datecreationquestion;
    }

    public void setDatecreationquestion(Date datecreationquestion) {
        this.datecreationquestion = datecreationquestion;
    }

    public boolean getEstprivee() {
        return estprivee;
    }

    public void setEstprivee(boolean estprivee) {
        this.estprivee = estprivee;
    }

    @XmlTransient
    public Collection<Motcle> getMotcleCollection() {
        return motcleCollection;
    }

    public void setMotcleCollection(Collection<Motcle> motcleCollection) {
        this.motcleCollection = motcleCollection;
    }

    public Image getImageid() {
        return imageid;
    }

    public void setImageid(Image imageid) {
        this.imageid = imageid;
    }

    @XmlTransient
    public Collection<Contenuquiz> getContenuquizCollection() {
        return contenuquizCollection;
    }

    public void setContenuquizCollection(Collection<Contenuquiz> contenuquizCollection) {
        this.contenuquizCollection = contenuquizCollection;
    }

    @XmlTransient
    public Collection<Intervalle> getIntervalleCollection() {
        return intervalleCollection;
    }

    public void setIntervalleCollection(Collection<Intervalle> intervalleCollection) {
        this.intervalleCollection = intervalleCollection;
    }

    @XmlTransient
    public Collection<Reponse> getReponseCollection() {
        return reponseCollection;
    }

    public void setReponseCollection(Collection<Reponse> reponseCollection) {
        this.reponseCollection = reponseCollection;
    }

    @XmlTransient
    public Collection<Qcm> getQcmCollection() {
        return qcmCollection;
    }

    public void setQcmCollection(Collection<Qcm> qcmCollection) {
        this.qcmCollection = qcmCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionid != null ? questionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionid == null && other.questionid != null) || (this.questionid != null && !this.questionid.equals(other.questionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Question[ questionid=" + questionid + " ]";
    }

    public Personne getPersonneid() {
        return personneid;
    }

    public void setPersonneid(Personne personneid) {
        this.personneid = personneid;
    }
    
}
