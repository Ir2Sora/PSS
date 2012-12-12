package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {

    private static FacesContext context = FacesContext.getCurrentInstance();

    public static void sendGrowlMessage(String message) {
        context.addMessage(null, new FacesMessage(message));
    }
}
