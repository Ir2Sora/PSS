package dao;

import entity.User;
import entity.Usergroup;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Дом
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "PSSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    
    @Override
    public void edit(User user){
        List<Usergroup> old = em.createNamedQuery("Usergroup.findByLogin")
                .setParameter("login", user.getLogin()).getResultList();
        Collection<String> now = user.getRolesView();
        for (Usergroup usergroup:old){
            if (!now.contains(usergroup.getUsergroupPK().getRole())){
                Usergroup removable = em.find(Usergroup.class, usergroup.getUsergroupPK());
                em.remove(removable);
            }
        }
        super.edit(user);
    }

    @Override
    public User findByLogin(String login) {
        List<User> result = em.createNamedQuery("User.findByLogin").setParameter("login", login).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
