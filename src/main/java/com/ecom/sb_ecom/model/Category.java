package com.ecom.sb_ecom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @NonNull()
    @NotEmpty(message = "Category name cannot be Empty..!!")
    @Size(min = 3, message = "Category name should be at least 3 characters..!!")
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
