package com.bhabani.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bhabani.entity.ProductEntity;
import com.bhabani.response.ProductResponse;

public interface ProductService {
	public String addProduct(ProductResponse productResponse);
	public ProductEntity getProductById(Integer productId);
	public List<ProductResponse> getAllProduct();
	public String updateProduct(Integer productId,ProductResponse productResponse);
	public String deleteProduct(Integer productId);
	public Page<ProductResponse> getProducts(Integer page,Integer size,String sortField,String direction);
}
