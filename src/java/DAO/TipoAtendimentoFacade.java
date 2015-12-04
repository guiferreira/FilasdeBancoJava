/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Maike Nunes
 */
@Stateless
public class TipoAtendimentoFacade extends AbstractFacade<TipoAtendimento> {
    @PersistenceContext(unitName = "mnunesorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoAtendimentoFacade() {
        super(TipoAtendimento.class);
    }
    
}
