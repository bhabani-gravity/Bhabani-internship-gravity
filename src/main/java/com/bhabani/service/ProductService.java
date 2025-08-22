package com.bhabani.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bhabani.entity.ProductEntity;

public interface ProductService {
	public String addProduct(ProductEntity productEntity);
	public ProductEntity getProductById(Integer productId);
	public List<ProductEntity> getAllProduct();
	public String updateProduct(Integer productId,ProductEntity productEntity);
	public String deleteProduct(Integer productId);
	public Page<ProductEntity> getProducts(Integer page,Integer size,String sortField,String direction);
}
