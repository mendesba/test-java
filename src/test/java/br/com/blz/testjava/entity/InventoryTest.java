package br.com.blz.testjava.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InventoryTest {

	@Test
	public void Test() {
		Inventory inventory = loadInventory();

		
		assertNotNull("",inventory);
		assertEquals("", 10, inventory.getQuantity());
		
		assertNotNull("", inventory.getWarehouses());
		assertThat(inventory.getWarehouses().size()>0).isTrue();
		
	}
	
	private Inventory loadInventory() {
		
		Inventory inventory = new Inventory();
		inventory.setQuantity(10);
		
		List<WareHouse> warehouses = new ArrayList<>();
		WareHouse warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE");
		warehouse.setQuantity(10);
		warehouse.setType("TIPO");
		warehouses.add(warehouse);
		
		inventory.setWarehouses(warehouses);
		
		return inventory;
	}
}
