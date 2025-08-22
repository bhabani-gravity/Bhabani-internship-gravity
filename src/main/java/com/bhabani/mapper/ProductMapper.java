package com.bhabani.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.bhabani.entity.ProductEntity;
import com.bhabani.response.ProductResponse;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductEntity toProductEntity(ProductResponse productResponse);
	List<ProductResponse> toListOfProductResponse(List<ProductEntity> productEntityList);
	void updateProductFromDto(ProductResponse productResponse,@MappingTarget ProductEntity prodEntity);
}
