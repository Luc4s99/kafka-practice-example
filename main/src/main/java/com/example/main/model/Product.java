package com.example.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
public class Product {

    public Product() {
    }

    public Product(Integer idProduct, String description, double salePrice, double costPrice) {
        this.idProduct = idProduct;
        this.description = description;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PRODUCT")
    @Getter @Setter
    @JsonProperty("idProduct")
    private Integer idProduct;

    @Column(name = "DESCRIPTION")
    @Getter @Setter
    @JsonProperty("description")
    private String description;

    @Column(name = "SALE_PRICE")
    @Getter @Setter
    @JsonProperty("salePrice")
    private double salePrice;

    @Column(name = "COST_PRICE")
    @Getter @Setter
    @JsonProperty("costPrice")
    private double costPrice;

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", description='" + description + '\'' +
                ", salePrice=" + salePrice +
                ", costPrice=" + costPrice +
                '}';
    }
}
