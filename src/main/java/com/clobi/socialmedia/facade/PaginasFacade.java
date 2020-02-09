/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.socialmedia.facade;

import com.clobi.socialmedia.entity.ComentariosFacebook;
import com.clobi.socialmedia.entity.Paginas;
import com.clobi.socialmedia.entity.Posts;
import com.clobi.socialmedia.entity.TotalSentimientos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Misael
 */
@Stateless
public class PaginasFacade extends AbstractFacade<Paginas> {

    @PersistenceContext(unitName = "socialPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaginasFacade() {
        super(Paginas.class);
    }

    public List<Posts> obtenerPostPorPagina(Integer id) {
        List<Posts> tmp;
        Query query = getEntityManager().createNamedQuery("Posts.findByPage");
        query.setParameter("id", id);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<Posts>() : tmp;
    }

    public List<Posts> obtenerPostMultiplePagina(List<Paginas> paginas, String fechaIni, String fechaFin) {
        List<Posts> tmp;
        String idPaginas = "";
        String dateParams = (!fechaIni.isEmpty() && !fechaFin.isEmpty()) ? "and (p.fecha >= TO_DATE('" + fechaIni + "', 'DD-MM-YYYY') and p.fecha <= TO_DATE('" + fechaFin + "', 'DD-MM-YYYY'))" : "";
        for (Paginas obj : paginas) {
            idPaginas += obj.getId().toString();
            idPaginas += ",";
        }
        idPaginas = idPaginas.substring(0, idPaginas.length() - 1);
        String sql = "select p.*"
                + "from posts p\n"
                + "inner join paginas pa on p.id_paginas = pa.id\n"
                + "inner join tipos_post tp on p.id_tipos_posts = tp.id\n"
                + "where id_paginas in (" + idPaginas + ")" + dateParams + "\n"
                + "order by p.fecha desc";
        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql, Posts.class);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<Posts>() : tmp;
    }

    public List<ComentariosFacebook> obtenerSentimientosMultiplePagina(List<Paginas> paginas) {
        List<ComentariosFacebook> tmp;
        String idPaginas = "";
        for (Paginas obj : paginas) {
            idPaginas += obj.getId().toString();
            idPaginas += ",";
        }
        idPaginas = idPaginas.substring(0, idPaginas.length() - 1);
        String sql = "select TO_CHAR(cf.id, '9999') as id,TO_CHAR(cf.id_posts, '9999') as idPost,p.link as linkPost, pa.nombre_pagina as pagina,cf.autor,\n"
                + "case when trim(cf.comentario) = '' then 'Foto o sticker' else cf.comentario end as comentario\n"
                + ",cf.usuario_comentario as usuarioComentaro,cf.link,cf.respondido_autor as respuesta,\n"
                + "TO_CHAR(cf.positivo, '99990.99') as positivo,TO_CHAR(cf.negativo, '99990.99') as negativo,TO_CHAR(cf.neutral, '99990.99') as neutral,cf.sentiment\n"
                + "from comentarios_facebook cf\n"
                + "inner join posts p on cf.id_posts = p.id\n"
                + "inner join paginas pa on p.id_paginas = pa.id\n"
                + "where p.id_paginas in (" + idPaginas + ")\n"
                + "order by id";
        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql, "ComentariosMapping");
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<ComentariosFacebook>() : tmp;
    }

    public List<TotalSentimientos> obtenerTotalSentimientoPagina(List<Paginas> paginas) {
        List<TotalSentimientos> tmp;
        String idPaginas = "";
        for (Paginas obj : paginas) {
            idPaginas += obj.getId().toString();
            idPaginas += ",";
        }
        idPaginas = idPaginas.substring(0, idPaginas.length() - 1);

        String sql = "select pa.nombre_pagina as nombrePagina, '' as link, cf.sentiment, TO_CHAR(COUNT(*), '9999') as total\n"
                + "from comentarios_facebook cf\n"
                + "inner join posts p on cf.id_posts = p.id\n"
                + "inner join paginas pa on p.id_paginas = pa.id\n"
                + "where p.id_paginas in (" + idPaginas + ") and sentiment is not null\n"
                + "group by pa.nombre_pagina,cf.sentiment\n"
                + "order by pa.nombre_pagina, sentiment";
        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql, "TotalesMapping");
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<TotalSentimientos>() : tmp;
    }

    public List<TotalSentimientos> obtenerTotalSentimientoPaginaSinAutor(List<Paginas> paginas) {
        List<TotalSentimientos> tmp;
        String idPaginas = "";
        for (Paginas obj : paginas) {
            idPaginas += obj.getId().toString();
            idPaginas += ",";
        }
        idPaginas = idPaginas.substring(0, idPaginas.length() - 1);

        String sql = "select pa.nombre_pagina as nombrePagina, '' as link, cf.sentiment, TO_CHAR(COUNT(*), '9999') as total\n"
                + "from comentarios_facebook cf\n"
                + "inner join posts p on cf.id_posts = p.id\n"
                + "inner join paginas pa on p.id_paginas = pa.id\n"
                + "where p.id_paginas in (" + idPaginas + ") and sentiment is not null and cf.autor = false\n"
                + "group by pa.nombre_pagina,cf.sentiment\n"
                + "order by pa.nombre_pagina, sentiment";

        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql, "TotalesMapping");
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<TotalSentimientos>() : tmp;
    }

    public List<TotalSentimientos> obtenerTotalSentimientoPaginaPorPost(List<Paginas> paginas) {
        List<TotalSentimientos> tmp;
        String idPaginas = "";
        for (Paginas obj : paginas) {
            idPaginas += obj.getId().toString();
            idPaginas += ",";
        }
        idPaginas = idPaginas.substring(0, idPaginas.length() - 1);

        String sql = "select pa.nombre_pagina as nombrePagina,p.link, cf.sentiment, TO_CHAR( COUNT(*), '9999') as total\n"
                + "from comentarios_facebook cf\n"
                + "inner join posts p on cf.id_posts = p.id\n"
                + "inner join paginas pa on p.id_paginas = pa.id\n"
                + "where p.id_paginas in (" + idPaginas + ") and sentiment is not null\n"
                + "group by pa.nombre_pagina,p.link, cf.sentiment\n"
                + "order by pa.nombre_pagina,p.link, cf.sentiment";

        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql, "TotalesMapping");
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<TotalSentimientos>() : tmp;
    }

    public List<TotalSentimientos> obtenerTotalSentimientoPaginaPorPostSinAutor(List<Paginas> paginas) {
        List<TotalSentimientos> tmp;
        String idPaginas = "";
        for (Paginas obj : paginas) {
            idPaginas += obj.getId().toString();
            idPaginas += ",";
        }
        idPaginas = idPaginas.substring(0, idPaginas.length() - 1);

        String sql = "select pa.nombre_pagina as nombrePagina,p.link, cf.sentiment, TO_CHAR( COUNT(*), '9999') as total\n"
                + "from comentarios_facebook cf\n"
                + "inner join posts p on cf.id_posts = p.id\n"
                + "inner join paginas pa on p.id_paginas = pa.id\n"
                + "where p.id_paginas in (" + idPaginas + ") and sentiment is not null and cf.autor = false \n"
                + "group by pa.nombre_pagina,p.link, cf.sentiment\n"
                + "order by pa.nombre_pagina,p.link, cf.sentiment";

        System.err.println("El query ejecutado es:" + sql);
        Query query = getEntityManager().createNativeQuery(sql, "TotalesMapping");
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<TotalSentimientos>() : tmp;
    }

    public List<Posts> obtenerPostFiltroFecha(Integer pagina, String fechaIni, String fechaFin) {

        List<Posts> tmp;
        /*
            Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(fechaIni);
            Date date2=new SimpleDateFormat("dd-MM-yyyy").parse(fechaFin);
         */
        String sql = "select * from posts where id_paginas=" + pagina + " and fecha >= TO_DATE('" + fechaIni + "', 'DD-MM-YYYY') and fecha <= TO_DATE('" + fechaFin + "', 'DD-MM-YYYY') ORDER BY fecha desc";
        Query query = getEntityManager().createNativeQuery(sql, Posts.class);
        System.err.println(sql);
        //query.setParameter("fechaIni", fechaIni);
        //query.setParameter("fechaFin", fechaFin);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<Posts>() : tmp;

    }

    public List<Paginas> obtenerTotalPaginas() {

        List<Paginas> tmp;
        String sql = "select * from paginas p order by p.nombre_pagina asc";
        Query query = getEntityManager().createNativeQuery(sql, Paginas.class);
        System.err.println(sql);
        tmp = query.getResultList();
        return tmp.isEmpty() ? new ArrayList<Paginas>() : tmp;

    }

}
