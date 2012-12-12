package dao;

import entity.Role;
import entity.User;
import java.io.Serializable;
import javax.ejb.Remote;

@Remote
public interface DAORemote extends Serializable{
    void addRoleForUser(User user, Role role) throws PSSDAOException;
    void removeRoleFromUser(User user, Role role) throws PSSDAOException;
}
