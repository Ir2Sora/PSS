package dao;

import entity.Department;
import entity.Status;
import entity.Suggestion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return null;
    }
}
