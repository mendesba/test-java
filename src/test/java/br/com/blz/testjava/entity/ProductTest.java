package br.com.blz.testjava.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProductTest {

	@Test
	public void Test() {
		Product product = loadProduct();
		
		assertNotNull("",product);
		assertEquals("", "4111", product.getSku().toString());
		assertEquals("","Nome do Produto", product.getName());
		
		assertNotNull("",product.getInventory());
		assertEquals("",10, product.getInventory().getQuantity());
		
		assertNotNull("", product.getInventory().getWarehouses());
		assertThat(product.getInventory().getWarehouses().size()>0).isTrue();
		
	}
	
	private Product loadProduct() {
		Product product = new Product();
		product.setSku(4111L);
		product.setName("Nome do Produto");
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(10);
		
		List<WareHouse> warehouses = new ArrayList<>();
		WareHouse warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE");
		warehouse.setQuantity(10);
		warehouse.setType("TIPO");
		warehouses.add(warehouse);
		
		inventory.setWarehouses(warehouses);
		
		product.setInventory(inventory);
		
		return product;
	}
}
