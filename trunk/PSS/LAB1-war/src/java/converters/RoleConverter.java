/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="roleConverter")
public class RoleConverter extends EnumConverter {

    public RoleConverter() {
        super(dao.Role.class);
    }

}
