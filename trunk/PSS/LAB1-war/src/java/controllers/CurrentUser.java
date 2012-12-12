package controllers;

import dao.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

@Named
@ConversationScoped
@Stateful
@Interceptors(dao.CurrentUserInterceptor.class)
public class CurrentUser implements CurrentUserLocal{
    @Inject 
    private Conversation conversation;
    @Inject 
    private DAORemote dao;
    @Inject
    ApplicationInfo applicationInfo;
    private User user;
    
    public CurrentUser(){
        user = new User();
    }

    public User getUser() {
        return user;
    }
    
    public String logIn(){
        try {
            user = dao.getUserByLogin(user.getLogin());
        } catch (PSSDAOException ex) {
            Logger.getLogger(CurrentUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        conversation.begin();
        return "/user/index.xhtml?faces-redirect=true";  
    }
    
    public String logOut(){
        conversation.end();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/user/index.xhtml?faces-redirect=true";        
    }
    
    public int getNumberOfUsers() {
        return applicationInfo.getNumberOfUsers();
    }
    
}
