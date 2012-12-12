/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Direction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Дом
 */
@Local
public interface DirectionFacadeLocal {

    void create(Direction direction);

    void edit(Direction direction);

    void remove(Direction direction);

    Direction find(Object id);

    List<Direction> findAll();

    List<Direction> findRange(int[] range);

    int count();
    
}
