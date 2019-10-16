package br.com.blz.testjava.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProductSearch;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	ConversionService conversionService;

	@Override
	public Product save(Product product) {
		logger.info("Initializing - Method save");
		
		Product productSave = repository.findBySku(product.getSku());
		if(productSave!=null) {
			throw new RuntimeException("O SKU["+product.getSku().toString()+"] já foi em outro produto.");
		}
		repository.save(product);
		
		logger.info("Initializing - Method save");
		return product;
	}

	@Override
	public void delete(Long sku) {
		logger.info("Initializing - Method delete");
		
		Product product = repository.findBySku(sku);
		if(product!=null) {
			repository.delete(product);
			logger.info("Finishing - Method delete");
		}else {
			throw new RuntimeException("Produto não encontrado.");
		}
	}

	@Override
	public ProductSearch findBySku(Long sku) {
		logger.info("Initializing - Method findBySku");
		
		Product product = repository.findBySku(sku);
		
		product.getInventory().setQuantity(0);
		
		Optional.ofNullable(product.getInventory().getWarehouses()).ifPresent(l -> l.forEach(warehouse -> {
			product.getInventory().setQuantity(product.getInventory().getQuantity()+warehouse.getQuantity());
		}));

		product.setMarketable(product.getInventory().getQuantity()>0);		
		
		ProductSearch productSearch = conversionService.convert(product, ProductSearch.class);
		
		logger.info("Finishing - Method findBySku");
		
		return productSearch;
	}

	@Override
	public Product update(Product product) {
		logger.info("Initializing - Method update");
		
		Product productSave = repository.findBySku(product.getSku());
		if(productSave!=null) {
			product.setId(productSave.getId());
			repository.save(product);
		}else {
			throw new RuntimeException("Produto não encontrado.");
		}
		
		logger.info("Finishing - Method update");

		return product;
	}

}
