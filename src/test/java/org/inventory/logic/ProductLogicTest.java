package org.inventory.logic;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.hibernate.PersistentObjectException;
import org.inventory.dto.InventoryResponse;
import org.inventory.dto.StatusDTO;
import org.inventory.entity.Product;
import org.inventory.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.inventory.Enums.MessagesEnum.BAD_REQUEST;
import static org.inventory.Enums.MessagesEnum.NO_RESULTS;
import static org.inventory.Enums.MessagesEnum.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class ProductLogicTest {

  @Inject
  private ProductLogic logic;

  @InjectMock
  private ProductRepository productRepository;

  private Product product;

  @BeforeEach
  void setUp() {
    product = new Product();

  }

  @Test
  void saveProductTest() {
    Mockito.when(productRepository.save(any())).thenReturn(product);
    final var expected = logic.saveProduct(product);
    assertEquals(expected, new InventoryResponse<>(product, new StatusDTO(SUCCESS.getMessage())));
  }

  @Test
  void saveProductExceptionTest() {
    Mockito.when(productRepository.save(any())).thenThrow(new PersistentObjectException("Fail"));
    final var expected = logic.saveProduct(product);
    assertEquals(expected, new InventoryResponse<>(null, new StatusDTO(BAD_REQUEST.getMessage())));
  }

  @Test
  void updateProductTest() {
    product.setId(1);
    Mockito.when(productRepository.save(any())).thenReturn(product);
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    final var expected = logic.updateProduct(product);
    assertEquals(expected, new InventoryResponse<>(product, new StatusDTO(SUCCESS.getMessage())));
  }

  @Test
  void updateProductExceptionTest() {
    product.setId(1);
    Mockito.when(productRepository.save(any())).thenThrow(new PersistentObjectException("Fail"));
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    final var expected = logic.updateProduct(product);
    assertEquals(expected, new InventoryResponse<>(null, new StatusDTO(BAD_REQUEST.getMessage())));
  }

  @Test
  void updateProductBadRequestTest() {
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    final var expected = logic.updateProduct(product);
    assertEquals(expected, new InventoryResponse<>(null, new StatusDTO(BAD_REQUEST.getMessage())));
  }

  @Test
  void deleteProductTest() {
    product.setId(1);
    product.setStatus(false);
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    Mockito.when(productRepository.save(any())).thenReturn(product);
    final var expected = logic.deleteProduct(product);
    assertEquals(expected, new InventoryResponse<>(product, new StatusDTO(SUCCESS.getMessage())));
  }

  @Test
  void findProductByIdTest() {
    product.setId(1);
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
    final var expected = logic.findProductById(1);
    assertEquals(expected, new InventoryResponse<>(product, new StatusDTO(SUCCESS.getMessage())));
  }

  @Test
  void findProductByIdEmptyTest() {
    product.setId(1);
    Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());
    final var expected = logic.findProductById(1);
    assertEquals(expected, new InventoryResponse<>(null, new StatusDTO(NO_RESULTS.getMessage())));
  }
}