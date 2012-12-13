package controllers;

import dao.SuggestionFacadeLocal;
import entity.Direction;
import entity.Status;
import entity.Suggestion;
import entity.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    private SuggestionFacadeLocal suggestionFacade;
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
        suggestion.setInitiator(currentUser.getUser());
        suggestion.setDateOfReceipt(new Date());
        suggestionFacade.create(suggestion);
        suggestion = new Suggestion();
        sendGrowlMessage("Инициатива успешно подана");
    }

    public void save(Suggestion sugg) {
        suggestionFacade.edit(sugg);
        sendGrowlMessage("Изменения успешно сохранены");
    }

    public void removeDirection(Direction direction) {
//        if (direction.getServiceStatus().equals(ServiceStatus.NEW))
//            suggestion.getDirections().remove(direction);
//        direction.remove();
    }

    public void addDirection() {
        suggestion.getDirections().add(new Direction());
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
        getDirectionByDepartmentOfCurrentUser().setEnumStatus(Status.ReceivedPeerRewiew);
        save(sugg);
        return null;
    }

    public String improve(Suggestion sugg) {
        sugg.setEnumStatus(Status.Improved);
        save(sugg);
        return null;
    }

    public String selectForwritePeerReview() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Collection finded = suggestionFacade.getForWritePeerReview(currentUser.getUser().getDepartment());
        Flash flash = ec.getFlash();
        flash.putNow("finded", finded);
        return "/expert/writePeerReview";
    }

    public String selectOwnSuggestions() {
        Collection<Suggestion> finded = suggestionFacade.findByInitiator(getCurrentUserLogin());
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.putNow("finded", finded);
        return "/user/viewOwnSuggestions";
    }

    public String select() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Collection<Suggestion> finded = suggestionFacade.getForWorkGroup(suggestion);
        Flash flash = ec.getFlash();
        flash.putNow("finded", finded);
        return "/workGroup/viewSuggestions";
    }

    public String selectSuggestions() {
        return "/workGroup/selectSuggestion.xhtml";
    }
}
