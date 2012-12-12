package dao;

import entity.Department;
import entity.Direction;
import entity.Role;
import entity.Status;
import entity.Suggestion;
import entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;

@LocalBean
@Stateless(name="dao")
public class Dao implements DAORemote{
    private Connection connection;
    private PreparedStatement getSuggestionsByDirectionStatusPS;
    private PreparedStatement getSuggestionsByStatusAndDirectionPS;
    private PreparedStatement getSuggestionsByDirectionStatusAndDepartmentPS;
    
    private PreparedStatement saveDirectionPS;
    private PreparedStatement addDirectionPS;
    private PreparedStatement removeDirectionPS;
    
    private PreparedStatement getDepartmentByIDPS;
    
    private PreparedStatement getUserByIDPS;
   
    private PreparedStatement getUserRolesPS;
    private PreparedStatement addRoleForUserPS;
    private PreparedStatement removeRoleFromUserPS;
       
    @Resource(name="jdbc/PSSDB")
    private DataSource ds;
    @PersistenceContext(unitName = "PSSPU")
    private EntityManager em;
    
    public Dao() {
    }
    
    @PostConstruct
    public void init() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PSSDB");
            this.connection = ds.getConnection();
            
            setCharsets();
            
            getSuggestionsByDirectionStatusPS = connection.prepareStatement("select * from suggestion where id_suggestion in (select id_suggestion from directions where status=?)");
            getSuggestionsByStatusAndDirectionPS = connection.prepareStatement(
                    "select * from suggestion where status=? and id_suggestion in (select id_suggestion from directions where direction=?)");
            getSuggestionsByDirectionStatusAndDepartmentPS = connection.prepareStatement("select * from suggestion where id_suggestion in (select id_suggestion from directions where status=? and direction=?)");
            
            saveDirectionPS = connection.prepareStatement("update directions set status=?, conclusion=? where id_suggestion=? and direction=?");
            addDirectionPS = connection.prepareStatement("insert directions values(?,?,?,?)");
            removeDirectionPS = connection.prepareStatement("delete from directions where id_suggestion=? and direction=?");
                        
            getDepartmentByIDPS = connection.prepareStatement("select * from department where id_department=?");
             
            getUserByIDPS = connection.prepareStatement("select * from user where id_user=?");       
            
