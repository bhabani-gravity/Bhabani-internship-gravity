package com.bhabani.service;

import java.util.List;

import com.bhabani.entity.ProductEntity;

public interface ProductService {
	public String addProduct(ProductEntity productEntity);
	public ProductEntity getProductById(Integer productId);
	public List<ProductEntity> getAllProduct();
	public String updateProduct(Integer productId,ProductEntity productEntity);
	public String deleteProduct(Integer productId);
}
