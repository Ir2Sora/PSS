package controllers;

import dao.User;
import dao.UserFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ManagedBean
@RequestScoped
public class UserController implements Serializable {

    @EJB
    private UserFacadeLocal userFacade;
    private User user;

    public UserController() {
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

    public String register() {
        if (userFacade.getUserByLogin(user.getLogin()) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Данный логин уже используется"));
            return null;
        } else {
            userFacade.create(user);
        }
        return "/user/index.xhtml?faces-redirect=true";

    }

    public String selectUserByLogin() {
        User finded = userFacade.getUserByLogin(user.getLogin());
        if (finded == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Пользователь с таким логином не найден"));
            return null;
        }
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.putNow("finded", finded);
        return "/admin/editUser?faces-redirect=true";
    }

    public void save(User user) {
        userFacade.edit(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Изменения успешно сохранены"));
    }
}
