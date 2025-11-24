package com.example.main.model;

public class Change {

    public Change(String type, Product product) {
        this.product = product;
        this.type = type;
    }

    private String type;

    private Product product;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
