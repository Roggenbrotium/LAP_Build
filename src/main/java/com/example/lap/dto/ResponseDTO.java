package com.example.lap.dto;

public class ResponseDTO {
    private String message;
    private StatusCode statusCode;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, StatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
