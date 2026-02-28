package com.example.task1.model;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


    @Entity
    @Table(name="Books")


    public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;

        @Column(name="title")
        private String title;

        @Column(name="year")
        private int year;

        @ManyToOne
        @JoinColumn(name = "author_id")
        private Author author;

        public Book(){ }

        public Book(String title, int year, Author author){
            this.title = title;
            this.year = year;
            this.author = author;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public int getYear() { return year; }
        public void setYear(int year) { this.year = year; }
        public Author getAuthor() { return author; }
        public void setAuthor(Author author) { this.author =author; }
    }