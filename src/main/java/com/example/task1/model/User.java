package com.example.task1.model;


import com.example.task1.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Column;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(
            name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> favoriteBookList = new HashSet<>();

    public User(){

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Book> getFavoriteBookList() {
        return favoriteBookList;
    }

    public void setFavoriteBookList(Set<Book> favoriteBookList) {
        this.favoriteBookList = favoriteBookList;
    }


}
