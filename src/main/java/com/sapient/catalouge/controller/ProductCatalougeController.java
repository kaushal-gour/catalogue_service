package com.sapient.catalouge.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.catalouge.cache.ProductDetails;
import com.sapient.catalouge.exception.DataNotFoundException;
import com.sapient.catalouge.model.Product;
import com.sapient.catalouge.model.ProductSeller;
import com.sapient.catalouge.model.Seller;
import com.sapient.catalouge.service.CataloueService;

@RestController
@RequestMapping("api/v1/catalouge")
public class ProductCatalougeController {

	@Autowired
	CataloueService cataloueService;

	@GetMapping("/price")
	public ResponseEntity<Map<Double, List<Product>>> getProductsByColor() {
		if (ProductDetails.inMemoryProducts.isEmpty()) {
			throw new DataNotFoundException("No Data found");
		}
		return new ResponseEntity<>(
				ProductDetails.inMemoryProducts.stream().collect(Collectors.groupingBy(Product::getPrice)),
				HttpStatus.OK);
	}

	@GetMapping("/colors")
	public ResponseEntity<Map<String, List<Product>>> getProductsByColor(@RequestParam String color) {
		if (ProductDetails.inMemoryProducts.isEmpty()) {
			throw new DataNotFoundException("No Data found");
		}

		return new ResponseEntity<>(
				ProductDetails.inMemoryProducts.stream().collect(Collectors.groupingBy(Product::getColor)),
				HttpStatus.OK);
	}

	@GetMapping("/size")
	public ResponseEntity<Map<String, List<Product>>> getProductsBySize(@RequestParam String size) {
		if (ProductDetails.inMemoryProducts.isEmpty()) {
			throw new DataNotFoundException("No Data found");
		}

		return new ResponseEntity<>(
				ProductDetails.inMemoryProducts.stream().collect(Collectors.groupingBy(Product::getSize)),
				HttpStatus.OK);
	}

	@GetMapping("/availableUnits")
	public ResponseEntity<List<Product>> getProductsBySize() {
		if (ProductDetails.inMemoryProducts.isEmpty()) {
			throw new DataNotFoundException("No Data found");
		}

		return new ResponseEntity<>(ProductDetails.inMemoryProducts.stream().filter(p -> p.getStockQuantity() > 0)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		product = cataloueService.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);

	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		product = cataloueService.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);

	}

	@DeleteMapping("/product")
	public ResponseEntity<String> deleteProduct(@RequestBody Long productId) {
		cataloueService.deleteProduct(productId);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);

	}

	@PostMapping("/seller")
	public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
		seller = cataloueService.addSeller(seller);
		return new ResponseEntity<Seller>(seller, HttpStatus.CREATED);

	}

	@PutMapping("/product/{product_id}/seller{seller_id}")
	public ResponseEntity<ProductSeller> addProductSeller(@PathVariable(name = "product_id") Long product_id,
			@PathVariable(name = "seller_id") Long seller_id) {
		ProductSeller productSeller = cataloueService.updateProuctSeller(product_id, seller_id);
		return new ResponseEntity<>(productSeller, HttpStatus.OK);
	}

	@GetMapping("/load")
	public ResponseEntity<List<Product>> loadData() {

		return new ResponseEntity<List<Product>>(cataloueService.loadData(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProducts(
			@RequestParam(value = "productCode", required = false) Long productCode,
			@RequestParam(value = "productCode", required = false) Long price,
			@RequestParam(value = "color", required = false) String color,
			@RequestParam(value = "size", required = false) String size) {
		List<Product> products = null;
		if (null != productCode) {
			products = ProductDetails.inMemoryProducts.stream().filter(p -> p.getProductCode().equals(productCode))
					.collect(Collectors.toList());
		} else if (null != price) {
			products = ProductDetails.inMemoryProducts.stream()
					.filter(p -> p.getPrice().doubleValue() == price.doubleValue()).collect(Collectors.toList());
		} else if (null != color) {
			products = ProductDetails.inMemoryProducts.stream().filter(p -> p.getColor().equals(color))
					.collect(Collectors.toList());
		} else if (null != size) {
			products = ProductDetails.inMemoryProducts.stream().filter(p -> p.getSize().equals(size))
					.collect(Collectors.toList());
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

}
