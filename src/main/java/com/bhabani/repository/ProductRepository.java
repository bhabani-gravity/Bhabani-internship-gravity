package com.bhabani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhabani.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
