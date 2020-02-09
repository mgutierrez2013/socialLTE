/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.socialmedia.entity;

/**
 *
 * @author azus
 */
public class ComentariosFacebook {
    private String id;
    private String idPost;
    private String linkPost;
    private String pagina;
    private String autor;
    private String comentario;
    private String usuarioComentaro;
    private String link;
    private String respuesta;
    private String positivo;
    private String negativo;
    private String neutral;
    private String sentiment;

    public ComentariosFacebook(String id, String idPost, String linkPost, String pagina, String autor, String comentario, String usuarioComentaro, String link, String respuesta, String positivo, String negativo, String neutral, String sentiment) {
        this.id = id;
        this.idPost = idPost;
        this.linkPost = linkPost;
        this.pagina = pagina;
        this.autor = autor;
        this.comentario = comentario;
        this.usuarioComentaro = usuarioComentaro;
        this.link = link;
        this.respuesta = respuesta;
        this.positivo = positivo;
        this.negativo = negativo;
        this.neutral = neutral;
        this.sentiment = sentiment;
    }
    


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getLinkPost() {
        return linkPost;
    }

    public void setLinkPost(String linkPost) {
        this.linkPost = linkPost;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuarioComentaro() {
        return usuarioComentaro;
    }

    public void setUsuarioComentaro(String usuarioComentaro) {
        this.usuarioComentaro = usuarioComentaro;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPositivo() {
        return positivo;
    }

    public void setPositivo(String positivo) {
        this.positivo = positivo;
    }

    public String getNegativo() {
        return negativo;
    }

    public void setNegativo(String negativo) {
        this.negativo = negativo;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
    
    
    
}
