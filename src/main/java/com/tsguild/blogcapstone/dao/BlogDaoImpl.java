/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dao;

import com.tsguild.blogcapstone.dto.Blog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
            = "INSERT INTO blogs (title, content, author_id, category_id, published, date) "
            + " VALUE(?, ?, ?, ?, ?, ?)";

    // Add blog tags
    private static final String SQL_ADD_BLOG_TAGS = "INSERT IGNORE INTO tags (tag) VALUE(?)";
    // add blog categories
    private static final String SQL_ADD_CATEGORY = "INSERT IGNORE INTO categories (category) VALUE(?)";
    // add author
    private static final String SQL_GET_AUTHOR_ID = "SELECT author_id FROM authors WHERE username = ?";
    // get category id
    private static final String SQL_GET_CATEGORY_ID = "SELECT category_id FROM categories WHERE category = ?";
    // get tag id
    private static final String SQL_GET_TAG_ID = "SELECT tag_id FROM tags WHERE tag = ?";
    // add blog_tags 
    private static final String SQL_ADD_BLOGS_TAGS_BRIDGE = "INSERT IGNORE INTO blogs_tags (blog_id, tag_id) VALUE(?,?)";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Blog createBlog(Blog blog) {

        // add category
        jdbcTemplate.update(SQL_ADD_CATEGORY, blog.getCategory());

        //get author id
        int authorId = jdbcTemplate.queryForObject(SQL_GET_AUTHOR_ID, Integer.class, blog.getAuthor());
        //get category id
        int categoryId = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_ID, Integer.class, blog.getCategory());

        jdbcTemplate.update(SQL_ADD_BLOG,
                blog.getTitle(),
                blog.getContent(),
                authorId,
                categoryId,
                blog.isPublished(),
                blog.getDate()
        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        blog.setId(id);

        // add tags
//        if (blog.getTags().isEmpty()) {
//            blog.setTags(null);
//        } else {
//            for (int i = 0; i < blog.getTags().size(); i++) {
//                jdbcTemplate.update(SQL_ADD_BLOG_TAGS, blog.getTags().get(i));
//                int tagId = jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, blog.getTags().get(i));
//                jdbcTemplate.update(SQL_ADD_BLOGS_TAGS_BRIDGE, id, tagId);
//            }
//        }
        if (!blog.getTags().isEmpty()) {

            for (int i = 0; i < blog.getTags().size(); i++) {
                jdbcTemplate.update(SQL_ADD_BLOG_TAGS, blog.getTags().get(i));
                int tagId = jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, blog.getTags().get(i));
                jdbcTemplate.update(SQL_ADD_BLOGS_TAGS_BRIDGE, id, tagId);
            }
        }

        return blog;
    }

    //GET A BLOG BY ID
    private static final String SQL_SELECT_BY_ID = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "	LEFT OUTER JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "    LEFT OUTER JOIN authorities ON `authors`.username = authorities.username\n"
            + "    LEFT OUTER JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "	LEFT OUTER JOIN categories ON blogs.category_id = categories.category_id\n"
            + "    LEFT OUTER JOIN tags ON tags.tag_id = blogs_tags.tag_id WHERE blogs.blog_id = ?";

    @Override
    public Blog getBlogById(int id) {
        try {
            List<Blog> blog = (List<Blog>) jdbcTemplate.query(SQL_SELECT_BY_ID, new BlogExtractor(), id);
            return blog.get(0);
        } catch (EmptyResultDataAccessException | IndexOutOfBoundsException e) {
            return null;
        }
    }

    //UPDATE BLOGS
    private static final String SQL_UPDATE_BLOG = "UPDATE blogs SET title = ?, "
            + " content = ?, author_id = ?, date = ?, category_id = ?, "
            + " published = ? WHERE blog_id = ?";

    private static final String SQL_REMOVE_TAGS = "DELETE FROM blogs_tags WHERE blog_id = ?";

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void updateBlog(Blog blog) {

        // add category
        jdbcTemplate.update(SQL_ADD_CATEGORY, blog.getCategory());

        //get author id
        int authorId = jdbcTemplate.queryForObject(SQL_GET_AUTHOR_ID, Integer.class, blog.getAuthor());
        //get category id
        int categoryId = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_ID, Integer.class, blog.getCategory());
        // get blog id
        int blogId = blog.getId();


//
//        String formattedDate = null;
//        try {
//            Date date = new Date(blog.getDate());
//            formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Date not formatted");
//        }

        jdbcTemplate.update(SQL_UPDATE_BLOG,
                blog.getTitle(),
                blog.getContent(),
                authorId,
                blog.getDate(),
                categoryId,
                blog.isPublished(),
                blogId
        );

