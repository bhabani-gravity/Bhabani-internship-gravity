package com.bhabani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bhabani.dto.ProductRequestDto;
import com.bhabani.dto.ProductResponseDto;
import com.bhabani.entity.ProductEntity;
import com.bhabani.mapper.ProductMapper;
import com.bhabani.exceptions.FailedToSaveProductException;
import com.bhabani.exceptions.ProductNotFoundException;
import com.bhabani.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductMapper prodMapper;
	
	@Override
	public String addProduct(ProductRequestDto productRequestDto) {
		ProductEntity productEntity = prodMapper.toProductEntity(productRequestDto);
		ProductEntity prodEntity = productRepo.save(productEntity);	
		if (prodEntity!=null) {
			return "product added successfully";
		}
		throw new FailedToSaveProductException("Failed to save product");
	}

	@Override
	public ProductResponseDto getProductById(Integer productId) {
		
		Optional<ProductEntity> optProd = productRepo.findById(productId);
		if (optProd.isPresent()) {
			ProductEntity productEntity= optProd.get();	
			return prodMapper.toProductResponseDto(productEntity);
		}
		throw new ProductNotFoundException("Product not found");
		
	}

	@Override
	public List<ProductResponseDto> getAllProduct() {
		return prodMapper.toListOfProductResponse(productRepo.findAll());
	}

	@Override
	public String updateProduct(Integer productId,ProductRequestDto productRequestDto) {
		Optional<ProductEntity> optProd = productRepo.findById(productId);
		if (optProd.isPresent()) {
			ProductEntity existingProduct = optProd.get();
			prodMapper.updateProductFromDto(productRequestDto, existingProduct);
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
	public Page<ProductResponseDto> getProducts(Integer page, Integer size, String sortField, String direction) {
		Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(sortField).descending():Sort.by(sortField).ascending();
		Pageable pageable = PageRequest.of(page, size,sort);
		 Page<ProductEntity> productEntityPage = productRepo.findAll(pageable);
		 List<ProductEntity> content = productEntityPage.getContent();
		 List<ProductResponseDto> listOfProductResponse = prodMapper.toListOfProductResponse(content);
		 return  new PageImpl<>(listOfProductResponse, pageable, productEntityPage.getTotalElements());
		 
	}

}
