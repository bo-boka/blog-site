/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dao;

import com.tsguild.blogcapstone.dto.Page;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class PageDaoImpl implements PageDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_ADD_PAGE = "INSERT INTO pages (title, content, image) \n" +
"		VALUE (?, ?, ?)";
            

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Page createPage(Page page) {
        jdbcTemplate.update(SQL_ADD_PAGE,
                page.getTitle(),
                page.getContent(),
                page.getImage()
        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        page.setId(id);
        return page;
    }

    private static final String SQL_SELECT_BY_ID
            = " SELECT * FROM pages WHERE page_id = ? ";
    
    @Override
    public Page getPageById(int id) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new PageMapper(), id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    
    private static final String SQL_GET_ALL_PAGES
            = " SELECT * FROM pages";
    
    @Override
    public List<Page> getAllPages() {
        List<Page> allPages;
        allPages = jdbcTemplate.query(SQL_GET_ALL_PAGES, new PageMapper());
        return allPages;
    }
    
    private static final String SQL_UPDATE_PAGE
            = "UPDATE `pages` SET `title` = ?, `content` = ?, `image` = ? WHERE `page_id` = ?";

    @Override
    public void updatePage(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE,
                page.getTitle(),
                page.getContent(),
                page.getImage(),
                page.getId());
    }

    private static final String SQL_REMOVE_PAGE
            = "DELETE FROM pages WHERE page_id = ?";
    
    @Override
    public void deletePage(int id) {
        jdbcTemplate.update(SQL_REMOVE_PAGE, id);
    }
    
    private static final class PageMapper implements RowMapper<Page> {
        
        @Override
        public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
            Page mappedPage = new Page();
            Integer id = rs.getInt("page_id");
            String title = rs.getString("title");
            String content = rs.getString("content");
//            Blob image = rs.getBlob("image");
            
            mappedPage.setId(id);
            mappedPage.setTitle(title);
            mappedPage.setContent(content);
//            mappedPage.setImage(image);
            
            return mappedPage;
        }
    }

}
