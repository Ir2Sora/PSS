package converters;

import dao.DepartmentFacadeLocal;
import entity.Department;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(forClass = Department.class)
public class DepartmentConverter implements Converter, Serializable {

    @Inject
    private DepartmentFacadeLocal departmentFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int departmentNumber = Integer.parseInt(value);
            return departmentFacade.findByNumber(departmentNumber);
        } catch (NumberFormatException ex) {
            throw new ConverterException();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Department) {
            Department department = (Department) value;
            return String.valueOf(department.getDepartmentNumber());
        } else {
            throw new ConverterException();
        }
    }
}