//        if (blog.getTags().isEmpty()) {
//            blog.setTags(null);
//        } else {
//
//            jdbcTemplate.update(SQL_REMOVE_TAGS, blogId);
//            for (int i = 0; i < blog.getTags().size(); i++) {
//                jdbcTemplate.update(SQL_ADD_BLOG_TAGS, blog.getTags().get(i));
//                int tagId = jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, blog.getTags().get(i));
//                jdbcTemplate.update(SQL_ADD_BLOGS_TAGS_BRIDGE, blogId, tagId);
//            }
//        }
        if (!blog.getTags().isEmpty()) {

            jdbcTemplate.update(SQL_REMOVE_TAGS, blogId);
            for (int i = 0; i < blog.getTags().size(); i++) {
                jdbcTemplate.update(SQL_ADD_BLOG_TAGS, blog.getTags().get(i));
                int tagId = jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, blog.getTags().get(i));
                jdbcTemplate.update(SQL_ADD_BLOGS_TAGS_BRIDGE, blogId, tagId);
            }
        }

    }

    //DELETE A BLOG
    private static final String SQL_REMOVE_THAT_BLOG = "DELETE FROM blogs WHERE blog_id = ?";
    private static final String SQL_REMOVE_BLOG_TAGS = "DELETE FROM blogs_tags WHERE blog_id = ?";

    @Override
    public void deleteBlog(int id) {
        jdbcTemplate.update(SQL_REMOVE_THAT_BLOG, id);
        jdbcTemplate.update(SQL_REMOVE_BLOG_TAGS, id);
    }

    //SEARCH BLOGS BY TITLE
    private static final String SQL_SEARCH_BY_TITLE
            = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "	LEFT OUTER JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "    LEFT OUTER JOIN authorities ON `authors`.username = authorities.username\n"
            + "    LEFT OUTER JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "	LEFT OUTER JOIN categories ON blogs.category_id = categories.category_id\n"
            + "    LEFT OUTER JOIN tags ON tags.tag_id = blogs_tags.tag_id WHERE blogs.published = true AND blogs.title LIKE ?";

    @Override
    public List<Blog> searchBlogsByTitle(String searchTitle) {
        try {
            searchTitle = "%" + searchTitle + "%";

            return (List<Blog>) jdbcTemplate.query(SQL_SEARCH_BY_TITLE, new BlogExtractor(), searchTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SEARCH BLOGS BY CATEGORY
    private static final String SQL_SEARCH_BY_CATEGORY
            = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "	LEFT OUTER JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "    LEFT OUTER JOIN authorities ON `authors`.username = authorities.username\n"
            + "    LEFT OUTER JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "	LEFT OUTER JOIN categories ON blogs.category_id = categories.category_id\n"
            + "    LEFT OUTER JOIN tags ON tags.tag_id = blogs_tags.tag_id WHERE blogs.published = true AND categories.category = ?";

    @Override
    public List<Blog> searchBlogsByCategory(String searchCategory) {
        try {
            return (List<Blog>) jdbcTemplate.query(SQL_SEARCH_BY_CATEGORY, new BlogExtractor(), searchCategory);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    //SEARCH BLOGS BY AUTHOR
    private static final String SQL_SEARCH_BY_AUTHOR
            = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "	LEFT OUTER JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "    LEFT OUTER JOIN authorities ON `authors`.username = authorities.username\n"
            + "    LEFT OUTER JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "	LEFT OUTER JOIN categories ON blogs.category_id = categories.category_id\n"
            + "    LEFT OUTER JOIN tags ON tags.tag_id = blogs_tags.tag_id WHERE blogs.published = true AND `authors`.username = ?";

    @Override
    public List<Blog> searchBlogsByAuthor(String searchAuthor) {
        try {
            return (List<Blog>) jdbcTemplate.query(SQL_SEARCH_BY_AUTHOR, new BlogExtractor(), searchAuthor);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    //SEARCH BLOGS BY DATE
    private static final String SQL_SEARCH_BY_DATE
            = "SELECT blog_id, title, "
            + "content, authors.username, `date`, categories.category, image, "
            + "published FROM blogs JOIN authors ON blogs.author_id = "
            + "authors.author_id JOIN categories ON blogs.category_id = "
            + "categories.category_id WHERE WHERE blogs.published = true AND blogs.`date` = ?";

    @Override
    public List<Blog> searchBlogsByDate(String searchDate) {
        try {
            return (List<Blog>) jdbcTemplate.query(SQL_SEARCH_BY_DATE, new BlogExtractor(), searchDate);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SEARCH BLOGS BY TAGS
    private static final String SQL_GET_POSTS_BY_TAGID = "SELECT blogs_tags.blog_id FROM blogs_tags WHERE blogs_tags.tag_id = ?";

    private static String SQL_BLOG_SEARCH_RESULTS = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "LEFT JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "LEFT JOIN authorities ON `authors`.username = authorities.username\n"
            + "LEFT JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "LEFT JOIN categories ON blogs.category_id = categories.category_id\n"
            + "LEFT JOIN tags ON tags.tag_id = blogs_tags.tag_id WHERE blogs.published = true AND blogs.blog_id = ?";

    @Override
    public List<Blog> searchBlogsByTags(String tagName) {

        try {
            int tagId = jdbcTemplate.queryForObject(SQL_GET_TAG_ID, Integer.class, tagName);
            List<Integer> blogIds = jdbcTemplate.queryForList(SQL_GET_POSTS_BY_TAGID, Integer.class, tagId);

//            String sql = " or blog_id = ?";
//            String sqlStatements = "";
//            for (int i = 1; i < blogIds.size(); i++) {
//                sqlStatements += sql;
//            }

            List<Blog> blogList = new ArrayList<>();
            for (int i = 0; i < blogIds.size(); i++) {
                try{
                List<Blog> blogs = (List<Blog>) jdbcTemplate.query(SQL_BLOG_SEARCH_RESULTS, new BlogExtractor(), blogIds.get(i));
                Blog blog = blogs.get(0);
                blogList.add(blog);
                }catch(IndexOutOfBoundsException e){
                    continue;
                }
            }

            return blogList;
//            SQL_BLOG_SEARCH_RESULTS += sqlStatements;

//            return (List<Blog>) jdbcTemplate.query(SQL_BLOG_SEARCH_RESULTS, new BlogExtractor(), blogIds);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //get all categories
    private static final String SQL_GET_ALL_CATEGORIES = "SELECT category FROM categories";

    @Override
    public List<String> getAllBlogCategories() {
        return jdbcTemplate.queryForList(SQL_GET_ALL_CATEGORIES, String.class);
    }

    //get all blogs
    private static final String SQL_GET_ALL_BLOGS = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "	LEFT OUTER JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "    LEFT OUTER JOIN authorities ON `authors`.username = authorities.username\n"
            + "    LEFT OUTER JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "	LEFT OUTER JOIN categories ON blogs.category_id = categories.category_id\n"
            + "    LEFT OUTER JOIN tags ON tags.tag_id = blogs_tags.tag_id ORDER BY blogs.`date` DESC";

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> allBlogs;
        allBlogs = (List<Blog>) jdbcTemplate.query(SQL_GET_ALL_BLOGS, new BlogExtractor());

        return allBlogs;
    }
    
    //get all blogs
    private static final String SQL_GET_ALL_PUBLISHED_BLOGS = "SELECT blogs.blog_id, title, content, authors.username, `date`, categories.category, tags.tag, published FROM blogs\n"
            + "	LEFT OUTER JOIN `authors` ON blogs.author_id = `authors`.author_id\n"
            + "    LEFT OUTER JOIN authorities ON `authors`.username = authorities.username\n"
            + "    LEFT OUTER JOIN blogs_tags ON blogs.blog_id = blogs_tags.blog_id\n"
            + "	LEFT OUTER JOIN categories ON blogs.category_id = categories.category_id\n"
            + "    LEFT OUTER JOIN tags ON tags.tag_id = blogs_tags.tag_id WHERE blogs.published ORDER BY blogs.`date` DESC";

    @Override
    public List<Blog> getAllPublishedBlogs() {
        List<Blog> allPubBlogs;
        allPubBlogs = (List<Blog>) jdbcTemplate.query(SQL_GET_ALL_PUBLISHED_BLOGS, new BlogExtractor());

        return allPubBlogs;
    }

    private static class BlogExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

            Map<Integer, Blog> map = new LinkedHashMap<>();
            ArrayList<String> tagsL = new ArrayList<>();
            Blog blog = null;
            while (rs.next()) {
                int id = rs.getInt("blog_id");
                blog = map.get(id);
                if (blog == null) {
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String author = rs.getString("username");
                    String date = rs.getString("date");
                    String category = rs.getString("category");
                    boolean published = rs.getBoolean("published");
                    blog = new Blog(id, title, content, author, date, category, published);
                    map.put(id, blog);
                    tagsL = new ArrayList<>();
                }
                if (rs.getString("tag") != null) {
                    tagsL.add(rs.getString("tag"));
                    blog.setTags(tagsL);
                } else {
                    blog.setTags(new ArrayList<>());
                }
            }
            return new ArrayList<>(map.values());
        }
    }

}
