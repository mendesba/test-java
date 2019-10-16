package br.com.blz.testjava.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductSearch;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	@PostMapping("")
	public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
		product = service.save(product);
		return ResponseEntity.ok().body(product);
	}
	
	@PutMapping("/{sku}")
	public ResponseEntity<Product> update(@PathVariable("sku") Long sku, @Valid @RequestBody Product product) {
		product = service.update(product);
		
		if(product!=null) {
			return ResponseEntity.ok().body(product);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity<String> delete(@PathVariable("sku") Long sku) {
		service.delete(sku);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{sku}")
	public ResponseEntity<ProductSearch> find(@PathVariable("sku") Long sku) {
		ProductSearch product = service.findBySku(sku);
		if(product!=null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