            getUserRolesPS = connection.prepareStatement("select role from usergroup where login=?");
            addRoleForUserPS = connection.prepareStatement("insert usergroup (login, role) values(?,?)");
            removeRoleFromUserPS = connection.prepareStatement("delete from usergroup where login=? and role=?");
        } catch (SQLException ex) {
            System.err.println("Alarm!! " + ex);
            throw new RuntimeException(ex);
        } catch (NamingException ex){
            System.err.println("Alarm!! " + ex);
            throw new RuntimeException(ex); 
        }
    }
    
    private void setCharsets() throws SQLException{
        connection.prepareStatement("SET character_set_client='utf8'").execute();
        connection.prepareStatement("SET character_set_connection='utf8'").execute();
        connection.prepareStatement("SET character_set_results='utf8'").execute();
        connection.prepareStatement("SET NAMES 'utf8'").execute();
    }
    
    private Collection<Suggestion> getSuggestionsFromPreparedStatement(PreparedStatement preparedStatement) throws PSSDAOException, SQLException{
        ResultSet resultSet = null;
        try{
            resultSet = preparedStatement.executeQuery();
            ArrayList<Suggestion> result = new ArrayList<Suggestion>();
            while (resultSet.next()) {
                result.add(getSuggestionFromResultSet(resultSet));
            }            
            return result;
        }finally{
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
    
    private void saveDirections(int id_suggestion, Collection<Direction> directions)throws PSSDAOException{
        for (Direction direction:directions){
            switch (direction.getServiceStatus()){
                case NEW:       addDirection(id_suggestion, direction); break;
                case CHANGED:   saveDirection(id_suggestion, direction); break;
                case REMOVED:   removeDirection(id_suggestion, direction); break;
                case CONSTANT:  break;
            }
        }
    }
    
    private void addDirection(int id_suggestion, Direction direction) throws PSSDAOException{
        try{
            addDirectionPS.setInt(1, id_suggestion);
            addDirectionPS.setString(2, direction.getStatus().name());
            addDirectionPS.setInt(3, direction.getDepartment().getId());
            addDirectionPS.setString(4, direction.getConclusion());
            addDirectionPS.executeUpdate();
        } catch (SQLException ex){
            throw new PSSDAOException(ex);
        }
    }
    
    private void saveDirection(int id_suggestion, Direction direction) throws PSSDAOException{
        try{
            saveDirectionPS.setString(1, direction.getStatus().name());
            saveDirectionPS.setString(2, direction.getConclusion());
            saveDirectionPS.setInt(3, id_suggestion);
            saveDirectionPS.setInt(4, direction.getDepartment().getId());
            saveDirectionPS.executeUpdate();
        } catch (SQLException ex){
            throw new PSSDAOException(ex);
        }
    }
    
    private void removeDirection(int id_suggestion, Direction direction) throws PSSDAOException{
        try{
            removeDirectionPS.setInt(1, id_suggestion);
            removeDirectionPS.setInt(2, direction.getDepartment().getId());
            removeDirectionPS.executeUpdate();
        } catch (SQLException ex){
            throw new PSSDAOException(ex);
        }
    }
    
    private Collection<Direction> getDirectionsBySuggestionID(int id_suggestion) throws PSSDAOException{
        try{
            List rs = em.createNamedQuery("Direction.findByIdSuggestion").setParameter("idSuggestion", id_suggestion)
                .getResultList();
            return (Collection<Direction>) rs;
        } catch (PersistenceException ex){
            throw new PSSDAOException(ex);
        }
    }
    
    private Collection<Role> getUserRoles(String login) throws SQLException{
        ResultSet resultSet = null;
        try{
            getUserRolesPS.setString(1, login);
            resultSet = getUserRolesPS.executeQuery();
            ArrayList<Role> result = new ArrayList<Role>();
            while (resultSet.next()) {
                result.add(Role.valueOf(resultSet.getString("role")));
            }
            return result;
        } finally{
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
    
    @Override
    public void addRoleForUser(User user, Role role) throws PSSDAOException {
        try{
            addRoleForUserPS.setString(1, user.getLogin());
            addRoleForUserPS.setString(2, role.name());
            addRoleForUserPS.executeUpdate();
        }catch(SQLException ex){
            throw new PSSDAOException(ex);
        }
    }

    @Override
    public void removeRoleFromUser(User user, Role role) throws PSSDAOException {
        try{
            removeRoleFromUserPS.setString(1, user.getLogin());
            removeRoleFromUserPS.setString(2, role.name());
            removeRoleFromUserPS.executeUpdate();
        }catch(SQLException ex){
            throw new PSSDAOException(ex);
        }
    }

    private Suggestion getSuggestionFromResultSet(ResultSet resultSet) throws SQLException, PSSDAOException{
        int id = resultSet.getInt("id_suggestion");
        Status status = Status.valueOf(resultSet.getString("status"));
        Collection<Direction> directions = getDirectionsBySuggestionID(id);
        User initiator = getUserByID(resultSet.getInt("id_initiator"));
        String problem = resultSet.getString("problem");
        String solution = resultSet.getString("solution");
        String result = resultSet.getString("result");
        int id_4i = resultSet.getInt("id_4i");
        Date Date_of_receipt = resultSet.getDate("Date_of_receipt");
        Date Date_of_consideration = resultSet.getDate("Date_of_consideration");
        return new Suggestion(id, status, directions, initiator, problem, solution, result, id_4i, Date_of_receipt, Date_of_consideration);
    }
    
    
    private Department getDepartmentFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id_department");
        int departmentNumber = resultSet.getInt("department_number");
        String shortName = resultSet.getString("short_name");
        String fullName = resultSet.getString("full_name");
        return new Department(id, departmentNumber, shortName, fullName);
    }
         
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException{
        User user = new User();
        user.setId(resultSet.getInt("id_user"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPatronymic(resultSet.getString("patronymic"));
        user.setDepartment(getDepartmentByID(resultSet.getInt("id_department")));
        user.setLogin((resultSet.getString("login")));
        user.setPassword(resultSet.getString("passwd"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
    
    private Department getDepartmentByID(int id) throws SQLException{
        getDepartmentByIDPS.setInt(1, id);
        ResultSet resultSet = null;
        try{
            resultSet = getDepartmentByIDPS.executeQuery();
            Department result = null;
            if (resultSet.next())
                result = getDepartmentFromResultSet(resultSet);
            return result;
        }finally{
            if(resultSet != null) resultSet.close();
        }
    }
    
    private User getUserByID(int id) throws SQLException{
        getUserByIDPS.setInt(1, id);
        ResultSet resultSet = null;
        try{
            resultSet = getUserByIDPS.executeQuery();
            User result = null;
            if (resultSet.next())
                result = getUserFromResultSet(resultSet);
            return result;
        }finally{
            if(resultSet != null) resultSet.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
