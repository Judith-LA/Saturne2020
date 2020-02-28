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
@Table(name = "personne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findByPersonneid", query = "SELECT p FROM Personne p WHERE p.personneid = :personneid"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personne.findByLogin", query = "SELECT p FROM Personne p WHERE p.login = :login"),
    @NamedQuery(name = "Personne.findByMotdepasse", query = "SELECT p FROM Personne p WHERE p.motdepasse = :motdepasse")})
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personneid")
    private Integer personneid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "login")
    private String login;
    @Size(max = 2147483647)
    @Column(name = "motdepasse")
    private String motdepasse;
    @JoinTable(name = "rolepersonne", joinColumns = {
        @JoinColumn(name = "personneid", referencedColumnName = "personneid")}, inverseJoinColumns = {
        @JoinColumn(name = "roleid", referencedColumnName = "roleid")})
    @ManyToMany
    private Collection<Role> roleCollection;
    @ManyToMany(mappedBy = "personneCollection")
    private Collection<Groupe> groupeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneid")
    private Collection<Quiz> quizCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneid")
    private Collection<Connexion> connexionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneid")
    private Collection<Evaluation> evaluationCollection;

    public Personne() {
    }

    public Personne(Integer personneid) {
        this.personneid = personneid;
    }

    public Personne(Integer personneid, String nom, String prenom, String login) {
        this.personneid = personneid;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
    }

    public Integer getPersonneid() {
        return personneid;
    }

    public void setPersonneid(Integer personneid) {
        this.personneid = personneid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @XmlTransient
    public Collection<Role> getRoleCollection() {
        return roleCollection;
    }

    public void setRoleCollection(Collection<Role> roleCollection) {
        this.roleCollection = roleCollection;
    }

    @XmlTransient
    public Collection<Groupe> getGroupeCollection() {
        return groupeCollection;
    }

    public void setGroupeCollection(Collection<Groupe> groupeCollection) {
        this.groupeCollection = groupeCollection;
    }

    @XmlTransient
    public Collection<Quiz> getQuizCollection() {
        return quizCollection;
    }

    public void setQuizCollection(Collection<Quiz> quizCollection) {
        this.quizCollection = quizCollection;
    }

    @XmlTransient
    public Collection<Connexion> getConnexionCollection() {
        return connexionCollection;
    }

    public void setConnexionCollection(Collection<Connexion> connexionCollection) {
        this.connexionCollection = connexionCollection;
    }

    @XmlTransient
    public Collection<Evaluation> getEvaluationCollection() {
        return evaluationCollection;
    }

    public void setEvaluationCollection(Collection<Evaluation> evaluationCollection) {
        this.evaluationCollection = evaluationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personneid != null ? personneid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.personneid == null && other.personneid != null) || (this.personneid != null && !this.personneid.equals(other.personneid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Personne[ personneid=" + personneid + " ]";
    }
    
}
