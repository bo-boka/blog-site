/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.blogcapstone.dto;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Blog {
    
    private int id;
    
    @NotEmpty(message="Title can't be empty.")
    @Length(max=50, message="Title must be no more than 50 characters in length.")
    private String title;
    
    @NotEmpty(message="Content can't be empty.")
    @Length(max=10000, message="Content must be no more than 10000 characters in length.")
    private String content;
    
    @NotEmpty(message="Author can't be empty.")
    @Length(max=50, message="Author must be no more than 50 characters in length.")
    private String author;
    
    @NotEmpty(message="Date can't be empty.")
    @Length(max=40, message="Date must be no more than 50 characters in length.")
    private String date;
    
    @NotEmpty(message="Category can't be empty.")
    @Length(max=50, message="Category must be no more than 50 characters in length.")
    private String category;
    
    private Blob image;
    private ArrayList<String> tags;
    private boolean published;

    public Blog(String title, String content, String author, String date, String category, Blob image, ArrayList tags, boolean published) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
        this.image = image;
        this.tags = tags;
        this.published = published;
    }
    
    public Blog(String title, String content, String author, String date, String category, ArrayList tags, boolean published) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
        this.tags = tags;
        this.published = published;
    }
    
    public Blog(int id, String title, String content, String author, String date, String category, boolean published) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
        this.published = published;
    }

    public Blog(int id, String title, String content, String author, String date, String category, ArrayList<String> tags, boolean published) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.category = category;
        this.tags = tags;
        this.published = published;
    }

    public Blog() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.content);
        hash = 79 * hash + Objects.hashCode(this.author);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.category);
        hash = 79 * hash + Objects.hashCode(this.image);
        hash = 79 * hash + Objects.hashCode(this.tags);
        hash = 79 * hash + (this.published ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.published != other.published) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }


}