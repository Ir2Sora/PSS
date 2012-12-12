/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByIdDepartment", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByDepartmentNumber", query = "SELECT d FROM Department d WHERE d.departmentNumber = :departmentNumber"),
    @NamedQuery(name = "Department.findByShortName", query = "SELECT d FROM Department d WHERE d.shortName = :shortName"),
    @NamedQuery(name = "Department.findByFullName", query = "SELECT d FROM Department d WHERE d.fullName = :fullName")})
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_department")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "department_number")
    private int departmentNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "short_name")
    private String shortName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "full_name")
    private String fullName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Collection<Direction> directions;
    @OneToMany
    private Collection<User> users;

    public Department() {
    }

    public Department(Integer idDepartment) {
        this.id = idDepartment;
    }

    public Department(Integer idDepartment, int departmentNumber, String shortName, String fullName) {
        this.id = idDepartment;
        this.departmentNumber = departmentNumber;
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idDepartment) {
        this.id = idDepartment;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlTransient
    public Collection<Direction> getDirections() {
        return directions;
    }

    public void setDirections(Collection<Direction> directionCollection) {
        this.directions = directionCollection;
    }

    @XmlTransient
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> userCollection) {
        this.users = userCollection;
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
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Department[ idDepartment=" + id + " ]";
    }
    
}
