/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dao;

import com.tsguild.blogcapstone.dto.Page;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class PageDaoImplTest {

    PageDao testDao;

    public PageDaoImplTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = factory.getBean("pageJdbcDao", PageDao.class);
        JdbcTemplate cleaner = factory.getBean("jdbcTemplateBean", JdbcTemplate.class);
        cleaner.execute("DELETE FROM pages WHERE 1=1");
    }

    Page[] pagesForTesting = {
        new Page(1, "Something", "Blah blah blah this is content", null),
        new Page(2, "Test title", "test content", null),
        new Page(3, "something else", "anything frealz", null),
        new Page(4, "Mostly stuff", "blah blah stuff stuff", null),
        new Page(5, "Skrimp", "Skramp", null),
        new Page(6, "Jimmy says TEST", "DO IT NOW", null),
        new Page(7, "Forks: Why do ya need 'em?", "Ta eat with, ya dingus.", null),
        new Page(8, "Chris: That 'Guy'", "Gawteem", null),
        new Page(9, "Stuff Shade says", "True.", null),
        new Page(10, "Example junk", "probably junky stuff.", null),
        new Page(11, "AllCaps Team", "We da bess.", null),
        new Page(12, "TeamTeam", "The original bess team", null),
        new Page(13, "Tripp's Tips", "Me me, I'm Tripp.", null),
        new Page(14, "Schlaaang", "Manananana fananananana", null),
        new Page(15, "Trump: Presidential?", "Emigrate to Canada.", null),
        new Page(16, "Llama: The next beef?", "Could be...", null)
    };

    Page[] duplicatePages = {
        new Page(1, "Something", "Blah blah blah this is content", null),
        new Page(2, "Test title", "test content", null),
        new Page(3, "something else", "anything frealz", null),
        new Page(4, "Mostly stuff", "blah blah stuff stuff", null),
        new Page(5, "Skrimp", "Skramp", null),
        new Page(6, "Jimmy says TEST", "DO IT NOW", null),
        new Page(7, "Forks: Why do ya need 'em?", "Ta eat with, ya dingus.", null),
        new Page(8, "Chris: That 'Guy'", "Gawteem", null),
        new Page(9, "Stuff Shade says", "True.", null),
        new Page(10, "Example junk", "probably junky stuff.", null),
        new Page(11, "AllCaps Team", "We da bess.", null),
        new Page(12, "TeamTeam", "The original bess team", null),
        new Page(13, "Tripp's Tips", "Me me, I'm Tripp.", null),
        new Page(14, "Schlaaang", "Manananana fananananana", null),
        new Page(15, "Trump: Presidential?", "Emigrate to Canada.", null),
        new Page(16, "Llama: The next beef?", "Could be...", null)
    };

    Page[] similarPages = {
        new Page(1, "Some 'Ting 'Mon", "Blah blah blah this is content", null),
        new Page(2, "Test title", "test 'content'", null),
        new Page(3, "Some 'Ting else", "anything frealz", null),
        new Page(4, "Mostly stuff", "blah stuff", null),
        new Page(5, "Skrimp", "Shrimp Dangler", null),
        new Page(6, "Jimmy says", "DO IT NOW", null),
        new Page(7, "Forks: Why do ya need 'em?", "Good forking question.", null),
        new Page(8, "Chris: That 'Guy'", "Something nice.", null),
        new Page(9, "Stuff Shade says", "Just Kidding.", null),
        new Page(10, "Example Stuff", "probably junky stuff.", null),
        new Page(11, "AllCaps Team", "We da boss.", null),
        new Page(12, "TeamTeam", "The OG bess team", null),
        new Page(13, "Tripp's Tips", "Debug? Why?", null),
        new Page(14, "Schlaang", "Manananana fananananana", null),
        new Page(15, "Trump: Presidential?", "Build a wall around Wall Street.", null),
        new Page(16, "Llamas: The next beef?", "yes", null)
    };

    
    @Test
    public void addOneToEmptyDaoTest() {
        // Step1: Make a Page
        Page testPage = new Page(-1, "Something", "Blah blah blah this is content", null);

        // Step2: Add page to dao
        testDao.createPage(testPage);

        // Step3: get page out of dao by id
        Page testPageAgain = testDao.getPageById(testPage.getId());

        // Step4: Make sure the page added looks like the page retrieved
        // We can only use assertEquals here because we overrode the .equals method in Page
        Assert.assertEquals("Page stored, vs. page retrieved does not match",
                testPage, testPageAgain);
        //        Assert.assertEquals("Added/GetByID page id did not match.", testPage.getId(), testPageAgain.getId());
        //        Assert.assertEquals("Added/GetByID page name did not match 'testPage' as expected", "testPage", testPageAgain.getId());
    }

    @Test
    public void testAgainstEmptyDAO() {

        Assert.assertNull("Asking for a non existant page should return null.", testDao.getPageById(445));
        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' is nonzero with empty DAO.", 0, testDao.getAllPages().size());
    }

    @Test
    public void testAddOnePage() {
        Page pageToAdd = pagesForTesting[0];
        testDao.createPage(pageToAdd);

        Assert.assertEquals("Returned page does not match expected.", pageToAdd, testDao.getPageById(pageToAdd.getId()));
        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' does not match after adding one page.", 1, testDao.getAllPages().size());
        Assert.assertTrue("Returned page in getAllPages does not match expected.", testDao.getAllPages().contains(pageToAdd));

    }

    @Test
    public void testUpdatePage() {
        testDao.createPage(pagesForTesting[0]);
        similarPages[0].setId(pagesForTesting[0].getId());
        testDao.updatePage(similarPages[0]);

        Assert.assertEquals("Updated page get does not match expected.", similarPages[0], testDao.getPageById(similarPages[0].getId()));
        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' does not match after replacing one page.", 1, testDao.getAllPages().size());
        Assert.assertTrue("Returned page in getAllPages does not match expected.", testDao.getAllPages().contains(similarPages[0]));
    }

    @Test
    public void testAddMultiplePages() {
        for (Page page : pagesForTesting) {
            testDao.createPage(page);
        }

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' does not match after adding several pages.",
                pagesForTesting.length, testDao.getAllPages().size());

        for (int i = 0; i < pagesForTesting.length; i++) {
            Assert.assertEquals("Returned page does not match expected.", pagesForTesting[i], testDao.getPageById(pagesForTesting[i].getId()));
            Assert.assertTrue("Returned page in getAllPages does not match expected.", testDao.getAllPages().contains(pagesForTesting[i]));
        }

    }

    @Test
    public void testUpdateMultiplePages() {
        for (Page page : pagesForTesting) {
            testDao.createPage(page);
        }

        for (int i = 0; i < pagesForTesting.length; i++) {
            similarPages[i].setId(pagesForTesting[i].getId());
            testDao.updatePage(similarPages[i]);
        }

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' does not match after replacing several pages.",
                pagesForTesting.length, testDao.getAllPages().size());

        for (int i = 0; i < similarPages.length; i++) {
            Assert.assertEquals("Get page does not match expected return on update.", similarPages[i], testDao.getPageById(similarPages[i].getId()));
            Assert.assertTrue("Returned page in getAllPages does not match expected.", testDao.getAllPages().contains(similarPages[i]));
        }

    }

    @Test
    public void testAddSimilarPages() {
        for (Page page : pagesForTesting) {
            testDao.createPage(page);
        }

        for (Page page : similarPages) {
            testDao.createPage(page);
        }

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count does not match after adding several similar pages.",
                pagesForTesting.length + similarPages.length, testDao.getAllPages().size());
        Assert.assertEquals("Expected page count of 'all pages' does not match after adding several similar pages.",
                pagesForTesting.length + similarPages.length, testDao.getAllPages().size());

        for (int i = 0; i < similarPages.length; i++) {
            Assert.assertEquals("Get page does not match expected return on addition of similar page.", similarPages[i],
                    testDao.getPageById(similarPages[i].getId()));
            Assert.assertTrue("Returned page in getAllPages does not match expected.", testDao.getAllPages().contains(similarPages[i]));
        }

        for (int i = 0; i < pagesForTesting.length; i++) {
            Assert.assertEquals("Get page does not match expected return on addition of similar page.", pagesForTesting[i],
                    testDao.getPageById(pagesForTesting[i].getId()));
            Assert.assertTrue("Returned page in getAllPages does not match expected.", testDao.getAllPages().contains(pagesForTesting[i]));
        }

    }

    @Test
    public void testAddAndRemoveOnePage() {
        testDao.createPage(pagesForTesting[0]);
        testDao.deletePage(pagesForTesting[0].getId());

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertNull("Get page should return null after being removed.", testDao.getPageById(pagesForTesting[0].getId()));
        Assert.assertEquals("Expected page count of 'all pages' should be zero when adding/removing a single page.", 0, testDao.getAllPages().size());
    }

    @Test
    public void testAddAndRemovePageTwice() {
        testDao.createPage(pagesForTesting[0]);
        testDao.deletePage(pagesForTesting[0].getId());
        testDao.deletePage(pagesForTesting[0].getId());

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertNull("Page should return null after being removed.", testDao.getPageById(pagesForTesting[0].getId()));
        Assert.assertEquals("Expected page count of 'all pages' should be zero when adding/removing a single page twice.", 0, testDao.getAllPages().size());
    }

    @Test
    public void testAddAndRemoveMultiplePages() {

        for (Page page : pagesForTesting) {
            testDao.createPage(page);
        }

        int pagesAdded = pagesForTesting.length;
        for (int i = 0; i < pagesForTesting.length; i += 2) {
            testDao.deletePage(pagesForTesting[i].getId());
            pagesAdded--;
        }

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' does not match after adding & removing several pages.",
                pagesAdded, testDao.getAllPages().size());

        for (int i = 0; i < pagesForTesting.length; i++) {
            if (i % 2 == 1) {
                Assert.assertEquals("Returned page does not match expected.", pagesForTesting[i], testDao.getPageById(pagesForTesting[i].getId()));
            } else {
                Assert.assertNull("Page should be removed and return null.", testDao.getPageById(pagesForTesting[i].getId()));
            }
        }

    }

    @Test
    public void testAddAndRemovePagesMultipleTimes() {

        for (Page page : pagesForTesting) {
            testDao.createPage(page);
        }

        for (Page page : pagesForTesting) {
            testDao.deletePage(page.getId());
        }

        for (Page page : pagesForTesting) {
            testDao.createPage(page);
        }

        Assert.assertNotNull("List of all pages should not be null.", testDao.getAllPages());
        Assert.assertEquals("Expected page count of 'all pages' should be zero when adding/removing a all pages.",
                pagesForTesting.length, testDao.getAllPages().size());

        for (int i = 0; i < pagesForTesting.length; i++) {
            Page page = pagesForTesting[i];
            Assert.assertEquals("Page should return after being re-added.", page, testDao.getPageById(page.getId()));
            testDao.deletePage(page.getId());
            Assert.assertNull("Page should return null after being removed.", testDao.getPageById(page.getId()));
        }

        Assert.assertEquals("Expected page count of 'all pages' should be zero when adding/removing a all pages.", 0, testDao.getAllPages().size());

    }

    @Test
    public void testPageCountOnAddition() {

        // Add all pages and check that count increments appropriately
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.createPage(pagesForTesting[i]);
            Assert.assertEquals("Expected " + (i + 1) + " pages after adding pages.",
                    i + 1, testDao.getAllPages().size());
        }

    }

    @Test
    public void testPageCountOnUpdate() {

        // Add all pages and check that count increments appropriately
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.createPage(pagesForTesting[i]);
            similarPages[i].setId(pagesForTesting[i].getId());
            testDao.updatePage(similarPages[i]);
            Assert.assertEquals("Expected " + (i + 1) + " pages after updating pages.",
                    i + 1, testDao.getAllPages().size());
        }

    }

    @Test
    public void testPageCountAfterRemoval() {

        // Add all pages
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.createPage(pagesForTesting[i]);
        }

        // Remove pages one by one and test that count decrements properly
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.deletePage(pagesForTesting[i].getId());
            Assert.assertEquals("Expected " + (pagesForTesting.length - i - 1) + " pages after removing pages.",
                    pagesForTesting.length - i - 1, testDao.getAllPages().size());
        }
    }

    @Test
    public void testPagesAfterRemovalOfNonExistent() {

        // Add all pages
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.createPage(pagesForTesting[i]);
        }

        testDao.deletePage(100);
        Assert.assertEquals("Expected " + pagesForTesting.length + " pages after removing pages.",
                pagesForTesting.length, testDao.getAllPages().size());

    }

    @Test
    public void testPageCountAfterTwiceRemoved() {

        // Add all pages
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.createPage(pagesForTesting[i]);
        }

        // Remove pages one by one and test that count decrements properly
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.deletePage(pagesForTesting[i].getId());
        }

        Assert.assertEquals("Expected " + 0 + " pages after removing pages.",
                0, testDao.getAllPages().size());

        // Remove pages one by one and test that count decrements properly
        for (int i = 0; i < pagesForTesting.length; i++) {
            testDao.deletePage(pagesForTesting[i].getId());
        }

        Assert.assertEquals("Expected " + 0 + " pages after attempting to re-remove pages.",
                0, testDao.getAllPages().size());

    }

}
