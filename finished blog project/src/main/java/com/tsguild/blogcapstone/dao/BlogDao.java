/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dao;

import com.tsguild.blogcapstone.dto.Blog;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public interface BlogDao {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Blog createBlog(Blog blog);

    //DELETE A BLOG
    void deleteBlog(int id);

    List<Blog> getAllBlogs();
    
    List<Blog> getAllPublishedBlogs();

    Blog getBlogById(int id);
    
    List<String> getAllBlogCategories();

    //SEARCH BLOGS BY CATEGORY
    List<Blog> searchBlogsByCategory(String searchCategory);
    
    //SEARCH BLOGS BY AUTHOR
    List<Blog> searchBlogsByAuthor(String searchAuthor);

    //SEARCH BLOGS BY DATE
    List<Blog> searchBlogsByDate(String searchDate);

    //SEARCH BLOGS BY TAGS
    List<Blog> searchBlogsByTags(String tagId);

    //SEARCH BLOGS BY TITLE
    List<Blog> searchBlogsByTitle(String searchTitle);

    void setJdbcTemplate(JdbcTemplate jdbcTemplate);

    void updateBlog(Blog blog);
    
}
