package com.ecom.sb_ecom.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private String productDescription;
    private String productImage;
    private double productPrice;
    private double productDiscount;
    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;
}
