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
public class TotalSentimientos {
    private String nombrePagina;
    private String link;
    private String sentiment;
    private String total;

    public TotalSentimientos(String nombrePagina, String link, String sentiment, String total) {
        this.nombrePagina = nombrePagina;
        this.link = link;
        this.sentiment = sentiment;
        this.total = total;
    }
    
    
 

    public String getNombrePagina() {
        return nombrePagina;
    }

    public void setNombrePagina(String nombrePagina) {
        this.nombrePagina = nombrePagina;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
}
