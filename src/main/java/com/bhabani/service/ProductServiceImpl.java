package com.bhabani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bhabani.entity.ProductEntity;

@Service
public class ProductServiceImpl implements ProductService{

	List<ProductEntity> productList=new ArrayList<>();
	
	@Override
	public String addProduct(ProductEntity productEntity) {
		try {
			productList.add(productEntity);
			return "Product added successfully";
		} catch (Exception e) {
			return "Product not added ";
		}		
	}

	@Override
	public ProductEntity getProductById(Integer productId) {
		
		Optional<ProductEntity> optProduct = productList.stream().filter(product->product.getProductId().equals(productId)).findFirst();
		if (optProduct.isPresent()) {
			return optProduct.get();
		}
		return null;
		
	}

	@Override
	public List<ProductEntity> getAllProduct() {
		return productList;
	}

	@Override
	public String updateProduct(Integer productId,ProductEntity productEntity) {
		Optional<ProductEntity> optProduct = productList.stream().filter(product->product.getProductId().equals(productId)).findFirst();
		if (optProduct.isPresent()) {
			 ProductEntity existingProduct = optProduct.get();
			 BeanUtils.copyProperties(productEntity, existingProduct);
			 try {
				 productList.set(productId-1, productEntity);
				 return "product updation successful";
			} catch (Exception e) {
				return "Product updation failed";
			}		
		}
		return "Product Not found";
	}

	@Override
	public String deleteProduct(Integer productId) {
		Optional<ProductEntity> optProduct = productList.stream().filter(product->product.getProductId().equals(productId)).findFirst();
		if (optProduct.isPresent()) {
			productList.remove(optProduct.get());
			return "Product deleted successfully";		
		}
		return "Product not found";
	}

}
