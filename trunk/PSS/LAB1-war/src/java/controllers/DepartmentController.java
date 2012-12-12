package controllers;

import dao.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DepartmentController implements Serializable{
    @Inject private DAORemote dao;
    private Department department;
    
    public DepartmentController(){
    }

    public DepartmentController(Department department) {
        this.department = department;
    }

    public Department getDepartment(){
        return department;
    }
    
    public void setDepartment(Department department){
        this.department = department;
    }
    
    public String save(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (dao.getDepartmentByNumber(department.getDepartmentNumber()) != null){
                context.addMessage(null, 
                        new FacesMessage("Данный номер подразделения уже используется")); 
                return null;
            }
            else
                dao.saveDepartment(department);
            department.setDepartmentNumber(0);
            department.setShortName(null);
            department.setFullName(null);
            context.addMessage(null, 
                        new FacesMessage("Подразделение успешно добавлено")); 
            return null;
        } catch (PSSDAOException ex) {
            context.addMessage(null, 
                        new FacesMessage("Ошибка " + ex)); 
            return null;
        }
    }
    
    public Collection<Department> getListDepartments(){
        try{
            return dao.getAllDepartments();
        } catch(PSSDAOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Ошибка " + ex)); 
            return null;
        }
    }

}
