package com.sapient.catalouge.service;

import java.util.List;

import com.sapient.catalouge.model.Product;
import com.sapient.catalouge.model.ProductSeller;
import com.sapient.catalouge.model.Seller;

public interface CataloueService {
	
	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public void deleteProduct(Long productId);
	
	public List<Product> loadData();
	
	public Seller addSeller(Seller seller);
	
	public ProductSeller updateProuctSeller(Long productId, Long sellerId);
	

}
