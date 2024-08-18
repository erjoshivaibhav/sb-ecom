package com.ecom.sb_ecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;


    @Enumerated(EnumType.STRING)
    private  ROLE_NAMES roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> usersList = new ArrayList<>();
}
