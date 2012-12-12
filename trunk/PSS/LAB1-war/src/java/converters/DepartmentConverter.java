package converters;

import dao.*;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
import javax.inject.Inject;
import javax.inject.Named;


@Named @ApplicationScoped @FacesConverter(forClass = Department.class)  
public class DepartmentConverter implements Converter, Serializable{
    private Dao dao = new Dao();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
            dao.init();
            int departmentNumber = Integer.parseInt(value);
            return dao.getDepartmentByNumber(departmentNumber);
        } catch(NumberFormatException ex){
            throw new ConverterException();
        } catch (PSSDAOException ex){
            throw new ConverterException();
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Department){
            Department department = (Department) value;
            return String.valueOf(department.getDepartmentNumber());
        }
        else {
            throw new ConverterException();
        }
    }
    
}
