package com.bhabani.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
	private String productName;
	
	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than 0")
	@Min(value = 1, message = "Price must be at least 1")
	private Integer productPrice;
}
