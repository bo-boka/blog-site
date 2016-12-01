/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.tsguild.blogcapstone.dao.BlogDao;
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
public class DaoTests {
    
    private BlogDao testDao;
    
    public DaoTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = factory.getBean(BlogDao.class);
        JdbcTemplate cleaner = factory.getBean("jdbcTemplateBean", JdbcTemplate.class);
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
     public void getOne() {
         Blog x = testDao.getBlogById(1);
         Assert.assertEquals(x, testDao.getBlogById(1));
     }
     
     @Test
     public void getNull(){
         Blog x = testDao.getBlogById(-5);
         Assert.assertNull(x);
     }
     
     @Test
     public void addSameDifferentId(){
         Blog x = testDao.getBlogById(1);
         Blog y = x;
         y.setId(-5);
         testDao.createBlog(y);
         Assert.assertEquals(y, testDao.getBlogById(-5));
     }
     
     @Test
     public void debugXProperties(){
         Blog x = testDao.getBlogById(1);
         x.getImage();
         Assert.assertTrue(true);
     }
     
     @Test
     public void addOne(){
         Blog y = testDao.getBlogById(1);
         ArrayList testTags = new ArrayList<>();
         testTags.add("tag1");
         testTags.add("testTag2");
         testTags.add("testTag3");
         Blog x = new Blog("Title", "asdfasdfasdfasdf", "Author", "2014-01-01", "Emergencies", null, testTags, false);
     }
     
     @Test
     public void getAll() {
         List<Blog> x = testDao.getAllBlogs();
         Assert.assertNotNull(x);
     }
}
