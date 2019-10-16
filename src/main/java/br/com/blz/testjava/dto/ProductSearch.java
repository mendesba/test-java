package br.com.blz.testjava.dto;

import br.com.blz.testjava.entity.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearch {

	 private Long sku;
	 private String name;
	 private Inventory inventory;
	 private boolean isMarketable;

	public ProductSearch() {
		// Constructor without parameters.
	}

}
