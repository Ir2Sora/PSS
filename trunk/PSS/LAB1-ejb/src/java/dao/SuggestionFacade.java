package dao;

import entity.Department;
import entity.Status;
import entity.Suggestion;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Дом
 */
@Stateless
public class SuggestionFacade extends AbstractFacade<Suggestion> implements SuggestionFacadeLocal {

    @PersistenceContext(unitName = "PSSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuggestionFacade() {
        super(Suggestion.class);
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
        //TODO
        //Status.RequestedPeerRewiew;
        return em.createNamedQuery("Suggestion.findByDepartment").setParameter("department", department)
                .getResultList();
    }

    @Override
    public List<Suggestion> getForWorkGroup(Suggestion suggestion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT s FROM Suggestion s ");
        List<String> conditions = new ArrayList<String>();
        if (suggestion.getDateOfReceipt() != null) {
            conditions.add("s.dateOfReceipt = :dateOfReceipt");
        }
        if (suggestion.getInitiator().getDepartment() != null) {
            conditions.add("s.initiator.department = :department");
        }
        if (suggestion.getStatus() != null || !suggestion.getStatus().isEmpty()) {
            conditions.add("s.status = :status");
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
        if (suggestion.getDateOfReceipt() != null) {
            q.setParameter("dateOfReceipt", suggestion.getDateOfReceipt());
        }
        if (suggestion.getInitiator().getDepartment() != null) {
            q.setParameter("department", suggestion.getInitiator().getDepartment());
        }
        if (suggestion.getStatus() != null || !suggestion.getStatus().isEmpty()) {
            q.setParameter("status", suggestion.getStatus());
        }

        return q.getResultList();
    }
}
