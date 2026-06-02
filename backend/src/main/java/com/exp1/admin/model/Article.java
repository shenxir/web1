package com.exp1.admin.model;

public class Article {
    private Integer id;
    private String title;
    private String author;
    private String time;

    public Article(Integer id, String title, String author, String time) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.time = time;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}
