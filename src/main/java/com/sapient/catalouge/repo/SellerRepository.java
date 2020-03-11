package com.sapient.catalouge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.catalouge.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
