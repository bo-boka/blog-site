/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.controller;

import com.tsguild.blogcapstone.dao.BlogDao;
import com.tsguild.blogcapstone.dto.Blog;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jimmy Cook
 */
@Controller
public class BlogController {

    private BlogDao dao;

    @Inject
    public BlogController(BlogDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayAdminPage(){
        return "admin";
    }
    
    @RequestMapping(value = "/causes", method = RequestMethod.GET)
    public String displayCausesPage(){
        return "causes";
    }
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String displayAboutPage(){
        return "about";
    }
    
    @RequestMapping(value = "/blogListDisplay", method = RequestMethod.GET)
    public String displayBlogListCategory(){
        return "blogListDisplay";
    }
    
    @ResponseBody
    @RequestMapping(value = "/blogListDisplayCategory/{category}", method = RequestMethod.GET)
    public List<Blog> displayBlogsInCategory(@PathVariable String category){
        List<Blog> result = dao.searchBlogsByCategory(category);
        return result;
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public Blog createBlog(@RequestBody Blog blog) {
        dao.createBlog(blog);
        return blog;
    }
    
    @ResponseBody
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
    public Blog getBlogById(@PathVariable int id){
        return dao.getBlogById(id);
    }
    
    @ResponseBody
    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public List<Blog> getAllBlogs(){
        return dao.getAllBlogs();
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    public void updateBlog(@PathVariable int id, @RequestBody Blog blog){
        blog.setId(id);
        dao.updateBlog(blog);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    public void deleteBlog(@PathVariable int id){
        dao.deleteBlog(id);
    }
    
}
