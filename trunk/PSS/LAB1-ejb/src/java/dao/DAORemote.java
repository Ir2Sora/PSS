package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Remote;

@Remote
public interface DAORemote extends Serializable{
    void addSuggestion(Suggestion suggestion) throws PSSDAOException;
    void saveSuggestion(Suggestion suggestion) throws PSSDAOException;
    Collection<Suggestion> getAllSuggestions() throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByInitiator(String login) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByStatus(Status status) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByDirectionStatus(Status status) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByDepartment(Department department) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByDateOfReceipt(Date dateOfReceipt) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByStatusAndDepartment(Status status, Department direction) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByDirectionStatusAndDepartment(Status status, Department direction) throws PSSDAOException;
    Collection<Suggestion> getSuggestionsByStatusDepartmentDirectionStatusAndDateOfReceipt(Status status, Department direction, Status directionStatus, Date dateOfReceipt) throws PSSDAOException;
    Suggestion getSuggestionByID(int id) throws PSSDAOException;
    void saveDepartment(Department department) throws PSSDAOException;
    Collection<Department> getAllDepartments() throws PSSDAOException;
    Department getDepartmentByNumber(int number) throws PSSDAOException;
    void addUser(User user) throws PSSDAOException;
    void saveUser(User user) throws PSSDAOException;
    User getUserByLogin(String login) throws PSSDAOException;
    void addRoleForUser(User user, Role role) throws PSSDAOException;
    void removeRoleFromUser(User user, Role role) throws PSSDAOException;
}
