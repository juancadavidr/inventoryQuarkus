package org.inventory.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.inventory.entity.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ProductControllerTest {

  @Test
  void saveProduct() {
    given()
      .contentType(ContentType.JSON)
      .body(new Product())
      .when()
      .post("/products")
      .then()
      .statusCode(200);
  }

  @Test
  void updateProduct() {
    given()
      .contentType(ContentType.JSON)
      .body(new Product())
      .when()
      .put("/products")
      .then()
      .statusCode(200);
  }

  @Test
  void findProductByID() {
    given()
      .when().get("/products/1")
      .then()
      .statusCode(200);
  }

  @Test
  void deleteProduct() {
    given()
      .contentType(ContentType.JSON)
      .body(new Product())
      .when()
      .delete("/products")
      .then()
      .statusCode(200);
  }
}