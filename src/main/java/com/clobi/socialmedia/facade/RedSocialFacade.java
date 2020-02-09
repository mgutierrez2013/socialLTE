/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.socialmedia.facade;

import com.clobi.socialmedia.entity.RedSocial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Misael
 */
@Stateless
public class RedSocialFacade extends AbstractFacade<RedSocial> {

    @PersistenceContext(unitName = "socialPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RedSocialFacade() {
        super(RedSocial.class);
    }
    
}
