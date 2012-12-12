/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Дом
 */
@Embeddable
public class DirectionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_suggestion")
    private int idSuggestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "direction")
    private int direction;

    public DirectionPK() {
    }

    public DirectionPK(int idSuggestion, int direction) {
        this.idSuggestion = idSuggestion;
        this.direction = direction;
    }

    public int getIdSuggestion() {
        return idSuggestion;
    }

    public void setIdSuggestion(int idSuggestion) {
        this.idSuggestion = idSuggestion;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSuggestion;
        hash += (int) direction;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirectionPK)) {
            return false;
        }
        DirectionPK other = (DirectionPK) object;
        if (this.idSuggestion != other.idSuggestion) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.DirectionPK[ idSuggestion=" + idSuggestion + ", direction=" + direction + " ]";
    }
    
}
