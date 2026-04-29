package com.user.service;

import com.user.service.security.Permission;

import java.io.Serializable;
import java.util.Set;

public enum Role implements Serializable {

    USER(Set.of(
            Permission.BOOK_READ
    )),

    AUTHOR(Set.of(
            Permission.BOOK_READ,
            Permission.BOOK_CREATE,
            Permission.BOOK_UPDATE,
            Permission.AUTHOR_CREATE
    )),

    ADMIN(Set.of(
            Permission.BOOK_READ,
            Permission.BOOK_CREATE,
            Permission.BOOK_UPDATE,
            Permission.BOOK_DELETE,
            Permission.AUTHOR_CREATE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}