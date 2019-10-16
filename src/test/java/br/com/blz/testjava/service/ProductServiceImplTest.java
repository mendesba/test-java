package br.com.blz.testjava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import br.com.blz.testjava.dto.ProductSearch;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.WareHouse;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.impl.ProductServiceImpl;

public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl service;

	@Mock
	ProductRepository repository;

	@Mock
	ConversionService conversionService;
	
	private ObjectId id;

	@Before
	public void setUp() {
		id = new ObjectId();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveRetornarOProdutoPorSku() {
		Product product = loadProduct();

		when(repository.findBySku(Mockito.any())).thenReturn(product);
		when(conversionService.convert(Mockito.any(Product.class), Matchers.<Class<ProductSearch>>any()))
				.thenReturn(loadProductSearch());

		ProductSearch result = service.findBySku(4111L);
		
		assertNotNull("", result);
		assertEquals("", "4111", product.getSku().toString());
		assertEquals("", "Nome do Produto", product.getName());
		assertNotNull("", product.getInventory());
		assertEquals("", 15, product.getInventory().getQuantity());
		assertThat(product.getInventory().getWarehouses().size() > 0).isTrue();

	}

	@Test
	public void deveAtualizarOsDadosDoProdutoPorSku() {
		Product product = loadProduct();
		product.setId(id);
		
		when(repository.findBySku(Mockito.any())).thenReturn(product);
		
		Product productUpdate = loadProduct();
		productUpdate.setId(id);
		productUpdate.setName("Nome do Produto 2");
		
		when(repository.save(Mockito.any())).thenReturn(productUpdate);
		
		Product result = service.update(productUpdate);

		assertNotNull("", result);
		assertEquals("", "4111", result.getSku().toString());
		assertEquals("", "Nome do Produto 2", result.getName());
		assertNotNull("", result.getInventory());
		assertEquals("", 15, result.getInventory().getQuantity());
		assertThat(result.getInventory().getWarehouses().size() > 0).isTrue();

	}

	@Test
	public void deveSalvarOProduto() {
		Product product = loadProduct();
		when(repository.findBySku(Mockito.any())).thenReturn(null);
		when(repository.save(Mockito.any())).thenReturn(product);
		
		Product result = service.save(product);

		assertNotNull("", result);
		assertEquals("", "4111", product.getSku().toString());
		assertEquals("", "Nome do Produto", product.getName());
		assertNotNull("", product.getInventory());
		assertEquals("", 15, product.getInventory().getQuantity());
		assertThat(product.getInventory().getWarehouses().size() > 0).isTrue();
		
	}
	
	@Test
	public void naoDeveSalvarOProdutoComSKUJaCadastrado() {
		Product product = loadProduct();
		when(repository.findBySku(Mockito.any())).thenReturn(product);
		when(repository.save(Mockito.any())).thenReturn(product);
		
		try {
			service.save(product);
		} catch (Exception ex) {
			assertEquals("", "O SKU[4111] já foi em outro produto.", ex.getMessage());
		}
	}

	@Test
	public void naoDeveRemoverOProdutoPorSKUInvalido() {
		Mockito.when(repository.findBySku(Mockito.any())).thenReturn(null);

		try {
			service.delete(4112L);
		} catch (Exception ex) {
			assertEquals("", "Produto não encontrado.", ex.getMessage());
		}
	}
	
	@Test
	public void deveRemoverOProdutoPorSKU() {
		Mockito.when(repository.findBySku(Mockito.any())).thenReturn(loadProduct());

		try {
			service.delete(4111L);
		} catch (Exception ex) {
			assertEquals("", "Produto não encontrado.", ex.getMessage());
		}
	}

	private Product loadProduct() {
		Product product = new Product();
		product.setSku(4111L);
		product.setName("Nome do Produto");

		Inventory inventory = new Inventory();
		inventory.setQuantity(15);

		List<WareHouse> warehouses = new ArrayList<>();
		WareHouse warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE");
		warehouse.setQuantity(10);
		warehouse.setType("TIPO");
		warehouses.add(warehouse);

		warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE 2");
		warehouse.setQuantity(5);
		warehouse.setType("TIPO 2");
		warehouses.add(warehouse);

		inventory.setWarehouses(warehouses);

		product.setInventory(inventory);

		return product;
	}

	private ProductSearch loadProductSearch() {
		ProductSearch product = new ProductSearch();
		product.setSku(4111L);
		product.setName("Nome do Produto");

		Inventory inventory = new Inventory();
		inventory.setQuantity(15);

		List<WareHouse> warehouses = new ArrayList<>();
		WareHouse warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE");
		warehouse.setQuantity(10);
		warehouse.setType("TIPO");
		warehouses.add(warehouse);

		warehouse = new WareHouse();
		warehouse.setLocality("LOCALIDADE 2");
		warehouse.setQuantity(5);
		warehouse.setType("TIPO 2");
		warehouses.add(warehouse);

		inventory.setWarehouses(warehouses);

		inventory.setWarehouses(warehouses);

		product.setInventory(inventory);

		return product;
	}

}
