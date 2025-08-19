package com.bhabani.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEntity {
	private Integer productId;
	private String productName;
	private Integer productPrice;
}
