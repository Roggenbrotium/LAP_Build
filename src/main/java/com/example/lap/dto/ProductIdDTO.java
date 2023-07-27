package com.example.lap.dto;

import java.util.Set;

public class ProductIdDTO {
    Set<Long> ids;

    public ProductIdDTO() {
    }

    public ProductIdDTO(Set<Long> ids) {
        this.ids = ids;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }
}
