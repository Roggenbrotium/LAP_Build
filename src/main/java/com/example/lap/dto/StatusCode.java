package com.example.lap.dto;

public enum StatusCode {
    OK (1),
    ERROR (2),
    INTERNAL_SERVER_ERROR (3);

    public final int id;

    StatusCode(int id) {
        this.id = id;
    }

    public static StatusCode getById(int id) {
        for(StatusCode p : values()) {
            if(p.id == id) {
                return p;
            }
        }

        return OK;
    }
}
