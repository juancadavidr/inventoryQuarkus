package org.inventory.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.inventory.entity.Buy;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BuyControllerTest {

  @Test
  void findBuyByIdTest() {
    given()
      .when().get("/buys/1")
      .then()
      .statusCode(200);
  }

  @Test
  void saveBuyTest() {
    given()
      .contentType(ContentType.JSON)
      .body(new Buy())
      .when()
      .post("/buys")
      .then()
      .statusCode(200);
  }
}