package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "suggestion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suggestion.findAll", query = "SELECT s FROM Suggestion s"),
    @NamedQuery(name = "Suggestion.findById", query = "SELECT s FROM Suggestion s WHERE s.id = :id"),
    @NamedQuery(name = "Suggestion.findByInitiator", query = "SELECT s FROM Suggestion s WHERE s.initiator.login = :initiator"),
    @NamedQuery(name = "Suggestion.findByDepartment", query = "SELECT s FROM Suggestion s WHERE s.initiator.department = :department"),
    @NamedQuery(name = "Suggestion.findByStatus", query = "SELECT s FROM Suggestion s WHERE s.status = :status"),
    @NamedQuery(name = "Suggestion.findByDateofreceipt", query = "SELECT s FROM Suggestion s WHERE s.dateOfReceipt = :dateOfReceipt"),
    @NamedQuery(name = "Suggestion.findByDateofconsideration", query = "SELECT s FROM Suggestion s WHERE s.dateOfConsideration = :dateOfConsideration")})
public class Suggestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_suggestion")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "problem")
    private String problem;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "solution")
    private String solution;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "result")
    private String result;
    @Column(name = "id_4i")
    private Integer id4i;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_of_receipt")
    @Temporal(TemporalType.DATE)
    private Date dateOfReceipt;
    @Column(name = "Date_of_consideration")
    @Temporal(TemporalType.DATE)
    private Date dateOfConsideration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "suggestion")
    private Collection<Direction> directions;
    @JoinColumn(name = "id_initiator", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User initiator;

    public Suggestion() {
    //    id = -1;
        status = Status.NEW.name();
    }

    public Suggestion(Integer idSuggestion) {
        this.id = idSuggestion;
        status = Status.NEW.name();
    }

    public Suggestion(Integer idSuggestion, String status, String problem, String solution, String result, Date dateofreceipt) {
        this.id = idSuggestion;
        this.status = status;
        this.problem = problem;
        this.solution = solution;
        this.result = result;
        this.dateOfReceipt = dateofreceipt;
    }

    public Suggestion(Integer id, Status status, Collection<Direction> directions, User initiator, String problem, String solution, String result, Integer id4i, Date dateOfReceipt, Date dateOfConsideration) {
        this.id = id;
        this.status = status.name();
        this.problem = problem;
        this.solution = solution;
        this.result = result;
        this.id4i = id4i;
        this.dateOfReceipt = dateOfReceipt;
        this.dateOfConsideration = dateOfConsideration;
        this.directions = directions;
        this.initiator = initiator;
    }
    


    public Integer getId() {
        return id;
    }

    public void setId(Integer idSuggestion) {
        this.id = idSuggestion;
    }
    
    public String getStatus() {
        return status;
    }

    public Status getEnumStatus() {
        return Status.valueOf(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setEnumStatus(Status status) {
        this.status = status.name();
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getId4i() {
        return id4i;
    }

    public void setId4i(Integer id4i) {
        this.id4i = id4i;
    }

    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateofreceipt) {
        this.dateOfReceipt = dateofreceipt;
    }

    public Date getDateOfConsideration() {
        return dateOfConsideration;
    }

    public void setDateOfConsideration(Date dateofconsideration) {
        this.dateOfConsideration = dateofconsideration;
    }

    @XmlTransient
    public Collection<Direction> getDirections() {
        return directions;
    }

    public void setDirections(Collection<Direction> directionCollection) {
        this.directions = directionCollection;
    }

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User idInitiator) {
        this.initiator = idInitiator;
    }
    
    public boolean isNeedImprovement(){
        return getEnumStatus() == Status.RequireImprovement;
    }
    
    public boolean isRecommended(){
        return getEnumStatus() == Status.Recommended;
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
        if (!(object instanceof Suggestion)) {
            return false;
        }
        Suggestion other = (Suggestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Suggestion[ idSuggestion=" + id + " ]";
    }
    
}
