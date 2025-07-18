package com.example.shopapp.service;

import com.example.shopapp.DTO.ProductDTO;
import com.example.shopapp.DTO.ProductImageDTO;
import com.example.shopapp.models.*;
import com.example.shopapp.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    public com.example.shopapp.models.Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(long id) throws Exception;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);
    com.example.shopapp.models.Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
     ProductImage createProductImage (
            Long productId,
            ProductImageDTO productImageDTO
    )throws Exception;

}
