package com.sapient.catalouge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.catalouge.cache.ProductDetails;
import com.sapient.catalouge.exception.CatalougeDBException;
import com.sapient.catalouge.exception.ProductNotFoundException;
import com.sapient.catalouge.model.Product;
import com.sapient.catalouge.model.ProductSeller;
import com.sapient.catalouge.model.Seller;
import com.sapient.catalouge.repo.ProductRepository;
import com.sapient.catalouge.repo.ProductSellerRepository;
import com.sapient.catalouge.repo.SellerRepository;
import com.sapient.catalouge.service.CataloueService;

@Service
public class CataloueServiceImpl implements CataloueService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	ProductSellerRepository productSellerRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> data = productRepository.findById(product.getId());
		if(!data.isPresent()) {
			throw new ProductNotFoundException("Np Product found with id: "+product.getId());
		}
		product = productRepository.save(product);
		return product;
	}

	@Override
	public void deleteProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			productRepository.delete(product.get());
		}
	}

	@Override
	public List<Product> loadData() {
		ProductDetails.inMemoryProducts = productRepository.findAll();
		return ProductDetails.inMemoryProducts;
	}

	@Override
	public Seller addSeller(Seller seller) {
		
		return sellerRepository.save(seller);
	}

	@Override
	public ProductSeller updateProuctSeller(Long productId, Long sellerId) {
		Optional<Product> productData = productRepository.findById(productId);
		Optional<Seller> sellerData = sellerRepository.findById(sellerId);
		ProductSeller productSeller = null;
		if(productData.isPresent() && sellerData.isPresent()) {
			productSeller = new ProductSeller();
			productSeller.setProductId(productData.get().getId());
			productSeller.setSellerId(sellerData.get().getId());
			
			productSellerRepository.save(productSeller);
		}
		if(productSeller == null) {
			throw new CatalougeDBException("DB Error");
		}
		return productSeller;
		
	}

}
