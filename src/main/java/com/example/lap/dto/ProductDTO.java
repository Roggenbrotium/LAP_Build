package com.example.lap.dto;

public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private Long calories;

    private Long sugar;

    private float price;

    private String imagePath;

    private int amount;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Long calories, Long sugar, float price, String imagePath, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.sugar = sugar;
        this.price = price;
        this.imagePath = imagePath;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getSugar() {
        return sugar;
    }

    public void setSugar(Long sugar) {
        this.sugar = sugar;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
