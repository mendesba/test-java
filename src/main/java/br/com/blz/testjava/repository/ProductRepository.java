package br.com.blz.testjava.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
	Product findBySku(Long sku);
}
