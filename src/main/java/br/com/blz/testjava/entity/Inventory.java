package br.com.blz.testjava.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Inventory  implements Serializable { 
		
	private static final long serialVersionUID = 3438737451245098617L;
	
	private int quantity;	
	private List<WareHouse> warehouses = new ArrayList<>();
	
	public Inventory() {
		//Constructor without parameters.
	}

}
