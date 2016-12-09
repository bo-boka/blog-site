package com.tsguild.blogcapstone.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tsguild.blogcapstone.dao.BlogDao;
import com.tsguild.blogcapstone.dto.Blog;
import com.tsguild.blogcapstone.dto.Blog;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class BlogDaoImplTest {

    private BlogDao testDao;

    public BlogDaoImplTest() {

    }

    @Before
    public void setUp() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = factory.getBean("blogJdbcDao", BlogDao.class);
        JdbcTemplate cleaner = factory.getBean("jdbcTemplateBean", JdbcTemplate.class);
        cleaner.execute("DELETE FROM blogs WHERE 1=1");
        cleaner.execute("DELETE FROM tags WHERE 1=1");
        cleaner.execute("DELETE FROM blogs_tags WHERE 1=1");
        cleaner.execute("DELETE FROM categories WHERE 1=1");
    }

    ArrayList<String> tags = new ArrayList<>();

    Blog[] blogsForTesting = {
        new Blog(1, "Something", "Blah blah blah this is content", "user", "2016-12-01", "Blah", new ArrayList<>(), false),
        new Blog(2, "Something Else", "Stuff n Junk", "admin", "2016-10-01", "Jimmy stuff", new ArrayList<>(), true),
        new Blog(3, "Baby Savin", "Savin tha babies", "neither", "2016-12-02", "Baby Savin", new ArrayList<>(), false),
        new Blog(4, "Laser Tornado", "Holy crap no way", "admin", "2016-10-02", "Weather", new ArrayList<>(), false),
        new Blog(5, "Skrimp", "Skriiiiiimp", "user", "2014-07-04", "Shrimp", new ArrayList<>(), false),
        new Blog(6, "Whatevs", "glorious mustache", "admin", "2016-12-01", "Mustache", new ArrayList<>(), true),
        new Blog(7, "Words", "Yes, these are words.", "admin", "2016-09-11", "NineEleven", new ArrayList<>(), false),
        new Blog(8, "Star Wars", "Obi-Wan-Kenobi is a badass.", "user", "1977-12-01", "Star Wars", new ArrayList<>(), true),
        new Blog(9, "Junk n Stuff", "Stuff mostly... mostly.", "both", "2016-12-22", "Stuff", new ArrayList<>(), false),
        new Blog(10, "Stuff Shade Says", "Truth", "admin", "2016-12-01", "Blah", new ArrayList<>(), false),
        new Blog(11, "Bookoo", "Tearin up the streets", "user", "2016-12-09", "Bookoo", new ArrayList<>(), true),
        new Blog(12, "Christmas Party!", "Merry Chrimbus ervrybudy!", "admin", "2016-12-25", "Christmas", new ArrayList<>(), true),
        new Blog(13, "Tripp's Tips", "Misquote me.", "neither", "2016-09-28", "Tripp", new ArrayList<>(), true),
        new Blog(14, "Programming by Doing", "Is the bane of my existance.", "admin", "2015-12-01", "PBD", new ArrayList<>(), false),
        new Blog(15, "Mustachio", "Hero of facial hair.", "admin", "2016-12-10", "Mustache", new ArrayList<>(), true),
        new Blog(16, "Chri-yuss", "Pork Smorkin", "user", "2016-11-11", "Blah", new ArrayList<>(), false)
    };

    Blog[] duplicateBlogs = {
        new Blog(1, "Something", "Blah blah blah this is content", "user", "2016-12-01", "Blah", new ArrayList<>(), false),
        new Blog(2, "Something Else", "Stuff n Junk", "admin", "2016-10-01", "Jimmy stuff", new ArrayList<>(), true),
        new Blog(3, "Baby Savin", "Savin tha babies", "neither", "2016-12-02", "Baby Savin", new ArrayList<>(), false),
        new Blog(4, "Laser Tornado", "Holy crap no way", "admin", "2016-10-02", "Weather", new ArrayList<>(), false),
        new Blog(5, "Skrimp", "Skriiiiiimp", "user", "2014-07-04", "Shrimp", new ArrayList<>(), false),
        new Blog(6, "Whatevs", "glorious mustache", "admin", "2016-12-01", "Mustache", new ArrayList<>(), true),
        new Blog(7, "Words", "Yes, these are words.", "admin", "2016-09-11", "NineEleven", new ArrayList<>(), false),
        new Blog(8, "Star Wars", "Obi-Wan-Kenobi is a badass.", "user", "1977-12-01", "Star Wars", new ArrayList<>(), true),
        new Blog(9, "Junk n Stuff", "Stuff mostly... mostly.", "both", "2016-12-22", "Stuff", new ArrayList<>(), false),
        new Blog(10, "Stuff Shade Says", "Truth", "admin", "2016-12-01", "Blah", new ArrayList<>(), false),
        new Blog(11, "Bookoo", "Tearin up the streets", "user", "2016-12-09", "Bookoo", new ArrayList<>(), true),
        new Blog(12, "Christmas Party!", "Merry Chrimbus ervrybudy!", "admin", "2016-12-25", "Christmas", new ArrayList<>(), true),
        new Blog(13, "Tripp's Tips", "Misquote me.", "neither", "2016-09-28", "Tripp", new ArrayList<>(), true),
        new Blog(14, "Programming by Doing", "Is the bane of my existance.", "admin", "2015-12-01", "PBD", new ArrayList<>(), false),
        new Blog(15, "Mustachio", "Hero of facial hair.", "admin", "2016-12-10", "Mustache", new ArrayList<>(), true),
        new Blog(16, "Chri-yuss", "Pork Smorkin", "user", "2016-11-11", "Blah", new ArrayList<>(), false)
    };

    Blog[] similarBlogs = {
        new Blog(1, "Something", "Blah blah  this is content", "admin", "2016-12-01", "Blah", new ArrayList<>(), false),
        new Blog(2, "Something Else", "Stuff, Junk", "user", "2016-10-01", "Jimmy stuff", new ArrayList<>(), true),
        new Blog(3, "Baby Savin'", "Savin tha babies", "admin", "2017-01-01", "Baby Savin", new ArrayList<>(), false),
        new Blog(4, "Lazer Tornado", "Holy crap no way", "admin", "2016-10-02", "Weather", new ArrayList<>(), false),
        new Blog(5, "Skrimp", "Skriiiiiimp", "both", "2014-07-04", "Skrimp", new ArrayList<>(), false),
        new Blog(6, "Whatevs", "Grorious mustache", "admin", "2016-12-01", "Mustache", new ArrayList<>(), true),
        new Blog(7, "Words, mostly.", "Yes, these are words.", "user", "2016-09-11", "NineEleven", new ArrayList<>(), false),
        new Blog(8, "Star Wars", "Obi-Wan-Kenobi is a Jedi.", "neither", "1977-12-01", "Star Wars", new ArrayList<>(), true),
        new Blog(9, "Junk, Stuff", "Stuff mostly... mostly.", "admin", "2016-12-22", "Stuff", new ArrayList<>(), false),
        new Blog(10, "Stuff Shade Says", "Truth", "user", "2016-12-01", "Meh", new ArrayList<>(), false),
        new Blog(11, "Bookoo", "Tearin up the streets", "user", "2016-12-09", "Bookoo", new ArrayList<>(), true),
        new Blog(12, "Christmas Party!", "Merry Chrimbus everybody!", "admin", "2016-12-25", "Christmas", new ArrayList<>(), true),
        new Blog(13, "Tri-yupp's Tips", "Misquote me.", "neither", "2016-09-28", "Tripp", new ArrayList<>(), true),
        new Blog(14, "Programming by Doing", "Is the bane of my existance.", "user", "2015-12-01", "truth", new ArrayList<>(), false),
        new Blog(15, "Mustachio", "Hero of facial hair.", "admin", "2016-12-10", "Face Forest", new ArrayList<>(), true),
        new Blog(16, "Chri-yuss", "Pork Sporkin", "neither", "2016-11-11", "Blah", new ArrayList<>(), false)
    };

    @Test
    public void addOneToEmptyDaoTest() {
        // Step1: Make a Blog
        Blog testBlog = new Blog(1, "TestTestTest", "Test Test Test content", "admin", "2016-12-21", "TEST", tags, false);
        testBlog.getTags().add("test");

        // Step2: Add blog to dao
        testDao.createBlog(testBlog);

        // Step3: get blog out of dao by id
        Blog testBlogAgain = testDao.getBlogById(testBlog.getId());

        Assert.assertEquals(testBlog.getId(), testBlogAgain.getId());
        Assert.assertEquals(testBlog.getTitle(), testBlogAgain.getTitle());
        Assert.assertEquals(testBlog.getContent(), testBlogAgain.getContent());
        Assert.assertEquals(testBlog.getAuthor(), testBlogAgain.getAuthor());
        Assert.assertEquals(testBlog.getDate(), testBlogAgain.getDate());
        Assert.assertEquals(testBlog.getCategory(), testBlogAgain.getCategory());
        Assert.assertEquals(testBlog.getTags(), testBlogAgain.getTags());

        junit.framework.Assert.assertEquals("Blog stored, vs. blog retrieved does not match",
                testBlog, testBlogAgain);
    }

    @Test
    public void testAgainstEmptyDAO() {

        junit.framework.Assert.assertNull("Asking for a non existant blog should return null.", testDao.getBlogById(945));
        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' is nonzero with empty DAO.", 0, testDao.getAllBlogs().size());
    }

    @Test
    public void testAddOneBlog() {
        Blog testBlog = blogsForTesting[0];
        Blog testBlogAgain = testDao.createBlog(testBlog);

        Assert.assertEquals(testBlog.getId(), testBlogAgain.getId());
        Assert.assertEquals(testBlog.getTitle(), testBlogAgain.getTitle());
        Assert.assertEquals(testBlog.getContent(), testBlogAgain.getContent());
        Assert.assertEquals(testBlog.getAuthor(), testBlogAgain.getAuthor());
        Assert.assertEquals(testBlog.getDate(), testBlogAgain.getDate());
        Assert.assertEquals(testBlog.getCategory(), testBlogAgain.getCategory());
        Assert.assertEquals(testBlog.getTags(), testBlogAgain.getTags());
        Assert.assertEquals(testBlog.isPublished(), testBlogAgain.isPublished());
        junit.framework.Assert.assertEquals("Returned blog does not match expected.", testBlog, testBlogAgain);
        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' does not match after adding one blog.", 1, testDao.getAllBlogs().size());
        junit.framework.Assert.assertTrue("Returned blog in getAllBlogs does not match expected.", testDao.getAllBlogs().contains(testBlog));

    }

    @Test
    public void testUpdateBlog() {
        testDao.createBlog(blogsForTesting[0]);
        similarBlogs[0].setId(blogsForTesting[0].getId());
        testDao.updateBlog(similarBlogs[0]);

        Assert.assertEquals(similarBlogs[0].getTags(), testDao.getBlogById(similarBlogs[0].getId()).getTags());
        Assert.assertEquals(similarBlogs[0].getTitle(), testDao.getBlogById(similarBlogs[0].getId()).getTitle());
        Assert.assertEquals(similarBlogs[0].getId(), testDao.getBlogById(similarBlogs[0].getId()).getId());
        Assert.assertEquals(similarBlogs[0].getContent(), testDao.getBlogById(similarBlogs[0].getId()).getContent());
        Assert.assertEquals(similarBlogs[0].getAuthor(), testDao.getBlogById(similarBlogs[0].getId()).getAuthor());
        Assert.assertEquals(similarBlogs[0].getDate(), testDao.getBlogById(similarBlogs[0].getId()).getDate());
        junit.framework.Assert.assertEquals("Updated blog get does not match expected.", similarBlogs[0], testDao.getBlogById(similarBlogs[0].getId()));
        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' does not match after replacing one blog.", 1, testDao.getAllBlogs().size());
        junit.framework.Assert.assertTrue("Returned blog in getAllBlogs does not match expected.", testDao.getAllBlogs().contains(similarBlogs[0]));
    }

    @Test
    public void testAddMultipleBlogs() {
        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' does not match after adding several blogs.",
                blogsForTesting.length, testDao.getAllBlogs().size());
