package com.bhabani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhabani.entity.ProductEntity;
import com.bhabani.mapper.ProductMapper;
import com.bhabani.repository.ProductRepository;
import com.bhabani.response.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductMapper prodMapper;
	
	@Override
	public String addProduct(ProductResponse productResponse) {
		ProductEntity productEntity = prodMapper.toProductEntity(productResponse);
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
	public List<ProductResponse> getAllProduct() {
		return prodMapper.toListOfProductResponse(productRepo.findAll());
	}

	@Override
	public String updateProduct(Integer productId,ProductResponse productResponse) {
		Optional<ProductEntity> optProd = productRepo.findById(productId);
		if (optProd.isPresent()) {
			ProductEntity existingProduct = optProd.get();
			prodMapper.updateProductFromDto(productResponse, existingProduct);
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

}
