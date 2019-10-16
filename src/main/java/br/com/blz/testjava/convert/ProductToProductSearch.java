package br.com.blz.testjava.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.dto.ProductSearch;
import br.com.blz.testjava.entity.Product;

@Component
public class ProductToProductSearch implements Converter<Product, ProductSearch> {

	public ProductToProductSearch() {
		// Constructor without parameters.
	}

	@Override
	public ProductSearch convert(Product source) {

		ProductSearch target = new ProductSearch();

		target.setSku(source.getSku());
		target.setName(source.getName());
		target.setMarketable(source.isMarketable());
		target.setInventory(source.getInventory());

		return target;
	}
}