//
        for (int i = 0; i < blogsForTesting.length; i++) {
            Assert.assertEquals(blogsForTesting[i].getTags(), testDao.getBlogById(blogsForTesting[i].getId()).getTags());
            Assert.assertEquals(blogsForTesting[i].getTitle(), testDao.getBlogById(blogsForTesting[i].getId()).getTitle());
            Assert.assertEquals(blogsForTesting[i].getId(), testDao.getBlogById(blogsForTesting[i].getId()).getId());
            Assert.assertEquals(blogsForTesting[i].getContent(), testDao.getBlogById(blogsForTesting[i].getId()).getContent());
            Assert.assertEquals(blogsForTesting[i].getAuthor(), testDao.getBlogById(blogsForTesting[i].getId()).getAuthor());
            Assert.assertEquals(blogsForTesting[i].getDate(), testDao.getBlogById(blogsForTesting[i].getId()).getDate());
            Assert.assertEquals(blogsForTesting[i].getCategory(), testDao.getBlogById(blogsForTesting[i].getId()).getCategory());
            junit.framework.Assert.assertEquals("Returned blog does not match expected.", blogsForTesting[i], testDao.getBlogById(blogsForTesting[i].getId()));
            junit.framework.Assert.assertTrue("Returned blog in getAllBlogs does not match expected.", testDao.getAllBlogs().contains(blogsForTesting[i]));
        }

    }

    @Test
    public void testUpdateMultipleBlogs() {
        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }

        for (int i = 0; i < blogsForTesting.length; i++) {
            similarBlogs[i].setId(blogsForTesting[i].getId());
            testDao.updateBlog(similarBlogs[i]);
        }

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' does not match after replacing several blogs.",
                blogsForTesting.length, testDao.getAllBlogs().size());

        for (int i = 0; i < similarBlogs.length; i++) {
            Assert.assertEquals(similarBlogs[i].getTags(), testDao.getBlogById(similarBlogs[i].getId()).getTags());
            Assert.assertEquals(similarBlogs[i].getTitle(), testDao.getBlogById(similarBlogs[i].getId()).getTitle());
            Assert.assertEquals(similarBlogs[i].getId(), testDao.getBlogById(similarBlogs[i].getId()).getId());
            Assert.assertEquals(similarBlogs[i].getContent(), testDao.getBlogById(similarBlogs[i].getId()).getContent());
            Assert.assertEquals(similarBlogs[i].getAuthor(), testDao.getBlogById(similarBlogs[i].getId()).getAuthor());
            Assert.assertEquals(similarBlogs[i].getDate(), testDao.getBlogById(similarBlogs[i].getId()).getDate());
            Assert.assertEquals(similarBlogs[i].getCategory(), testDao.getBlogById(similarBlogs[i].getId()).getCategory());
            junit.framework.Assert.assertEquals("Get blog does not match expected return on update.", similarBlogs[i], testDao.getBlogById(similarBlogs[i].getId()));
            junit.framework.Assert.assertTrue("Returned blog in getAllBlogs does not match expected.", testDao.getAllBlogs().contains(similarBlogs[i]));
        }

    }

    @Test
    public void testAddSimilarBlogs() {
        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }

        for (Blog blog : similarBlogs) {
            testDao.createBlog(blog);
        }

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count does not match after adding several similar blogs.",
                blogsForTesting.length + similarBlogs.length, testDao.getAllBlogs().size());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' does not match after adding several similar blogs.",
                blogsForTesting.length + similarBlogs.length, testDao.getAllBlogs().size());

        for (int i = 0; i < similarBlogs.length; i++) {
            junit.framework.Assert.assertEquals("Get blog does not match expected return on addition of similar blog.", similarBlogs[i],
                    testDao.getBlogById(similarBlogs[i].getId()));
            junit.framework.Assert.assertTrue("Returned blog in getAllBlogs does not match expected.", testDao.getAllBlogs().contains(similarBlogs[i]));
        }

        for (int i = 0; i < blogsForTesting.length; i++) {
            junit.framework.Assert.assertEquals("Get blog does not match expected return on addition of similar blog.", blogsForTesting[i],
                    testDao.getBlogById(blogsForTesting[i].getId()));
            junit.framework.Assert.assertTrue("Returned blog in getAllBlogs does not match expected.", testDao.getAllBlogs().contains(blogsForTesting[i]));
        }

    }

    @Test
    public void testAddAndRemoveOneBlog() {
        testDao.createBlog(blogsForTesting[0]);
        testDao.deleteBlog(blogsForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertNull("Get blog should return null after being removed.", testDao.getBlogById(blogsForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' should be zero when adding/removing a single blog.", 0, testDao.getAllBlogs().size());
    }

    @Test
    public void testAddAndRemoveBlogTwice() {
        testDao.createBlog(blogsForTesting[0]);
        testDao.deleteBlog(blogsForTesting[0].getId());
        testDao.deleteBlog(blogsForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertNull("Blog should return null after being removed.", testDao.getBlogById(blogsForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' should be zero when adding/removing a single blog twice.", 0, testDao.getAllBlogs().size());
    }

    @Test
    public void testAddAndRemoveMultipleBlogs() {

        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }

        int blogsAdded = blogsForTesting.length;
        for (int i = 0; i < blogsForTesting.length; i += 2) {
            testDao.deleteBlog(blogsForTesting[i].getId());
            blogsAdded--;
        }

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' does not match after adding & removing several blogs.",
                blogsAdded, testDao.getAllBlogs().size());

        for (int i = 0; i < blogsForTesting.length; i++) {
            if (i % 2 == 1) {
                junit.framework.Assert.assertEquals("Returned blog does not match expected.", blogsForTesting[i], testDao.getBlogById(blogsForTesting[i].getId()));
            } else {
                junit.framework.Assert.assertNull("Blog should be removed and return null.", testDao.getBlogById(blogsForTesting[i].getId()));
            }
        }

    }

    @Test
    public void testAddAndRemoveBlogsMultipleTimes() {

        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }

        for (Blog blog : blogsForTesting) {
            testDao.deleteBlog(blog.getId());
        }

        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' should be zero when adding/removing a all blogs.",
                blogsForTesting.length, testDao.getAllBlogs().size());

        for (int i = 0; i < blogsForTesting.length; i++) {
            Blog blog = blogsForTesting[i];
            junit.framework.Assert.assertEquals("Blog should return after being re-added.", blog, testDao.getBlogById(blog.getId()));
            testDao.deleteBlog(blog.getId());
            junit.framework.Assert.assertNull("Blog should return null after being removed.", testDao.getBlogById(blog.getId()));
        }

        junit.framework.Assert.assertEquals("Expected blog count of 'all blogs' should be zero when adding/removing a all blogs.", 0, testDao.getAllBlogs().size());

    }

    @Test
    public void testBlogCountOnAddition() {

        // Add all blogs and check that count increments appropriately
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.createBlog(blogsForTesting[i]);
            junit.framework.Assert.assertEquals("Expected " + (i + 1) + " blogs after adding blogs.",
                    i + 1, testDao.getAllBlogs().size());
        }

    }

    @Test
    public void testBlogCountOnUpdate() {

        // Add all blogs and check that count increments appropriately
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.createBlog(blogsForTesting[i]);
            similarBlogs[i].setId(blogsForTesting[i].getId());
            testDao.updateBlog(similarBlogs[i]);
            junit.framework.Assert.assertEquals("Expected " + (i + 1) + " blogs after updating blogs.",
                    i + 1, testDao.getAllBlogs().size());
        }

    }

    @Test
    public void testBlogCountAfterRemoval() {

        // Add all blogs
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.createBlog(blogsForTesting[i]);
        }

        // Remove blogs one by one and test that count decrements properly
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.deleteBlog(blogsForTesting[i].getId());
            junit.framework.Assert.assertEquals("Expected " + (blogsForTesting.length - i - 1) + " blogs after removing blogs.",
                    blogsForTesting.length - i - 1, testDao.getAllBlogs().size());
        }
    }

    @Test
    public void testBlogsAfterRemovalOfNonExistent() {

        // Add all blogs
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.createBlog(blogsForTesting[i]);
        }

        testDao.deleteBlog(100);
        junit.framework.Assert.assertEquals("Expected " + blogsForTesting.length + " blogs after removing blogs.",
                blogsForTesting.length, testDao.getAllBlogs().size());

    }

    @Test
    public void testBlogCountAfterTwiceRemoved() {

        // Add all blogs
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.createBlog(blogsForTesting[i]);
        }

        // Remove blogs one by one and test that count decrements properly
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.deleteBlog(blogsForTesting[i].getId());
        }

        junit.framework.Assert.assertEquals("Expected " + 0 + " blogs after removing blogs.",
                0, testDao.getAllBlogs().size());

        // Remove blogs one by one and test that count decrements properly
        for (int i = 0; i < blogsForTesting.length; i++) {
            testDao.deleteBlog(blogsForTesting[i].getId());
        }

        junit.framework.Assert.assertEquals("Expected " + 0 + " blogs after attempting to re-remove blogs.",
                0, testDao.getAllBlogs().size());
    }

    @Test
    public void testSearchBlogsByTitle() {
        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }
        for (Blog blog : similarBlogs) {
            testDao.createBlog(blog);
        }

        Assert.assertEquals(blogsForTesting[0].getTags(), testDao.getBlogById(blogsForTesting[0].getId()).getTags());
        Assert.assertEquals(blogsForTesting[0].getTitle(), testDao.getBlogById(blogsForTesting[0].getId()).getTitle());
        Assert.assertEquals(blogsForTesting[0].getId(), testDao.getBlogById(blogsForTesting[0].getId()).getId());
        Assert.assertEquals(blogsForTesting[0].getContent(), testDao.getBlogById(blogsForTesting[0].getId()).getContent());
        Assert.assertEquals(blogsForTesting[0].getAuthor(), testDao.getBlogById(blogsForTesting[0].getId()).getAuthor());
        Assert.assertEquals(blogsForTesting[0].getDate(), testDao.getBlogById(blogsForTesting[0].getId()).getDate());
        Assert.assertEquals(blogsForTesting[0].getCategory(), testDao.getBlogById(blogsForTesting[0].getId()).getCategory());
