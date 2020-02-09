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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Misael
 */
@Entity
@Table(name = "tipos_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposPost.findAll", query = "SELECT t FROM TiposPost t")
    , @NamedQuery(name = "TiposPost.findById", query = "SELECT t FROM TiposPost t WHERE t.id = :id")
    , @NamedQuery(name = "TiposPost.findByNombreTipoPost", query = "SELECT t FROM TiposPost t WHERE t.nombreTipoPost = :nombreTipoPost")})
public class TiposPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "nombre_tipo_post")
    private String nombreTipoPost;
    @OneToMany(mappedBy = "idTiposPosts")
    private List<Posts> postsList;

    public TiposPost() {
    }

    public TiposPost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTipoPost() {
        return nombreTipoPost;
    }

    public void setNombreTipoPost(String nombreTipoPost) {
        this.nombreTipoPost = nombreTipoPost;
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
        if (!(object instanceof TiposPost)) {
            return false;
        }
        TiposPost other = (TiposPost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clobi.socialmedia.entity.TiposPost[ id=" + id + " ]";
    }
    
}