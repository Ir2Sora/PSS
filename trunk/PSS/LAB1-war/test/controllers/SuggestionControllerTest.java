package controllers;

import entity.Direction;
import entity.Suggestion;
import javax.inject.Inject;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SuggestionControllerTest extends TestCase {

    @Inject
    SuggestionController controller;

    public SuggestionControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws NamingException {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setSuggestion method, of class SuggestionController.
     */
    @Test
    public void testSetSuggestion() {
        System.out.println("setSuggestion");
        Suggestion suggestion = null;
        SuggestionController instance = new SuggestionController();
        instance.setSuggestion(suggestion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of submitSuggestion method, of class SuggestionController.
     */
    @Test
    public void testSubmitSuggestion() {
        System.out.println("submitSuggestion");
        SuggestionController instance = new SuggestionController();
        instance.submitSuggestion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class SuggestionController.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Suggestion sugg = null;
        SuggestionController instance = new SuggestionController();
        instance.save(sugg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writePeerReview method, of class SuggestionController.
     */
    @Test
    public void testWritePeerReview() {
        System.out.println("writePeerReview");
        Suggestion sugg = null;
        SuggestionController instance = new SuggestionController();
        String expResult = "";
        String result = instance.writePeerReview(sugg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of improve method, of class SuggestionController.
     */
    @Test
    public void testImprove() {
        System.out.println("improve");
        Suggestion sugg = null;
        SuggestionController instance = new SuggestionController();
        String expResult = "";
        String result = instance.improve(sugg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectForwritePeerReview method, of class SuggestionController.
     */
    @Test
    public void testSelectForwritePeerReview() {
        System.out.println("selectForwritePeerReview");
        SuggestionController instance = new SuggestionController();
        String expResult = "";
        String result = instance.selectForwritePeerReview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectOwnSuggestions method, of class SuggestionController.
     */
    @Test
    public void testSelectOwnSuggestions() {
        System.out.println("selectOwnSuggestions");
        SuggestionController instance = new SuggestionController();
        String expResult = "";
        String result = instance.selectOwnSuggestions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class SuggestionController.
     */
    @Test
    public void testSelect() {
        System.out.println("select");
        SuggestionController instance = new SuggestionController();
        String expResult = "";
        String result = instance.select();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectSuggestions method, of class SuggestionController.
     */
    @Test
    public void testSelectSuggestions() {
        System.out.println("selectSuggestions");
        SuggestionController instance = new SuggestionController();
        String expResult = "";
        String result = instance.selectSuggestions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
