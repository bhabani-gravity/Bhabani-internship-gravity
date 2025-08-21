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
import com.bhabani.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public String addProduct(ProductEntity productEntity) {
		ProductEntity prodEntity = productRepo.save(productEntity);	
		if (prodEntity!=null) {
			return "product added successfully";
		}
		return "Failed to save the product";
	}

	@Override
	public ProductEntity getProductById(Integer productId) {
		
		Optional<ProductEntity> optProd = productRepo.findById(productId);
		if (optProd.isPresent()) {
			return optProd.get();			
		}
		return null;
		
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
		return "Failed to update Product";
	}

	@Override
	public String deleteProduct(Integer productId) {
		boolean status = productRepo.existsById(productId);
		if (status) {
			productRepo.deleteById(productId);
			return "Product deleted successfuly";
		}
		return "Failed to deleted Product ";
	}

	@Override
	public Page<ProductEntity> getProducts(Integer page, Integer size, String sortField, String direction) {
		Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(sortField).descending():Sort.by(sortField).ascending();
		Pageable pageable = PageRequest.of(page, size,sort);
		return productRepo.findAll(pageable);
	}

}
