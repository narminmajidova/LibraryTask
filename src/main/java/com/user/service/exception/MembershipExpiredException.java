package com.user.service.exception;

public class MembershipExpiredException extends RuntimeException {
    public MembershipExpiredException(String username) { super("Membership expired for user: " + username); }
}