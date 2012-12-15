package dao;

import entity.Department;
import entity.Direction;
import entity.Status;
import entity.Suggestion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Дом
 */
@Stateless
public class SuggestionFacade extends AbstractFacade<Suggestion> implements SuggestionFacadeLocal {

    @PersistenceContext(unitName = "PSSPU")
    private EntityManager em;
    @Inject
    private DirectionFacadeLocal directionFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuggestionFacade() {
        super(Suggestion.class);
    }

    @Override
    public void edit(Suggestion suggestion) {
        for (Direction direction : suggestion.getDirections()) {
            if (direction.isNew()) {
                directionFacade.create(direction);
            }
        }
        super.edit(suggestion);
    }

    @Override
    public List<Suggestion> findByInitiator(String login) {
        return em.createNamedQuery("Suggestion.findByInitiator").setParameter("initiator", login)
                .getResultList();
    }

    @Override
    public List<Suggestion> findByStatus(Status status) {
        return em.createNamedQuery("Suggestion.findByStatus").setParameter("status", status.name())
                .getResultList();
    }

    @Override
    public List<Suggestion> getForWritePeerReview(Department department) {
        List<Suggestion> result = em.createNamedQuery("Suggestion.selectForWritePeerReview")
                .setParameter("department", department).getResultList();
        for (Suggestion s : result) {
            s.setDirection(s.getDirections().iterator().next());
        }
        return result;
    }

    @Override
    public List<Suggestion> getForWorkGroup(Suggestion suggestion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT s FROM Suggestion s ");
        List<String> conditions = new ArrayList<String>();
        if (suggestion.getDirection().getEnumStatus() != null) {
            query.append("JOIN s.directions d ");
            conditions.add("d.status = :directionStatus");
        }
        if (suggestion.getSearchDateOfReceipt() != null) {
            conditions.add("s.dateOfReceipt = :dateOfReceipt");
        }
        if (suggestion.getInitiator().getDepartment() != null) {
            conditions.add("s.initiator.department = :department");
        }
        if (suggestion.getEnumStatus() != null) {
            conditions.add("s.status = :suggestionStatus");
        }

        if (!conditions.isEmpty()) {
            query.append("WHERE ");
            for (int i = 0; i < conditions.size(); i++) {
                if (i > 0) {
                    query.append(" AND ");
                }
                query.append(conditions.get(i)).append(" ");
            }
        }

        Query q = em.createQuery(query.toString());
        if (suggestion.getSearchDateOfReceipt() != null) {
            q.setParameter("dateOfReceipt", suggestion.getSearchDateOfReceipt());
        }
        if (suggestion.getInitiator().getDepartment() != null) {
            q.setParameter("department", suggestion.getInitiator().getDepartment());
        }
        if (suggestion.getEnumStatus() != null) {
            q.setParameter("suggestionStatus", suggestion.getStatus());
        }
        if (suggestion.getDirection().getEnumStatus() != null) {
            q.setParameter("directionStatus", suggestion.getDirection().getStatus());
        }

        return q.getResultList();
    }
}
