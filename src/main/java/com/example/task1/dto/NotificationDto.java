package com.example.task1.dto;

import java.time.LocalDateTime;

public class NotificationDto {
    private Long userId;
    private Long bookId;
    private String message;
    private LocalDateTime timestamp;

    public NotificationDto() {}

    public NotificationDto(Long userId, Long bookId, String message, LocalDateTime timestamp) {
        this.userId = userId;
        this.bookId = bookId;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters və Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}