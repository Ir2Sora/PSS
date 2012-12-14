package controllers;

import dao.DirectionFacadeLocal;
import dao.SuggestionFacadeLocal;
import entity.Department;
import entity.Direction;
import entity.Status;
import entity.Suggestion;
import entity.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
    @Inject
    private DirectionFacadeLocal directionFacade;
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
        if (checkDirections(sugg.getDirections())) {
            deleteRemovedDirections(sugg.getDirections());
            suggestionFacade.edit(sugg);
            sendGrowlMessage("Изменения успешно сохранены");
        } else {
            sendGrowlMessage("В отдел нельзя отправить несколько направлений по одной инициативе");
        }
    }

    public Direction getDirectionFromSuggestionForPeerReview(Suggestion sugg) {
        for (Direction direction : sugg.getDirections()) {
            if (direction.getDepartment().getDepartmentNumber()
                    == currentUser.getUser().getDepartment().getDepartmentNumber()) {
                return direction;
            }
        }
        return null;
    }

    public String writePeerReview(Suggestion sugg) {
        getDirectionFromSuggestionForPeerReview(sugg).setEnumStatus(Status.ReceivedPeerRewiew);
        save(sugg);
        return null;
    }

    public String improve(Suggestion sugg) {
        sugg.setEnumStatus(Status.Improved);
        save(sugg);
        return null;
    }

    private boolean checkDirections(Collection<Direction> directions) {
        Set<Department> temp = new HashSet<Department>();
        for (Direction d : directions) {
            if (!d.isRemoved() && !temp.add(d.getDepartment())) {
                return false;
            }
        }
        return true;
    }

    private void deleteRemovedDirections(Collection<Direction> directions) {
        Iterator<Direction> iter = directions.iterator();
        while (iter.hasNext()) {
            Direction direction = iter.next();
            if (direction.isRemoved()) {
                directionFacade.remove(direction);
                iter.remove();
            }
        }
    }

    public String selectForwritePeerReview() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Collection<Suggestion> finded = suggestionFacade.getForWritePeerReview(currentUser.getUser().getDepartment());
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
