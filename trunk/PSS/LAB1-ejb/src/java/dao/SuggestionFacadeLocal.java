/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Дом
 */
@Local
public interface SuggestionFacadeLocal {

    void create(Suggestion suggestion);

    void edit(Suggestion suggestion);

    void remove(Suggestion suggestion);

    Suggestion find(Object id);

    List<Suggestion> findAll();

    List<Suggestion> findRange(int[] range);

    int count();
    
}
