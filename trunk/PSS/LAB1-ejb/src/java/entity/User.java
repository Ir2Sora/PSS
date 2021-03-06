package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Дом
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPasswd", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname"),
    @NamedQuery(name = "User.findByPatronymic", query = "SELECT u FROM User u WHERE u.patronymic = :patronymic"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "passwd")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "patronymic")
    private String patronymic;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Недопустимый адрес электронной почты")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "initiator")
    private Collection<Suggestion> suggestions;
    @JoinColumn(name = "id_department", referencedColumnName = "id_department")
    @ManyToOne
    private Department department;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")   
    private Collection<Usergroup> usergroups;

    public User() {
    }

    public User(Integer idUser) {
        this.id = idUser;
    }

    public User(Integer idUser, String login, String passwd, String firstName, String surname, String patronymic, String email) {
        this.id = idUser;
        this.login = login;
        this.password = passwd;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idUser) {
        this.id = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Collection<Suggestion> suggestionCollection) {
        this.suggestions = suggestionCollection;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.User[ idUser=" + id + " ]";
    }
    
    public Collection<Role> getRoles() {
        Collection<Role> roles = new ArrayList<Role>();
        for (Usergroup usergroup:usergroups){
            Role role = Role.valueOf(usergroup.usergroupPK.getRole());
            roles.add(role);
        }
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        Collection<Usergroup> usergroups = new ArrayList<Usergroup>();
        for (Role role:roles){
            Usergroup usergroup = new Usergroup(login, role.name());
            usergroups.add(usergroup);
        }
        this.usergroups = usergroups;
    }   
    
    public Collection<String> getRolesView() {
        Collection<String> roles = new ArrayList<String>();
        for (Usergroup usergroup:usergroups){
            roles.add(usergroup.usergroupPK.getRole());
        }
        return roles;
    }

    public void setRolesView(Collection<String> roles) {
        Collection<Usergroup> usergroups = new ArrayList<Usergroup>();
        for (String role:roles){
            Usergroup usergroup = new Usergroup(login, role);
            usergroups.add(usergroup);
        }
        this.usergroups = usergroups;
    }   
}
