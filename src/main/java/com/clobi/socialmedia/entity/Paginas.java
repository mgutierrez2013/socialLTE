/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.socialmedia.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "paginas")


@SqlResultSetMapping(
        name = "TotalesMapping",
    classes={
        @ConstructorResult(targetClass=TotalSentimientos.class,
            columns={
                @ColumnResult(name="nombrePagina", type=String.class),
                @ColumnResult(name="link", type=String.class),
                @ColumnResult(name="sentiment", type=String.class),
                @ColumnResult(name="total", type=String.class)
            })
    })


@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paginas.findAll", query = "SELECT p FROM Paginas p")
    , @NamedQuery(name = "Paginas.findById", query = "SELECT p FROM Paginas p WHERE p.id = :id")
    , @NamedQuery(name = "Paginas.findByNombrePagina", query = "SELECT p FROM Paginas p WHERE p.nombrePagina = :nombrePagina")
    , @NamedQuery(name = "Paginas.findByLinkPagina", query = "SELECT p FROM Paginas p WHERE p.linkPagina = :linkPagina")
    , @NamedQuery(name = "Paginas.findByIdRedSocial", query = "SELECT p FROM Paginas p WHERE p.idRedSocial = :idRedSocial")})
public class Paginas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "nombre_pagina")
    private String nombrePagina;
    @Size(max = 150)
    @Column(name = "link_pagina")
    private String linkPagina;
    @Column(name = "id_red_social")
    private Integer idRedSocial;
    @OneToMany(mappedBy = "idPaginas")
    private List<Posts> postsList;

    public Paginas() {
    }

    public Paginas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePagina() {
        return nombrePagina;
    }

    public void setNombrePagina(String nombrePagina) {
        this.nombrePagina = nombrePagina;
    }

    public String getLinkPagina() {
        return linkPagina;
    }

    public void setLinkPagina(String linkPagina) {
        this.linkPagina = linkPagina;
    }

    public Integer getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(Integer idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    @XmlTransient
    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
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
        if (!(object instanceof Paginas)) {
            return false;
        }
        Paginas other = (Paginas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.socialmedia.entity.Paginas[ id=" + id + " ]";
    }
    
}
