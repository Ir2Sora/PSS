/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import entity.Worker;

/**
 *
 * @author Дом
 */
@Local
public interface WorkerFacadeLocal {

    void create(Worker worker);

    void edit(Worker worker);

    void remove(Worker worker);

    Worker find(Object id);

    List<Worker> findAll();

    List<Worker> findRange(int[] range);

    int count();
    
}
