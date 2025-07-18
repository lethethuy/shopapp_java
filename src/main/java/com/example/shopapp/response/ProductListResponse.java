package com.example.shopapp.response;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductListResponse {
    private List<ProductResponse> products;
    private int totalPage;

}
