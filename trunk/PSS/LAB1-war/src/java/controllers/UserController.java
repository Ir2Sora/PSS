package controllers;

import dao.UserFacadeLocal;
import entity.Role;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import utils.FacesUtils;

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
    
    public List<Role> getAllRoles(){
        return Arrays.asList(Role.values());
    }

    public String register() {
        if (userFacade.findByLogin(user.getLogin()) != null) {
            FacesUtils.sendGrowlMessage("Данный логин уже используется");
            return null;
        } else {
            userFacade.create(user);
        }
        return "/user/index.xhtml?faces-redirect=true";

    }

    public String selectUserByLogin() {
        User finded = userFacade.findByLogin(user.getLogin());
        if (finded == null) {
            FacesUtils.sendGrowlMessage("Пользователь с таким логином не найден");
            return null;
        }
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.putNow("finded", finded);
        return "/admin/editUser?faces-redirect=true";
    }

    public void save(User user) {
        userFacade.edit(user);
        FacesUtils.sendGrowlMessage("Изменения успешно сохранены");
    }
}
