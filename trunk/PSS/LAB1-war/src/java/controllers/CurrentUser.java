package controllers;

import dao.ApplicationInfo;
import dao.UserFacadeLocal;
import entity.User;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import utils.FacesUtils;

@Named
@SessionScoped
@Stateful
@Interceptors(interceptor.CurrentUserInterceptor.class)
public class CurrentUser implements CurrentUserLocal{

    @Inject 
    private UserFacadeLocal userFacade;
    @Inject
    ApplicationInfo applicationInfo;
    private User user;
    
    public CurrentUser(){
        user = new User();
    }
    
    @PostConstruct
    void init(){
        String login = FacesUtils.getCurrentUserLogin();
        if (login != null){
            user = userFacade.findByLogin(login);
        }
    }

    @Override
    public User getUser() {
        if (user == null){
            init();
        }
        return user;
    }

    @Override
    public String logIn() {
        return "/user/index.xhtml?faces-redirect=true";
    }

    @Override
    public String logOut() {
        FacesUtils.invalidateSession();
        return "/user/index.xhtml?faces-redirect=true";        
    }
    
    @Override
    public int getNumberOfUsers() {
        return applicationInfo.getNumberOfUsers();
    }
    
}
