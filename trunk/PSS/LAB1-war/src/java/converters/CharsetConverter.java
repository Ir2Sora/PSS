/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import java.io.UnsupportedEncodingException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="latin1_to_UTF-8")
public class CharsetConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
            return new String(value.getBytes("iso-8859-1"), "UTF-8");
        }catch(UnsupportedEncodingException ex){
            throw new ConverterException(ex);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof String){
            String string = (String) value;
                return string;
        }
        else throw new ConverterException();
    }
}
