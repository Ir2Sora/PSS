/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Department;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Дом
 */
@Local
public interface DepartmentFacadeLocal {

    void create(Department department);

    void edit(Department department);

    void remove(Department department);

    Department find(Object id);

    List<Department> findAll();

    List<Department> findRange(int[] range);

    int count();

    Department findByNumber(int depNumber);
    
}
