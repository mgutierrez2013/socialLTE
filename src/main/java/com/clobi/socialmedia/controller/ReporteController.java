/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.socialmedia.controller;

import com.clobi.socialmedia.controller.util.JsfUtil;
import com.clobi.socialmedia.entity.ComentariosFacebook;
import com.clobi.socialmedia.entity.Paginas;
import com.clobi.socialmedia.entity.Posts;
import com.clobi.socialmedia.entity.RedSocial;
import com.clobi.socialmedia.entity.TotalSentimientos;
import com.clobi.socialmedia.facade.PaginasFacade;
import com.clobi.socialmedia.facade.RedSocialFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author azus
 */
@Named(value = "reporteController")
@ViewScoped
public class ReporteController implements Serializable {

    /**
     * Creates a new instance of ReporteController
     */
    public ReporteController() {
    }
    
   @EJB
    private PaginasFacade ejbFacade;
    @EJB
    private RedSocialFacade socialFacade;
    
    private List<Paginas> items = null;
    
    private List<Posts> listaPost= null;
    
    private List<Posts> itemsPost= null;
    
    private List<ComentariosFacebook> itemsComentarios= null;
    
    private List<TotalSentimientos> itemsTotales= null;
    
    private List<Paginas> selectedPages ;
    
    private Paginas selected;
    
    private List<RedSocial> listaRedes;
    
    private RedSocial redSelected;
    
    private String fechaIni;
    
    private String fechaFin;
    
    private String fechaIniReporte;
    
    private String fechaFinReporte;


    public Paginas getSelected() {
        return selected;
    }

