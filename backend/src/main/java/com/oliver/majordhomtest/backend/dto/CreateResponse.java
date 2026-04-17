package com.oliver.majordhomtest.backend.dto;
public class CreateResponse {

    private Long id;
    private String message;

    public CreateResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}