/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dao;

import com.tsguild.blogcapstone.dto.Page;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public interface PageDao {
    
        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        Page createPage(Page page);
        
        Page getPageById(int id);
        
        List<Page> getAllPages();
        
        //UPDATE PAGE
        void updatePage(Page page);
        
        //DELETE A PAGE
        void deletePage(int id);
}
