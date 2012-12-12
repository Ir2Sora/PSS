/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дом
 */
@Entity
@Table(name = "directions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direction.findAll", query = "SELECT d FROM Direction d"),
    @NamedQuery(name = "Direction.findByIdSuggestion", query = "SELECT d FROM Direction d WHERE d.directionPK.idSuggestion = :idSuggestion"),
    @NamedQuery(name = "Direction.findByStatus", query = "SELECT d FROM Direction d WHERE d.status = :status"),
    @NamedQuery(name = "Direction.findByDirection", query = "SELECT d FROM Direction d WHERE d.directionPK.direction = :direction")})
public class Direction implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DirectionPK directionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Lob
    @Size(max = 65535)
    @Column(name = "conclusion")
    private String conclusion;
    @JoinColumn(name = "id_suggestion", referencedColumnName = "id_suggestion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Suggestion suggestion;
    @JoinColumn(name = "direction", referencedColumnName = "id_department", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Department department;

//    private ServiceStatus serviceStatus;

    public Direction() {
    }

    public Direction(DirectionPK directionPK) {
        this.directionPK = directionPK;
    }

    public Direction(DirectionPK directionPK, String status) {
        this.directionPK = directionPK;
        this.status = status;
    }

    public Direction(int idSuggestion, int direction) {
        this.directionPK = new DirectionPK(idSuggestion, direction);
    }

    public DirectionPK getDirectionPK() {
        return directionPK;
    }

    public void setDirectionPK(DirectionPK directionPK) {
        this.directionPK = directionPK;
    }

    public Status getStatus() {
        return Status.valueOf(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setStatus(Status status) {
        this.status = status.name();
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ServiceStatus getServiceStatus() {
        return ServiceStatus.CHANGED;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (directionPK != null ? directionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direction)) {
            return false;
        }
        Direction other = (Direction) object;
        if ((this.directionPK == null && other.directionPK != null) || (this.directionPK != null && !this.directionPK.equals(other.directionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Direction[ directionPK=" + directionPK + " ]";
    }
    
}
