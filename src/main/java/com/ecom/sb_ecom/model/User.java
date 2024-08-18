package com.ecom.sb_ecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "User name cannot be empty")
    @Size(min = 5 , message = "Username should be at least 5 characters")
    private String userName;

    @Email
    @NotEmpty(message = "User email cannot be empty")
    @Size(min = 5 , message = "User email should be at least 5 characters")
    private String userEmail;

    @NotEmpty(message = "User password cannot be empty")
    private String userPassword;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
                )
    private Set<Roles> roles = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "users_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Product> products = new ArrayList<>();

}
