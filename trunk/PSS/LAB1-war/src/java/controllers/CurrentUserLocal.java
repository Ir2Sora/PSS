package controllers;

import dao.User;
import java.io.Serializable;
import javax.ejb.Local;

@Local
public interface CurrentUserLocal extends Serializable{
    User getUser();
    String logIn();
    String logOut(); 
    int getNumberOfUsers();
}
