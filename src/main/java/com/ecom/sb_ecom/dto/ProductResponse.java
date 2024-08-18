package com.ecom.sb_ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private List<ProductDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Long totalPages;
    private boolean lastPage;


}
