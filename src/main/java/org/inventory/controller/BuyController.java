package org.inventory.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.inventory.dto.InventoryResponse;
import org.inventory.entity.Buy;
import org.inventory.logic.BuyLogic;

@Path("/buys")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuyController {

  @Inject
  private BuyLogic buyLogic;

  @POST
  public InventoryResponse<Buy> saveBuy(Buy buy) {
    return buyLogic.makeBuy(buy);
  }

  @GET
  @Path("/{id}")
  public InventoryResponse<Buy> findBuyByID(@PathParam("id") Integer id) {
    return buyLogic.findBuyById(id);
  }
}
