package org.inventory.logic;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.inventory.dto.InventoryResponse;
import org.inventory.entity.Product;
import org.inventory.repository.ProductRepository;
import org.inventory.util.Utils;

import java.util.Objects;

import static org.inventory.Enums.MessagesEnum.BAD_REQUEST;
import static org.inventory.Enums.MessagesEnum.NO_RESULTS;
import static org.inventory.Enums.MessagesEnum.SUCCESS;

@ApplicationScoped
public class ProductLogic {

  @Inject
  private ProductRepository repository;

  @Inject
  private Utils utility;

  @Transactional
  public InventoryResponse<Product> saveProduct(Product product) {
    var response = new InventoryResponse<Product>();

    try {

      product.setStatus(true);

      var productSaved = repository.save(product);

      response.setBody(productSaved);
      response.setStatusDTO(utility.createStatusDTO(SUCCESS.getMessage()));
      return response;

    } catch (Exception e) {

      return new InventoryResponse(null, utility.createStatusDTO(BAD_REQUEST.getMessage()));
    }

  }

  @Transactional
  public InventoryResponse<Product> updateProduct(Product product) {

    try {

      if (Objects.nonNull(product.getId()) && repository.findById(product.getId()).isPresent()) {
        return new InventoryResponse<>(repository.save(product), utility.createStatusDTO(SUCCESS.getMessage()));
      }

      return new InventoryResponse<>(null, utility.createStatusDTO(BAD_REQUEST.getMessage()));

    } catch (Exception e) {

      return new InventoryResponse<>(null, utility.createStatusDTO(BAD_REQUEST.getMessage()));

    }
  }

  public InventoryResponse<Product> deleteProduct(Product product) {

    try {

      var findProduct = repository.findById(product.getId());
      var productModified = findProduct.get();

      productModified.setStatus(false);

      return new InventoryResponse<>(repository.save(productModified), utility.createStatusDTO(SUCCESS.getMessage()));

    } catch (Exception e) {

      return new InventoryResponse<>(null, utility.createStatusDTO(BAD_REQUEST.getMessage()));
    }

  }

  public InventoryResponse<Product> findProductById(Integer id) {
    var findProduct = repository.findById(id);

    if (findProduct.isEmpty()) {
      return new InventoryResponse<>(null, utility.createStatusDTO(NO_RESULTS.getMessage()));
    }

    return new InventoryResponse<>(findProduct.get(), utility.createStatusDTO(SUCCESS.getMessage()));
  }

}
