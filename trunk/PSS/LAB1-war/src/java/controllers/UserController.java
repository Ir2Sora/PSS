package controllers;

import dao.*;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ManagedBean
@RequestScoped
public class UserController implements Serializable{
    @EJB private DAORemote dao;
    private User user;

    public UserController(){
        user = new User();
    }

    UserController(User user) {
        this.user = user;
    }  

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String register(){
        try {
            if (dao.getUserByLogin(user.getLogin()) != null){
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Данный логин уже используется")); 
                return null;
            }
            else
                dao.addUser(user);
            return "/user/index.xhtml?faces-redirect=true";
        } catch (PSSDAOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Ошибка " + ex)); 
            return null;
        }
        
    }
    
    public String selectUserByLogin(){
        try {
            User finded = dao.getUserByLogin(user.getLogin());
            if (finded == null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Пользователь с таким логином не найден")); 
                return null;               
            }
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.putNow("finded", finded);
            return "/admin/editUser?faces-redirect=true";
        } catch (PSSDAOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Ошибка " + ex)); 
            return null;
        }
    }
    
    public void save(User user){
        try{
            dao.saveUser(user);
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Изменения успешно сохранены")); 
        }catch(PSSDAOException ex){
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Ошибка " + ex)); 
        }
    }
}
