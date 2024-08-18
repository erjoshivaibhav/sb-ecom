package com.ecom.sb_ecom.dto;

import com.ecom.sb_ecom.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private long id;
    private String productName;
    private String productDescription;
    private String productImage;
    private double productPrice;
    private double productDiscount;
    private double specialPrice;
    @JsonIgnore
    private Category productCategory;
}
