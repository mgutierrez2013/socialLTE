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
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "historico_seguidores")
@XmlRootElement

@SqlResultSetMapping(
        name = "ComentariosMapping",
    classes={
        @ConstructorResult(targetClass=ComentariosFacebook.class,
            columns={
                @ColumnResult(name="id", type=String.class),
                @ColumnResult(name="idPost", type=String.class),
                @ColumnResult(name="linkPost", type=String.class),
                @ColumnResult(name="pagina", type=String.class),
                @ColumnResult(name="autor", type=String.class),
                @ColumnResult(name="comentario", type=String.class),
                @ColumnResult(name="usuarioComentaro", type=String.class),
                @ColumnResult(name="link", type=String.class),
                @ColumnResult(name="respuesta", type=String.class),
                @ColumnResult(name="positivo", type=String.class),
                @ColumnResult(name="negativo", type=String.class),
                @ColumnResult(name="neutral", type=String.class),
                @ColumnResult(name="sentiment", type=String.class)
            })
    })


@NamedQueries({
    @NamedQuery(name = "HistoricoSeguidores.findAll", query = "SELECT h FROM HistoricoSeguidores h")
    , @NamedQuery(name = "HistoricoSeguidores.findById", query = "SELECT h FROM HistoricoSeguidores h WHERE h.id = :id")
    , @NamedQuery(name = "HistoricoSeguidores.findByIdPaginas", query = "SELECT h FROM HistoricoSeguidores h WHERE h.idPaginas = :idPaginas")
    , @NamedQuery(name = "HistoricoSeguidores.findByTotalSeguidores", query = "SELECT h FROM HistoricoSeguidores h WHERE h.totalSeguidores = :totalSeguidores")
    , @NamedQuery(name = "HistoricoSeguidores.findByFecha", query = "SELECT h FROM HistoricoSeguidores h WHERE h.fecha = :fecha")})
public class HistoricoSeguidores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_paginas")
    private Integer idPaginas;
    @Column(name = "total_seguidores")
    private Integer totalSeguidores;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public HistoricoSeguidores() {
    }

    public HistoricoSeguidores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaginas() {
        return idPaginas;
    }

    public void setIdPaginas(Integer idPaginas) {
        this.idPaginas = idPaginas;
    }

    public Integer getTotalSeguidores() {
        return totalSeguidores;
    }

    public void setTotalSeguidores(Integer totalSeguidores) {
        this.totalSeguidores = totalSeguidores;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof HistoricoSeguidores)) {
            return false;
        }
        HistoricoSeguidores other = (HistoricoSeguidores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.socialmedia.entity.HistoricoSeguidores[ id=" + id + " ]";
    }
    
}
