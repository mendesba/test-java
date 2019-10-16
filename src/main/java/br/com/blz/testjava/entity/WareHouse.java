package br.com.blz.testjava.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WareHouse implements Serializable {

	private static final long serialVersionUID = 7668422585914031640L;

	private String locality;
	private int quantity;
	private String type;

	public WareHouse() {
		// Constructor without parameters.
	}

}
