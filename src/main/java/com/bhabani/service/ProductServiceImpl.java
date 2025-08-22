package com.bhabani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bhabani.entity.ProductEntity;
import com.bhabani.exceptions.FailedToSaveProductException;
import com.bhabani.exceptions.ProductNotFoundException;
import com.bhabani.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public String addProduct(ProductEntity productEntity) {
		if (productRepo.existsById(productEntity.getProductId())) {
			return "Product id already exist . Could not save the product";
		}
		ProductEntity prodEntity = productRepo.save(productEntity);	
		if (prodEntity!=null) {
			return "product added successfully";
		}
		throw new FailedToSaveProductException("Failed to save product");
	}

	@Override
	public ProductEntity getProductById(Integer productId) {
		
		Optional<ProductEntity> optProd = productRepo.findById(productId);
		if (optProd.isPresent()) {
			return optProd.get();			
		}
		throw new ProductNotFoundException("Product not found");
		
	}

	@Override
	public List<ProductEntity> getAllProduct() {
		return productRepo.findAll();
	}

	@Override
	public String updateProduct(Integer productId,ProductEntity productEntity) {
		Optional<ProductEntity> optProd = productRepo.findById(productId);
		if (optProd.isPresent()) {
			ProductEntity existingProduct = optProd.get();
			BeanUtils.copyProperties(productEntity, existingProduct);
			productRepo.save(existingProduct);
			return "Product updation successful";
		}
		throw new ProductNotFoundException("Product not found, Can not perform Update Operation");
	}

	@Override
	public String deleteProduct(Integer productId) {
		boolean status = productRepo.existsById(productId);
		if (status) {
			productRepo.deleteById(productId);
			return "Product deleted successfuly";
		}
		throw new ProductNotFoundException("Product not found, can not delete product");
	}

	@Override
	public Page<ProductEntity> getProducts(Integer page, Integer size, String sortField, String direction) {
		Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(sortField).descending():Sort.by(sortField).ascending();
		Pageable pageable = PageRequest.of(page, size,sort);
		return productRepo.findAll(pageable);
	}

}
