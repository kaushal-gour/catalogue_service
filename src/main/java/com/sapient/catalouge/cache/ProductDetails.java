package com.sapient.catalouge.cache;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sapient.catalouge.model.Product;
import com.sapient.catalouge.repo.ProductRepository;

@Component
public class ProductDetails implements ApplicationListener<ApplicationReadyEvent>{
	
	@Autowired ProductRepository productRepository;
	
	
	public static List<Product> inMemoryProducts = new CopyOnWriteArrayList<>();

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		inMemoryProducts = productRepository.findAll();
	}

}
