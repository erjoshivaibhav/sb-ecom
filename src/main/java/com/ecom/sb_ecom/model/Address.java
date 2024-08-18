package com.ecom.sb_ecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private int houseNumber;
    private String area;
    private String city;
    private String state;
    private String pin;

    @ManyToMany(mappedBy = "addresses")
    private List<User> user = new ArrayList<>();

}
