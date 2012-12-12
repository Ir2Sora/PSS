package controllers;

import dao.ApplicationInfo;
import dao.User;
import dao.UserFacadeLocal;
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
    private UserFacadeLocal userFacade;
    @Inject
    ApplicationInfo applicationInfo;
    private User user;
    
    public CurrentUser(){
        user = new User();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String logIn() {
        user = userFacade.getUserByLogin(user.getLogin());
        conversation.begin();
        return "/user/index.xhtml?faces-redirect=true";
    }

    @Override
    public String logOut() {
        conversation.end();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/user/index.xhtml?faces-redirect=true";        
    }
    
    @Override
    public int getNumberOfUsers() {
        return applicationInfo.getNumberOfUsers();
    }
    
}
