package com.miko.Assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MikoProductsDTO {

    private String productName;
    private double price;
    private String category;
    private String manufacturer;
    private String description;


    @Override
    public String toString() {
        return "MikoProductsDTO{" +

                " productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
