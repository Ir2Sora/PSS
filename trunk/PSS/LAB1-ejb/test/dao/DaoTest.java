/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DaoTest {
    
    public DaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveSuggestion method, of class Dao.
     */
    @Test
    public void testSaveSuggestion() throws Exception {
try{
        System.out.println("c");
        Suggestion suggestion = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        instance.saveSuggestion(suggestion);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e{
        fail("The test case is a prototype.");}
    }

    /**
     * Test of addSuggestion method, of class Dao.
     */
    @Test
    public void testAddSuggestion() throws Exception {
try{
        System.out.println("addSuggestion");
        Suggestion suggestion = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        instance.addSuggestion(suggestion);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getAllSuggestions method, of class Dao.
     */
    @Test
    public void testGetAllSuggestions() throws Exception {
try{
        System.out.println("getAllSuggestions");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Collection expResult = null;
        Collection result = instance.getAllSuggestions();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getSuggestionsByStatus method, of class Dao.
     */
    @Test
    public void testGetSuggestionsByStatus() throws Exception {try{
        System.out.println("getSuggestionsByStatus");
        Status status = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Collection expResult = null;
        Collection result = instance.getSuggestionsByStatus(status);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getSuggestionsByDirectionStatus method, of class Dao.
     */
    @Test
    public void testGetSuggestionsByDirectionStatus() throws Exception {try{
        System.out.println("getSuggestionsByDirectionStatus");
        Status status = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Collection expResult = null;
        Collection result = instance.getSuggestionsByDirectionStatus(status);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch{Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getSuggestionsByInitiator method, of class Dao.
     */
    @Test
    public void testGetSuggestionsByInitiator() throws Exception {try{
        System.out.println("getSuggestionsByInitiator");
        String login = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Collection expResult = null;
        Collection result = instance.getSuggestionsByInitiator(login);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getSuggestionsByDepartment method, of class Dao.
     */
    @Test
    public void testGetSuggestionsByDepartment() throws Exception {try{
        System.out.println("getSuggestionsByDepartment");
        Department department = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Collection expResult = null;
        Collection result = instance.getSuggestionsByDepartment(department);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of saveDepartment method, of class Dao.
     */
    @Test
    public void testSaveDepartment() throws Exception {
try{
        System.out.println("saveDepartment");
        Department department = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        instance.saveDepartment(department);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getAllDepartments method, of class Dao.
     */
    @Test
    public void testGetAllDepartments() throws Exception {
try{
        System.out.println("getAllDepartments");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Collection expResult = null;
        Collection result = instance.getAllDepartments();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getDepartmentByNumber method, of class Dao.
     */
    @Test
    public void testGetDepartmentByNumber() throws Exception {try{
        System.out.println("getDepartmentByNumber");
        int number = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        Department expResult = null;
        Department result = instance.getDepartmentByNumber(number);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of saveUser method, of class Dao.
     */
    @Test
    public void testSaveUser() throws Exception {
try{
        System.out.println("saveUser");
        User user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        instance.saveUser(user);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of addUser method, of class Dao.
     */
    @Test
    public void testAddUser() throws Exception {
try{
        System.out.println("addUser");
        User user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        instance.addUser(user);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }

    /**
     * Test of getUserByLogin method, of class Dao.
     */
    @Test
    public void testGetUserByLogin() throws Exception {
try{
        System.out.println("getUserByLogin");
        String login = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        DAORemote instance = (DAORemote)container.getContext().lookup("java:global/classes/Dao");
        User expResult = null;
        User result = instance.getUserByLogin(login);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
}catch(Exception e){
        fail("The test case is a prototype.");}
    }



}
