package dao;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class ApplicationInfo {
    private int numberOfUsers = 0;

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void incrementNumberOfUsers() {
        numberOfUsers++;
    }
    
    public int decrementNumberOfUsers() {
        return numberOfUsers;
    }

}
