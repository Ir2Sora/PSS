/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Дом
 */
@Stateless
public class SuggestionFacade extends AbstractFacade<Suggestion> implements SuggestionFacadeLocal {
    @PersistenceContext(unitName = "LAB1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuggestionFacade() {
        super(Suggestion.class);
    }
    
    
    
}
