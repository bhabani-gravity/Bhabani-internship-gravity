package com.bhabani.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhabani.dto.ProductRequestDto;
import com.bhabani.dto.ProductResponseDto;
import com.bhabani.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductRest {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public String saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
		return productService.addProduct(productRequestDto);
	}
	
	@GetMapping("/find/{productId}")
	public ProductResponseDto getProductById(@PathVariable Integer productId) {
		return productService.getProductById(productId);
	}
	
	@GetMapping("/findAll")
	public List<ProductResponseDto> getAllProducts() {
		return productService.getAllProduct();
	}
	
	@PutMapping("/update/{productId}")
	public String updateProducts(@PathVariable Integer productId,
								@Valid @RequestBody ProductRequestDto productRequestDto) {
		return productService.updateProduct(productId, productRequestDto);
	}
	
	@DeleteMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable Integer productId) {
		return productService.deleteProduct(productId);
	}
	
	@GetMapping("/page")
	public Page<ProductResponseDto> getProducts(
											@RequestParam (defaultValue = "0") Integer page,
											@RequestParam (defaultValue = "3") Integer size,
											@RequestParam (defaultValue = "productId") String sortField,
											@RequestParam (defaultValue = "asc") String direction){
		return productService.getProducts(page, size, sortField, direction);
	}
	
}
