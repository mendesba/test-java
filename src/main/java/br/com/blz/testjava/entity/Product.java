package br.com.blz.testjava.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "Product")
public class Product {

	@Id
	private ObjectId id; 
	private Long sku;
	private String name;
	private Inventory inventory;
	private boolean isMarketable;

	public Product() {
		// Constructor without parameters.
	}

}
