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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author louis-alexandre
 */
@Entity
@Table(name = "test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByTestid", query = "SELECT t FROM Test t WHERE t.testid = :testid"),
    @NamedQuery(name = "Test.findByDatedebuttest", query = "SELECT t FROM Test t WHERE t.datedebuttest = :datedebuttest"),
    @NamedQuery(name = "Test.findByDatefintest", query = "SELECT t FROM Test t WHERE t.datefintest = :datefintest"),
    @NamedQuery(name = "Test.findByDureemaxtest", query = "SELECT t FROM Test t WHERE t.dureemaxtest = :dureemaxtest")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "testid")
    private Integer testid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datedebuttest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedebuttest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datefintest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datefintest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dureemaxtest")
    @Temporal(TemporalType.TIME)
    private Date dureemaxtest;
    @JoinColumn(name = "groupeid", referencedColumnName = "groupeid")
    @ManyToOne(optional = false)
    private Groupe groupeid;
    @JoinColumn(name = "notationid", referencedColumnName = "notationid")
    @ManyToOne(optional = false)
    private Notation notationid;
    @JoinColumn(name = "quizid", referencedColumnName = "quizid")
    @ManyToOne(optional = false)
    private Quiz quizid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testid")
    private Collection<Evaluation> evaluationCollection;

    public Test() {
    }

    public Test(Integer testid) {
        this.testid = testid;
    }

    public Test(Integer testid, Date datedebuttest, Date datefintest, Date dureemaxtest) {
        this.testid = testid;
        this.datedebuttest = datedebuttest;
        this.datefintest = datefintest;
        this.dureemaxtest = dureemaxtest;
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public Date getDatedebuttest() {
        return datedebuttest;
    }

    public void setDatedebuttest(Date datedebuttest) {
        this.datedebuttest = datedebuttest;
    }

    public Date getDatefintest() {
        return datefintest;
    }

    public void setDatefintest(Date datefintest) {
        this.datefintest = datefintest;
    }

    public Date getDureemaxtest() {
        return dureemaxtest;
    }

    public void setDureemaxtest(Date dureemaxtest) {
        this.dureemaxtest = dureemaxtest;
    }

    public Groupe getGroupeid() {
        return groupeid;
    }

    public void setGroupeid(Groupe groupeid) {
        this.groupeid = groupeid;
    }

    public Notation getNotationid() {
        return notationid;
    }

    public void setNotationid(Notation notationid) {
        this.notationid = notationid;
    }

    public Quiz getQuizid() {
        return quizid;
    }

    public void setQuizid(Quiz quizid) {
        this.quizid = quizid;
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
        hash += (testid != null ? testid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.testid == null && other.testid != null) || (this.testid != null && !this.testid.equals(other.testid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.centrale.pgrou.saturne.items.Test[ testid=" + testid + " ]";
    }
    
}
