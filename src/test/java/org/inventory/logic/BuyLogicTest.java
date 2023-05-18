package org.inventory.logic;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.inventory.dto.InventoryResponse;
import org.inventory.dto.StatusDTO;
import org.inventory.entity.Buy;
import org.inventory.entity.BuyDetail;
import org.inventory.entity.Product;
import org.inventory.repository.BuyRepository;
import org.inventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.inventory.Enums.MessagesEnum.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class BuyLogicTest {
  @Inject
  private BuyLogic logic;

  @InjectMock
  private BuyRepository buyRepository;

  @InjectMock
  private ProductRepository productRepository;

  private Buy buy;
  private BuyDetail buyDetail;
  private Product product;

  @BeforeEach
  void setUp() {
    buyDetail = new BuyDetail();
    buy = new Buy();
    product = new Product();
    product.setInventory(100);
    product.setName("producto1");
    buyDetail.setIdProduct(1);
    buyDetail.setQuantity(1);
    buy.setClientName("name");
    buy.setIdType("CC");
    buy.setProducts(Collections.singletonList(buyDetail));

  }

  @Test
  void makeBuyTest() {
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    Mockito.when(productRepository.save(any())).thenReturn(product);
    Mockito.when(buyRepository.save(any())).thenReturn(buy);
    final var expected = logic.makeBuy(buy);
    assertEquals(expected, new InventoryResponse<>(buy, new StatusDTO(SUCCESS.getMessage())));

  }

  @Test
  void makeBuyProductEmptyTest() {
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());
    final var expected = logic.makeBuy(buy);
    assertEquals(new InventoryResponse<>(null, new StatusDTO("No se encontró el producto id : " + 1)),expected);

  }

  @Test
  void makeBuyProductInventoryOutTest() {
    buy.getProducts().get(0).setQuantity(1000);
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    final var expected = logic.makeBuy(buy);
    assertEquals(new InventoryResponse<>(null, new StatusDTO("No hay cantidad suficiente del producto:" + product.getName())),expected);

  }

  @Test
  void findBuyById() {
    buy.setBuyId(1);
    Mockito.when(buyRepository.findById(any())).thenReturn(Optional.of(buy));
    final var expected = logic.findBuyById(1);
    assertEquals(expected, new InventoryResponse<>(buy, new StatusDTO(SUCCESS.getMessage())));
  }
}