//
        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        String testSearch = blogsForTesting[0].getTitle();
        List<Blog> testSearchResults = testDao.searchBlogsByTitle(testSearch);
//        List<Blog> expectedResults = 
        
        Assert.assertEquals(2, testSearchResults.size());

        Assert.assertTrue(testSearchResults.contains(blogsForTesting[1]));
        
        Assert.assertTrue(testSearchResults.contains(similarBlogs[1]));
        
    }
    
    @Test
    public void testSearchBlogsByCategory() {
        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }
        for (Blog blog : similarBlogs) {
            testDao.createBlog(blog);
        }

        Assert.assertEquals(blogsForTesting[0].getTags(), testDao.getBlogById(blogsForTesting[0].getId()).getTags());
        Assert.assertEquals(blogsForTesting[0].getTitle(), testDao.getBlogById(blogsForTesting[0].getId()).getTitle());
        Assert.assertEquals(blogsForTesting[0].getId(), testDao.getBlogById(blogsForTesting[0].getId()).getId());
        Assert.assertEquals(blogsForTesting[0].getContent(), testDao.getBlogById(blogsForTesting[0].getId()).getContent());
        Assert.assertEquals(blogsForTesting[0].getAuthor(), testDao.getBlogById(blogsForTesting[0].getId()).getAuthor());
        Assert.assertEquals(blogsForTesting[0].getDate(), testDao.getBlogById(blogsForTesting[0].getId()).getDate());
        Assert.assertEquals(blogsForTesting[0].getCategory(), testDao.getBlogById(blogsForTesting[0].getId()).getCategory());

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        String testSearch = blogsForTesting[0].getCategory();
        
        List<Blog> testSearchResults = testDao.searchBlogsByCategory(testSearch);
        
        String testSearchPublished = blogsForTesting[5].getCategory();
        List<Blog> testSearchResultsPub = testDao.searchBlogsByCategory(testSearchPublished);
        
        Assert.assertEquals(0, testSearchResults.size());
        
        Assert.assertEquals(3, testSearchResultsPub.size());
        
        Assert.assertTrue("if true, results contain category", testSearchResultsPub.contains(blogsForTesting[14]));
        
        
    }
    
    @Test
    public void testSearchBlogsByAuthor() {
        for (Blog blog : blogsForTesting) {
            testDao.createBlog(blog);
        }
        for (Blog blog : similarBlogs) {
            testDao.createBlog(blog);
        }

        Assert.assertEquals(blogsForTesting[0].getTags(), testDao.getBlogById(blogsForTesting[0].getId()).getTags());
        Assert.assertEquals(blogsForTesting[0].getTitle(), testDao.getBlogById(blogsForTesting[0].getId()).getTitle());
        Assert.assertEquals(blogsForTesting[0].getId(), testDao.getBlogById(blogsForTesting[0].getId()).getId());
        Assert.assertEquals(blogsForTesting[0].getContent(), testDao.getBlogById(blogsForTesting[0].getId()).getContent());
        Assert.assertEquals(blogsForTesting[0].getAuthor(), testDao.getBlogById(blogsForTesting[0].getId()).getAuthor());
        Assert.assertEquals(blogsForTesting[0].getDate(), testDao.getBlogById(blogsForTesting[0].getId()).getDate());
        Assert.assertEquals(blogsForTesting[0].getCategory(), testDao.getBlogById(blogsForTesting[0].getId()).getCategory());

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        String testSearch = blogsForTesting[0].getAuthor();
        
        List<Blog> testSearchResults = testDao.searchBlogsByAuthor(testSearch);
        
       
        Assert.assertEquals(4, testSearchResults.size());
        
        Assert.assertTrue(testSearchResults.contains(similarBlogs[1]));
        
    }
    
    @Test
    public void testSearchBlogsByTags() {
        
        for (Blog blog : blogsForTesting) {
            
            blog.getTags().add("#ouch");
            blog.getTags().add("#winning");
            blog.getTags().add("#dood");
            testDao.createBlog(blog);
        }
        for (Blog blog : similarBlogs) {
            blog.getTags().add("#dank");
            blog.getTags().add("#royal");
            blog.getTags().add("#ouch");
            testDao.createBlog(blog);
        }

        Assert.assertEquals(blogsForTesting[0].getTags(), testDao.getBlogById(blogsForTesting[0].getId()).getTags());
        Assert.assertEquals(blogsForTesting[0].getTitle(), testDao.getBlogById(blogsForTesting[0].getId()).getTitle());
        Assert.assertEquals(blogsForTesting[0].getId(), testDao.getBlogById(blogsForTesting[0].getId()).getId());
        Assert.assertEquals(blogsForTesting[0].getContent(), testDao.getBlogById(blogsForTesting[0].getId()).getContent());
        Assert.assertEquals(blogsForTesting[0].getAuthor(), testDao.getBlogById(blogsForTesting[0].getId()).getAuthor());
        Assert.assertEquals(blogsForTesting[0].getDate(), testDao.getBlogById(blogsForTesting[0].getId()).getDate());
        Assert.assertEquals(blogsForTesting[0].getCategory(), testDao.getBlogById(blogsForTesting[0].getId()).getCategory());

        junit.framework.Assert.assertNotNull("List of all blogs should not be null.", testDao.getAllBlogs());
        String testSearch = blogsForTesting[0].getTags().get(0);
        
        List<Blog> testSearchResults = testDao.searchBlogsByTags(testSearch);
        
       
        Assert.assertEquals(14, testSearchResults.size());
        
//        Assert.assertTrue(testSearchResults.contains(similarBlogs[1]));
        
    }
}
