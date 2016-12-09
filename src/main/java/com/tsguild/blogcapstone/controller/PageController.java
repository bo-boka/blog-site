/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.controller;

import com.tsguild.blogcapstone.dao.PageDao;
import com.tsguild.blogcapstone.dto.Page;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
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
public class PageController {

    private PageDao dao;

    @Inject
    public PageController(PageDao dao) {
        this.dao = dao;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Page createPage(@Valid @RequestBody Page page) {
        dao.createPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public Page getPageById(@PathVariable int id) {
        return dao.getPageById(id);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/page/{id}", method = RequestMethod.DELETE)
    public void deletePage(@PathVariable int id) {
        dao.deletePage(id);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/page/{id}", method = RequestMethod.PUT)
    public void updatePage(@PathVariable int id,@Valid @RequestBody Page page){
        dao.updatePage(page);
    }
    
    @ResponseBody
    @RequestMapping(value = "/allpages", method = RequestMethod.GET)
    public List<Page> getAllPages(){
        return dao.getAllPages();
    }
}
