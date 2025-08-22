package com.bhabani.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bhabani.dto.ProductRequestDto;
import com.bhabani.dto.ProductResponseDto;

public interface ProductService {
	public String addProduct(ProductRequestDto productRequest);
	public ProductResponseDto getProductById(Integer productId);
	public List<ProductResponseDto> getAllProduct();
	public String updateProduct(Integer productId,ProductRequestDto productRequest);
	public String deleteProduct(Integer productId);
	public Page<ProductResponseDto> getProducts(Integer page,Integer size,String sortField,String direction);
}
