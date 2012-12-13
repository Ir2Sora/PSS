
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дом
 */
@Entity
@Table(name = "usergroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usergroup.findAll", query = "SELECT u FROM Usergroup u"),
    @NamedQuery(name = "Usergroup.findByLogin", query = "SELECT u FROM Usergroup u WHERE u.usergroupPK.login = :login"),
    @NamedQuery(name = "Usergroup.findByRole", query = "SELECT u FROM Usergroup u WHERE u.usergroupPK.role = :role")})
public class Usergroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsergroupPK usergroupPK;
    @JoinColumn(name = "login", referencedColumnName = "login", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Usergroup() {
    }

    public Usergroup(UsergroupPK usergroupPK) {
        this.usergroupPK = usergroupPK;
    }

    public Usergroup(String login, String role) {
        this.usergroupPK = new UsergroupPK(login, role);
    }

    public UsergroupPK getUsergroupPK() {
        return usergroupPK;
    }

    public void setUsergroupPK(UsergroupPK usergroupPK) {
        this.usergroupPK = usergroupPK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usergroupPK != null ? usergroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usergroup)) {
            return false;
        }
        Usergroup other = (Usergroup) object;
        if ((this.usergroupPK == null && other.usergroupPK != null) || (this.usergroupPK != null && !this.usergroupPK.equals(other.usergroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usergroup[ usergroupPK=" + usergroupPK + " ]";
    }

}
