package com.user.service.dto;

public class BookDto {
    private Long id;
    private String title;
    private int year;
    private String favoriteBookList;

    public BookDto() {}

    public BookDto(Long id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public String getFavoriteBookList() {
        return favoriteBookList;
    }

    public void setFavoriteBookList(String favoriteBookList) {
        this.favoriteBookList = favoriteBookList;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}