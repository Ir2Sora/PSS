/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Department;
import entity.Status;
import entity.Suggestion;
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

    List<Suggestion> findByInitiator(String login);

    List<Suggestion> findByStatus(Status status);

    List<Suggestion> getForWritePeerReview(Department department);

    List<Suggestion> getForWorkGroup(Suggestion suggestion);
    
}
