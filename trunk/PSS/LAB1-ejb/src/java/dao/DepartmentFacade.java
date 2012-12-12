/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Department;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Дом
 */
@Stateless
public class DepartmentFacade extends AbstractFacade<Department> implements DepartmentFacadeLocal {
    @PersistenceContext(unitName = "PSSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentFacade() {
        super(Department.class);
    }

    @Override
    public Department findByNumber(int depNumber) {
        List<Department> result = em.createNamedQuery("Department.findByDepartmentNumber").setParameter("departmentNumber", depNumber).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }    
}
