package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductSearch;
import br.com.blz.testjava.entity.Product;

public interface ProductService {
	Product save(Product product);
	void delete(Long sku);
	ProductSearch findBySku(Long sku);
	Product update(Product product);
}
