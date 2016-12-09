/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.controller;

import com.tsguild.blogcapstone.dao.BlogDao;
import com.tsguild.blogcapstone.dao.PageDao;
import com.tsguild.blogcapstone.dto.Blog;
import com.tsguild.blogcapstone.dto.Page;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author $arah
 */
@Controller
public class BlogController {

    private BlogDao dao;
    private PageDao pageDao;

    @Inject
    public BlogController(BlogDao dao, PageDao pageDao) {
        this.dao = dao;
        this.pageDao = pageDao;
    }

    //all page displays
    @RequestMapping(value = {"/","home"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<Page> pagesModel = pageDao.getAllPages();
        model.addAttribute("pages", pagesModel);
        return "home";
    }
    
    //login page
    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String displayLoginPage(){
        return "loginForm";
    }
    
    //admin page
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayAdminPage(Model model){
        List<Page> pagesModel = pageDao.getAllPages();
        List<String> categories = dao.getAllBlogCategories();
        model.addAttribute("pages", pagesModel);
        model.addAttribute("categories", categories);
        return "admin";
    }
    
    //display a cause page
    @RequestMapping(value = "/causes/{id}", method = RequestMethod.GET)
    public String displayCausesPage(@PathVariable int id, Model model){
        
        Page aPage = pageDao.getPageById(id);
        model.addAttribute("cause", aPage);
        
        List<Page> pagesModel = pageDao.getAllPages();
        model.addAttribute("pages", pagesModel);
        
        return "page";
    }
    
    //single blog page
    @RequestMapping(value = "/singleBlog/{id}", method = RequestMethod.GET)
    public String displaySingleBlogPage(@PathVariable int id, Model model){
        
        Blog aBlog = dao.getBlogById(id);
        model.addAttribute("oneBlog", aBlog);
        
        List<Page> pagesModel = pageDao.getAllPages();
        model.addAttribute("pages", pagesModel);
        
        return "single";
    }
    
    // about page
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String displayAboutPage(Model model){
        List<Page> pagesModel = pageDao.getAllPages();
        model.addAttribute("pages", pagesModel);
        return "about";
    }
    
    // search page display
    @RequestMapping(value = "/blogListDisplay", method = RequestMethod.GET)
    public String displayBlogListCategory(Model model){
        List<Page> pagesModel = pageDao.getAllPages();
        model.addAttribute("pages", pagesModel);
        return "blogListDisplay";
    }
    
    //blog crud
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public Blog createBlog(@Valid @RequestBody Blog blog) {
        dao.createBlog(blog);
        return blog;
    }
    
    //get blog by id
    @ResponseBody
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public Blog getBlogById(@PathVariable int id){
        return dao.getBlogById(id);
    }
    
    //get all blogs
    @ResponseBody
    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public List<Blog> getAllBlogs(){
        return dao.getAllBlogs();
    }
    
    //get all published blogs
    @ResponseBody
    @RequestMapping(value = "/blogsPublished", method = RequestMethod.GET)
    public List<Blog> getAllPublishedBlogs(){
        return dao.getAllPublishedBlogs();
    }
    
    //update blog
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    public void updateBlog(@Valid @PathVariable int id, @Valid @RequestBody Blog blog){
//        blog.setId(id);
        dao.updateBlog(blog);
    }
    
    //delete a blog
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    public void deleteBlog(@PathVariable int id){
        dao.deleteBlog(id);
    }
    
    //get all categories
    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<String> getAllCategories(){
     return dao.getAllBlogCategories();
    }
    
}
