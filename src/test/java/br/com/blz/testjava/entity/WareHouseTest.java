package br.com.blz.testjava.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class WareHouseTest {

	@Test
	public void Test() {
		WareHouse wareHouse = loadWareHouse();
		
		assertNotNull("",wareHouse);
		assertEquals("", "LOCALIDADE", wareHouse.getLocality());
		assertEquals("", "TIPO", wareHouse.getType());
		assertEquals("", 10, wareHouse.getQuantity());
		
	}
	
	private WareHouse loadWareHouse() {
	
		WareHouse warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE");
		warehouse.setQuantity(10);
		warehouse.setType("TIPO");
		
		return warehouse;
	}
}
