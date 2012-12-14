/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Direction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Дом
 */
@Stateless
public class DirectionFacade extends AbstractFacade<Direction> implements DirectionFacadeLocal {
    @PersistenceContext(unitName = "PSSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DirectionFacade() {
        super(Direction.class);
    }
      
}
