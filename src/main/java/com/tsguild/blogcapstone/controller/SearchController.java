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
 * @author apprentice
 */

@Controller
public class SearchController {
    
    private BlogDao dao;
    
    @Inject
    public SearchController(BlogDao dao){
        this.dao = dao;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/blogListDisplayCategory/{category}", method = RequestMethod.GET)
    public List<Blog> displayBlogsInCategory(@PathVariable String category){
        List<Blog> result = dao.searchBlogsByCategory(category);
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/blogListDisplayAuthor/{author}", method = RequestMethod.GET)
    public List<Blog> displayBlogsByAuthor(@PathVariable String author){
        List<Blog> result = dao.searchBlogsByAuthor(author);
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/blogListDisplayTitle/{title}", method = RequestMethod.GET)
    public List<Blog> displayBlogsByTitle(@PathVariable String title){
        List<Blog> result = dao.searchBlogsByTitle(title);
        return result;
    }

    

    @ResponseBody
    @RequestMapping(value = "/searchTags", method = RequestMethod.POST)
    public List<Blog> displayBlogsByTag(@RequestBody String searchInfo){
        
        return dao.searchBlogsByTags(searchInfo);
    }
}
