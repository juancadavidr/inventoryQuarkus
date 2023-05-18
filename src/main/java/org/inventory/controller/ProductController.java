package org.inventory.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.inventory.dto.InventoryResponse;
import org.inventory.entity.Product;
import org.inventory.logic.ProductLogic;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

  @Inject
  private ProductLogic productLogic;

  @POST
  public InventoryResponse<Product> saveProduct(Product product) {
    return productLogic.saveProduct(product);
  }

  @PUT
  public InventoryResponse<Product> updateProduct(Product product) {
    return productLogic.updateProduct(product);
  }

  @GET
  @Path("/{id}")
  public InventoryResponse<Product> findProductByID(@PathParam("id") Integer id) {
    return productLogic.findProductById(id);
  }

  @DELETE
  public InventoryResponse<Product> deleteProduct(Product product){
    return productLogic.deleteProduct(product);
  }
}