    public void setSelected(Paginas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaginasFacade getFacade() {
        return ejbFacade;
    }

    public Paginas prepareCreate() {
        selected = new Paginas();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void obtenerListaPostPorPagina(){
        if(selected != null){
            System.err.println("Entro por aca");
            setListaPost(ejbFacade.obtenerPostPorPagina(selected.getId()));
        }
    }
    
    
    public void obtenerPostMultiplesPaginas(){
        if(selectedPages != null && !selectedPages.isEmpty()){
            System.err.println("Entro por aca en seleccion de multiple pagina");
            setItemsPost(ejbFacade.obtenerPostMultiplePagina(selectedPages, fechaIniReporte, fechaFinReporte));
        }
        else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("SelectedPages"));
        }
    }
    
    
    public void obtenerAnalisisSentimiento(){
        if(selectedPages != null && !selectedPages.isEmpty()){
            System.err.println("Entro por aca en seleccion de multiple pagina");
            setItemsComentarios(ejbFacade.obtenerSentimientosMultiplePagina(selectedPages));
            //setItemsPost(ejbFacade.obtenerPostMultiplePagina(selectedPages, fechaIniReporte, fechaFinReporte));
        }
        else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("SelectedPages"));
        }
    }
    
    public void obtenerTotalSentimientoPagina(){
        if(selectedPages != null && !selectedPages.isEmpty()){
            System.err.println("Entro por aca en seleccion de multiple pagina");
            setItemsTotales(ejbFacade.obtenerTotalSentimientoPagina(selectedPages));
        }
        else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("SelectedPages"));
        }
    }
    

     public void obtenerTotalSentimientoPaginaSinAutor(){
        if(selectedPages != null && !selectedPages.isEmpty()){
            System.err.println("Entro por aca en seleccion de multiple pagina");
            setItemsTotales(ejbFacade.obtenerTotalSentimientoPaginaSinAutor(selectedPages));
        }
        else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("SelectedPages"));
        }
    } 
     
     
     public void obtenerTotalSentimientoPaginaPorPost(){
        if(selectedPages != null && !selectedPages.isEmpty()){
            System.err.println("Entro por aca en seleccion de multiple pagina");
            setItemsTotales(ejbFacade.obtenerTotalSentimientoPaginaPorPost(selectedPages));
        }
        else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("SelectedPages"));
        }
    } 
     

     public void obtenerTotalSentimientoPaginaPorPostSinAutor(){
        if(selectedPages != null && !selectedPages.isEmpty()){
            System.err.println("Entro por aca en seleccion de multiple pagina");
            setItemsTotales(ejbFacade.obtenerTotalSentimientoPaginaPorPostSinAutor(selectedPages));
        }
        else{
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("SelectedPages"));
        }
    }     
     
    public void filtrarPostFechas(){
        System.err.println("Entro por aca fechas");
        setListaPost(ejbFacade.obtenerPostFiltroFecha(1,fechaIni, fechaFin));
    }
    
    public void limpiarPost(){
        setListaPost(null);
        setFechaIni("");
        setFechaFin("");
    }
    
    public void limpiarReportPost(){
        setItemsPost(null);
        setItemsComentarios(null);
        setItemsTotales(null);
        setFechaIniReporte("");
        setFechaFinReporte("");
    }
    
    public String obtenerNombreRedSocial(Integer id){
        RedSocial social= socialFacade.find(id);
        return social.getNombreRedSocial();
    }
    
    
    @PostConstruct
    public void init() {
        setListaRedes(socialFacade.findAll());
    }
    
    public void recargarPaginas() {
        items= ejbFacade.obtenerTotalPaginas();
        setListaPost(null);
        setSelected(null);
        setSelectedPages(null);
        setItemsComentarios(null);
        setItemsTotales(null);
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PaginasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            listaPost=null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PaginasUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PaginasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            listaPost=null;
        }
    }

    public List<Paginas> getItems() {
        if (items == null) {
            items = getFacade().obtenerTotalPaginas();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Paginas getPaginas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Paginas> getItemsAvailableSelectMany() {
        return getFacade().obtenerTotalPaginas();
    }

    public List<Paginas> getItemsAvailableSelectOne() {
        return getFacade().obtenerTotalPaginas();
    }

    @FacesConverter(forClass = Paginas.class)
    public static class PaginasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaginasController controller = (PaginasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paginasController");
            return controller.getPaginas(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Paginas) {
                Paginas o = (Paginas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paginas.class.getName()});
                return null;
            }
        }

    }

    public List<RedSocial> getListaRedes() {
        return listaRedes;
    }

    public void setListaRedes(List<RedSocial> listaRedes) {
        this.listaRedes = listaRedes;
    }

    public RedSocial getRedSelected() {
        return redSelected;
    }

    public void setRedSelected(RedSocial redSelected) {
        this.redSelected = redSelected;
    }

    public List<Posts> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Posts> listaPost) {
        this.listaPost = listaPost;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Posts> getItemsPost() {
        return itemsPost;
    }

    public void setItemsPost(List<Posts> itemsPost) {
        this.itemsPost = itemsPost;
    }

    public List<Paginas> getSelectedPages() {
        return selectedPages;
    }

    public void setSelectedPages(List<Paginas> selectedPages) {
        this.selectedPages = selectedPages;
    }

    public String getFechaIniReporte() {
        return fechaIniReporte;
    }

    public void setFechaIniReporte(String fechaIniReporte) {
        this.fechaIniReporte = fechaIniReporte;
    }

    public String getFechaFinReporte() {
        return fechaFinReporte;
    }

    public void setFechaFinReporte(String fechaFinReporte) {
        this.fechaFinReporte = fechaFinReporte;
    }

    public List<ComentariosFacebook> getItemsComentarios() {
        return itemsComentarios;
    }

    public void setItemsComentarios(List<ComentariosFacebook> itemsComentarios) {
        this.itemsComentarios = itemsComentarios;
    }

    public List<TotalSentimientos> getItemsTotales() {
        return itemsTotales;
    }

    public void setItemsTotales(List<TotalSentimientos> itemsTotales) {
        this.itemsTotales = itemsTotales;
    }
    
}
