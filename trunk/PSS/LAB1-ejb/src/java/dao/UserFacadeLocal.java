/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Дом
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();

    User findByLogin(String login);
    
}
