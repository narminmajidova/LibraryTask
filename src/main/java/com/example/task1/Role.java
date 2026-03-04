package com.example.task1;

import com.example.task1.security.Permission;

import java.util.Set;

public enum Role {

    ROLE_USER(Set.of(
            Permission.BOOK_READ
    )),

    ROLE_AUTHOR(Set.of(
            Permission.BOOK_READ,
            Permission.BOOK_CREATE,
            Permission.BOOK_UPDATE
    )),

    ROLE_ADMIN(Set.of(
            Permission.BOOK_READ,
            Permission.BOOK_CREATE,
            Permission.BOOK_UPDATE,
            Permission.BOOK_DELETE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}