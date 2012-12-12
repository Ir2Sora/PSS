package controllers;

import dao.*;
import entity.Direction;
import entity.Status;
import entity.Suggestion;
import entity.User;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import static utils.FacesUtils.getCurrentUserLogin;
import static utils.FacesUtils.sendGrowlMessage;

@Named
@RequestScoped
public class SuggestionController implements Serializable {

    @Inject
    private DAORemote dao;
    private Suggestion suggestion;
    @Inject
    private CurrentUserLocal currentUser;

    public SuggestionController() {
        suggestion = new Suggestion();
        suggestion.setInitiator(new User());
    }

    SuggestionController(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public void submitSuggestion() {
        try {
            suggestion.setInitiator(currentUser.getUser());
            suggestion.setDateOfReceipt(new Date());
            dao.addSuggestion(suggestion);
            suggestion = new Suggestion();
            sendGrowlMessage("Инициатива успешно подана");
        } catch (PSSDAOException ex) {
            sendGrowlMessage("Ошибка. " + ex);
        }
    }

    public void save(Suggestion sugg) {
        try {
            dao.saveSuggestion(sugg);
            sendGrowlMessage("Изменения успешно сохранены");
        } catch (PSSDAOException ex) {
            sendGrowlMessage("Ошибка. " + ex);
        }
    }

    public void removeDirection(Direction direction) {
//        if (direction.getServiceStatus().equals(ServiceStatus.NEW))
//            suggestion.getDirections().remove(direction);
//        direction.remove();
    }

    public void addDirection() {
        suggestion.getDirections().add(new Direction());
    }

    public String getTitle() {
        StringBuilder title = new StringBuilder();
        title.append("id=").append(suggestion.getId()).append("; ");
        title.append("инициатор=").append(suggestion.getInitiator().getLogin()).append("; ");
        title.append("status=").append(suggestion.getEnumStatus().getDescription()).append("; ");
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        title.append(" дата создания=").append(df.format(suggestion.getDateOfReceipt()));
        return title.toString();
    }

    public Direction getDirectionByDepartmentOfCurrentUser() {
        for (Direction direction : suggestion.getDirections()) {
            if (direction.getDepartment().getDepartmentNumber()
                    == currentUser.getUser().getDepartment().getDepartmentNumber()) {
                return direction;
            }
        }
        return null;
    }

    public String writePeerReview(Suggestion sugg) {
        getDirectionByDepartmentOfCurrentUser().setStatus(Status.ReceivedPeerRewiew);
        save(sugg);
        return null;
    }

    public String improve(Suggestion sugg) {
        if (sugg.getEnumStatus() == Status.RequireImprovement) {
            sugg.setEnumStatus(Status.Improved);
        } else if (sugg.getEnumStatus() == Status.Recommended) {
            sugg.setEnumStatus(Status.Registered);
        }
        save(sugg);
        return null;
    }

    public String selectForwritePeerReview() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Collection finded = dao.getSuggestionsByDirectionStatusAndDepartment(Status.RequestedPeerRewiew, currentUser.getUser().getDepartment());
            Flash flash = ec.getFlash();
            flash.putNow("finded", finded);
            return "/expert/writePeerReview";
        } catch (PSSDAOException ex) {
            sendGrowlMessage("Ошибка. " + ex);
            return "";
        }
    }

    public String selectOwnSuggestions() {
        try {
            Collection<Suggestion> finded = dao.getSuggestionsByInitiator(getCurrentUserLogin());
            List<SuggestionController> listControllers = new ArrayList();
            for (Suggestion sugg : finded) {
                listControllers.add(new SuggestionController(sugg));
            }
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.putNow("finded", listControllers);
            return "/user/viewOwnSuggestions";
        } catch (PSSDAOException ex) {
            sendGrowlMessage("Ошибка. " + ex);
            return "";
        }
    }

    public String select() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            Collection<Suggestion> finded = dao.getSuggestionsByStatusDepartmentDirectionStatusAndDateOfReceipt(suggestion.getEnumStatus(), currentUser.getUser().getDepartment(), null, suggestion.getDateOfReceipt());
            List<SuggestionController> listControllers = new ArrayList();
            for (Suggestion sugg : finded) {
                listControllers.add(new SuggestionController(sugg));
            }
            Flash flash = ec.getFlash();
            flash.putNow("finded", listControllers);
            return "/workGroup/viewSuggestions";
        } catch (PSSDAOException ex) {
            sendGrowlMessage("Ошибка. " + ex);
            return null;
        }
    }

    public String selectSuggestions() {
        return "/workGroup/selectSuggestion.xhtml";
    }
}
