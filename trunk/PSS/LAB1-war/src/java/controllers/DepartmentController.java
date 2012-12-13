package controllers;

import dao.DepartmentFacadeLocal;
import entity.Department;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import static utils.FacesUtils.sendGrowlMessage;

@Named
@RequestScoped
public class DepartmentController implements Serializable {

    @Inject
    private DepartmentFacadeLocal departmentFacade;
    private Department department;

    public DepartmentController() {
        department = new Department();
    }

    public DepartmentController(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String save() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (departmentFacade.findByNumber(department.getDepartmentNumber()) != null) {
            context.addMessage(null,
                    new FacesMessage("Данный номер подразделения уже используется"));
            return null;
        } else {
            departmentFacade.edit(department);
        }
        department.setDepartmentNumber(0);
        department.setShortName(null);
        department.setFullName(null);
        sendGrowlMessage("Подразделение успешно добавлено");
        return null;

    }

    public Collection<Department> getListDepartments() {
        return departmentFacade.findAll();
    }
}
