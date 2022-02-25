package ru.gb.rest;

import java.time.LocalDateTime;

public class ErrorDto {

    private LocalDateTime timestamp;

    private String message;

    public ErrorDto() {
    }

    public ErrorDto(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
