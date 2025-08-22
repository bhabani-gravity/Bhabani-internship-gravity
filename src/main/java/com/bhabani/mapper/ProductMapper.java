package com.bhabani.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bhabani.dto.ProductRequestDto;
import com.bhabani.dto.ProductResponseDto;
import com.bhabani.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(target = "productId", ignore = true)
	ProductEntity toProductEntity(ProductRequestDto productRequestDto);
	
	List<ProductResponseDto> toListOfProductResponse(List<ProductEntity> productEntityList);
	
	 @Mapping(target = "productId", ignore = true)
	void updateProductFromDto(ProductRequestDto productRequestDto,@MappingTarget ProductEntity prodEntity);
	 
	ProductResponseDto toProductResponseDto(ProductEntity productEntity);
}
