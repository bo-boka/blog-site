/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dao;

import com.tsguild.blogcapstone.dto.Blog;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class BlogDaoImpl implements BlogDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ADD A NEW BLOG
    private static final String SQL_ADD_BLOG
            = "INSERT INTO blogs (title, content, author_id, category_id, image, published) "
            + " VALUE(?, ?, ?, ?, ?, ?)";
    // Add an author when ready
//    private static final String SQL_ADD_AUTHOR = "INSERT INTO authors (username, password, enabled) "
//            + "VALUE (?, ?, ?)";
//                       

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Blog createBlog(Blog blog) {
        
        jdbcTemplate.update(SQL_ADD_BLOG,
                blog.getTitle(),
                blog.getContent(),
                1,
                1,
                blog.getImage(),
                blog.isPublished()
        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        blog.setId(id);
        return blog;
    }

    //GET A BLOG BY ID
//    private static final String SQL_SELECT_BY_ENTRY
//            = " SELECT * FROM blogs WHERE id = ? ";
    private static final String SQL_SELECT_BY_ID = "SELECT blog_id, title, "
            + "content, authors.username, `date`, categories.category, image, "
            + "published FROM blogs JOIN authors ON blogs.author_id = "
            + "authors.author_id JOIN categories ON blogs.category_id = "
            + "categories.category_id WHERE blog_id = ?";

    private static final String SQL_SELECT_TAGS_BY_ID = "SELECT tag FROM blogs_tags "
            + "JOIN tags ON blogs_tags.tag_id = tags.tag_id WHERE blog_id = ?";

    @Override
    public Blog getBlogById(int id) {
        try {
            List<String> tags = new ArrayList<>();
            // This returns the results set of tags and assigns them to the tags variable
            tags = jdbcTemplate.query(SQL_SELECT_TAGS_BY_ID, (ResultSet resultSet, int i) -> resultSet.getString(1), id);
            // This returns the blog object for the id
            Blog blog = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new BlogMapper(), id);
            // This sets the tags to the blog object
            blog.setTags((ArrayList<String>) tags);
            return blog;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    //The SQL statement below is commented out because we don't have tags yet. That will be in iteration 3.
//    private static final String SQL_GET_ALL_BLOGS = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n" +
//"	JOIN authors ON blogs.author_id = authors.author_id\n" +
//"    JOIN authorities ON authors.username = authorities.username\n" +
//"    LEFT JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n" +
//"    LEFT JOIN tags ON tags.tag_id = blogs_tags.tag_id\n" +
//"    JOIN categories ON blogs.category_id = categories.category_id";
    
    private static final String SQL_GET_ALL_BLOGS = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, published FROM blogs\n" +
"	JOIN authors ON blogs.author_id = authors.author_id\n" +
"    JOIN authorities ON authors.username = authorities.username\n" +
"    JOIN categories ON blogs.category_id = categories.category_id";

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> allBlogs;
        allBlogs = jdbcTemplate.query(SQL_GET_ALL_BLOGS, new BlogMapper());
        return allBlogs;
    }

    //UPDATE BLOGS
    private static final String SQL_UPDATE_BLOG = "UPDATE blogs "
            + " SET title = ?, "
            + " content = ?, "
            + " author = ?, "
            + " date = ?, "
            + " category = ?, "
            + " image = ?, "
            + " tags = ? "
            + " published = ? "
            + " WHERE id = ?";

    @Override
    public void updateBlog(Blog blog) {
        jdbcTemplate.update(SQL_UPDATE_BLOG,
                blog.getTitle(),
                blog.getContent(),
                blog.getDate(),
                blog.getCategory(),
                blog.getImage(),
                blog.getTags());

    }

    //DELETE A BLOG
    private static final String SQL_REMOVE_THAT_BLOG = "DELETE FROM blogs WHERE id = ?";

    @Override
    public void deleteBlog(int id) {
        jdbcTemplate.update(SQL_REMOVE_THAT_BLOG, id);
    }

    //SEARCH BLOGS BY TITLE
    private static final String SQL_SEARCH_BY_TITLE
            = " SELECT * FROM blogs WHERE title = ?";

    @Override
    public List<Blog> searchBlogsByTitle(String searchTitle) {
        try {
            return jdbcTemplate.query(SQL_SEARCH_BY_TITLE, new BlogMapper(), searchTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SEARCH BLOGS BY CATEGORY
    private static final String SQL_SEARCH_BY_CATEGORY
            = "SELECT blog_id, title, "
            + "content, authors.username, `date`, categories.category, image, "
            + "published FROM blogs JOIN authors ON blogs.author_id = "
            + "authors.author_id JOIN categories ON blogs.category_id = "
            + "categories.category_id WHERE categories.category = ?";

    @Override
    public List<Blog> searchBlogsByCategory(String searchCategory) {
        try {
            return jdbcTemplate.query(SQL_SEARCH_BY_CATEGORY, new BlogMapper(), searchCategory);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    //SEARCH BLOGS BY DATE
    private static final String SQL_SEARCH_BY_DATE
            = " SELECT * FROM blogs WHERE date = ?";

    @Override
    public List<Blog> searchBlogsByDate(String searchDate) {
        try {
            return jdbcTemplate.query(SQL_SEARCH_BY_DATE, new BlogMapper(), searchDate);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SEARCH BLOGS BY TAGS
    private static final String SQL_SEARCH_BY_TAGS
            = " SELECT * FROM blogs WHERE tags = ?";

    @Override
    public List<Blog> searchBlogsByTags(int tagId) {
        try {
            return jdbcTemplate.query(SQL_SEARCH_BY_TAGS, new BlogMapper(), tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Mapper
    private static final class BlogMapper implements RowMapper<Blog> {

        @Override
        public Blog
                mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Blog mappedBlog = new Blog();
            Integer id = rs.getInt("blog_id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String author = rs.getString("username");
            String date = rs.getString("date");
            String category = rs.getString("category");
//            Blob image = rs.getBlob("image");
            Boolean published = rs.getBoolean("published");
            
            mappedBlog.setId(id);
            mappedBlog.setTitle(title);
            mappedBlog.setContent(content);
            mappedBlog.setAuthor(author);
            mappedBlog.setDate(date);
            mappedBlog.setCategory(category);
//            mappedBlog.setImage(image);
            mappedBlog.setPublished(published);
//            mappedBlog.setTags(new ArrayList<>());

            return mappedBlog;
        }
    }
    
    private static final class BlogResultExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
            
            Blog blog = new Blog();
            
            
            return blog;
        }
        
        
        
    }

}
