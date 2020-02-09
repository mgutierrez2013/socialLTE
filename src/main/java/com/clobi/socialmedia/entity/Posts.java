/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.socialmedia.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p")
    , @NamedQuery(name = "Posts.findById", query = "SELECT p FROM Posts p WHERE p.id = :id")
    , @NamedQuery(name = "Posts.findByPage", query = "SELECT p FROM Posts p WHERE p.idPaginas.id = :id order by p.fecha desc")
    , @NamedQuery(name = "Posts.findByLink", query = "SELECT p FROM Posts p WHERE p.link = :link")
    , @NamedQuery(name = "Posts.findByLikes", query = "SELECT p FROM Posts p WHERE p.likes = :likes")
    , @NamedQuery(name = "Posts.findByComentarios", query = "SELECT p FROM Posts p WHERE p.comentarios = :comentarios")
    , @NamedQuery(name = "Posts.findByCompartidos", query = "SELECT p FROM Posts p WHERE p.compartidos = :compartidos")
    , @NamedQuery(name = "Posts.findByFecha", query = "SELECT p FROM Posts p WHERE p.fecha = :fecha")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 700)
    @Column(name = "link")
    private String link;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "comentarios")
    private Integer comentarios;
    @Column(name = "compartidos")
    private Integer compartidos;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_paginas", referencedColumnName = "id")
    @ManyToOne
    private Paginas idPaginas;
    @JoinColumn(name = "id_tipos_posts", referencedColumnName = "id")
    @ManyToOne
    private TiposPost idTiposPosts;

    public Posts() {
    }

    public Posts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComentarios() {
        return comentarios;
    }

    public void setComentarios(Integer comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getCompartidos() {
        return compartidos;
    }

    public void setCompartidos(Integer compartidos) {
        this.compartidos = compartidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Paginas getIdPaginas() {
        return idPaginas;
    }

    public void setIdPaginas(Paginas idPaginas) {
        this.idPaginas = idPaginas;
    }

    public TiposPost getIdTiposPosts() {
        return idTiposPosts;
    }

    public void setIdTiposPosts(TiposPost idTiposPosts) {
        this.idTiposPosts = idTiposPosts;
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
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.socialmedia.entity.Posts[ id=" + id + " ]";
    }
    
}
