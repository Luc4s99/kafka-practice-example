package com.example.branch1.model;

public class Change {

    public Change() {


    }

    public Change(Product product, String type) {
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
