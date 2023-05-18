package org.inventory.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.inventory.dto.InventoryResponse;
import org.inventory.entity.Buy;
import org.inventory.repository.BuyRepository;
import org.inventory.repository.ProductRepository;
import org.inventory.util.Utils;

import java.time.LocalDateTime;

import static org.inventory.Enums.MessagesEnum.SUCCESS;

@ApplicationScoped
public class BuyLogic {
  @Inject
  private BuyRepository buyRepository;

  @Inject
  private ProductRepository productRepository;

  @Inject
  private Utils utility;

  public InventoryResponse<Buy> makeBuy(Buy buy) {
    StringBuffer message = new StringBuffer();

    try {
      buy.getProducts().stream().forEach(b -> message.append(validateInventory(b.getIdProduct(), b.getQuantity())));

      if (message.length() > 0) {
        return new InventoryResponse<>(null, utility.createStatusDTO(message.toString()));
      }

      saveProducts(buy);
      buy.setDate(LocalDateTime.now());
      var response = buyRepository.save(buy);

      return new InventoryResponse<>(response, utility.createStatusDTO(SUCCESS.getMessage()));
    } catch (Exception e) {
      return new InventoryResponse<>(null, utility.createStatusDTO(e.getMessage()));
    }

  }

  private void saveProducts(Buy buy) {
    buy.getProducts().stream().forEach(b -> {

      var product = productRepository.findById(b.getIdProduct()).get();
      product.setInventory(product.getInventory() - b.getQuantity());
      productRepository.save(product);

    });
  }

  private String validateInventory(Integer productId, Integer quantity) {
    var product = productRepository.findById(productId);

    if (product.isEmpty()) {
      return "No se encontró el producto id : " + productId;
    }

    if (product.get().getInventory() < quantity) {
      return "No hay cantidad suficiente del producto:" + product.get().getName();
    }
    return "";
  }

  public InventoryResponse<Buy> findBuyById(Integer id) {
    var response = buyRepository.findById(id);
    return new InventoryResponse<>(response.get(), utility.createStatusDTO(SUCCESS.getMessage()));
  }
}
