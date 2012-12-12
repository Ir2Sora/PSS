package utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class FacesUtils {

    public FacesUtils() {
    }

    public static void sendGrowlMessage(String message) {
        getContext().addMessage(null, new FacesMessage(message));
    }

    public static String getCurrentUserLogin() {
        return getContext().getExternalContext().getRemoteUser();
    }
    
    public static void invalidateSession() {
        HttpSession httpSession = (HttpSession) getContext().getExternalContext().getSession(false);
        httpSession.invalidate();
    }

    private static FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
}
