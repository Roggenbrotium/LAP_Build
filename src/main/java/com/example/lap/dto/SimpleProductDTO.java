package com.example.lap.dto;

public class SimpleProductDTO {
    private Long id;

    private int amount;

    public SimpleProductDTO() {
    }

    public SimpleProductDTO(Long id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

