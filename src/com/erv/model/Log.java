/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.erv.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author TI-PNP
 */
@Entity
@Table(name = "LOG")
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEVEL")
    private int level;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "LOGGER")
    private String logger;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MESSAGE")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEQUENCE")
    private int sequence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "SOURCECLASS")
    private String sourceclass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "SOURCEMETHOD")
    private String sourcemethod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "THREADID")
    private int threadid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMEENTERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeentered;

    public Log() {
    }

    public Log(Integer id) {
        this.id = id;
    }

    public Log(Integer id, int level, String logger, String message, int sequence, String sourceclass, String sourcemethod, int threadid, Date timeentered) {
        this.id = id;
        this.level = level;
        this.logger = logger;
        this.message = message;
        this.sequence = sequence;
        this.sourceclass = sourceclass;
        this.sourcemethod = sourcemethod;
        this.threadid = threadid;
        this.timeentered = timeentered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getSourceclass() {
        return sourceclass;
    }

    public void setSourceclass(String sourceclass) {
        this.sourceclass = sourceclass;
    }

    public String getSourcemethod() {
        return sourcemethod;
    }

    public void setSourcemethod(String sourcemethod) {
        this.sourcemethod = sourcemethod;
    }

    public int getThreadid() {
        return threadid;
    }

    public void setThreadid(int threadid) {
        this.threadid = threadid;
    }

    public Date getTimeentered() {
        return timeentered;
    }

    public void setTimeentered(Date timeentered) {
        this.timeentered = timeentered;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.erv.model.Log[ id=" + id + " ]";
    }
    
}
