package com.sapient.catalouge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.catalouge.model.ProductSeller;

@Repository
public interface ProductSellerRepository extends JpaRepository<ProductSeller, Long>{
}
