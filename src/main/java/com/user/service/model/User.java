package com.user.service.model;

import com.user.service.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String username;

    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;

    private Boolean isActive;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "has_overdue", nullable = false)
    private boolean hasOverdue = false;

    @Column(name = "active_borrow_count", nullable = false)
    private int activeBorrowCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_status", nullable = false)
    private MembershipStatus membershipStatus = MembershipStatus.ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "book_id")
    @JsonIgnore
    private List<Long> favoriteBookIds = new ArrayList<>();

    public User() {}

    public User(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
}