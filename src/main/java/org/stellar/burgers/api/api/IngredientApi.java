package org.stellar.burgers.api.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.stellar.burgers.api.routes.IngredientRoutes.BASIC_ROUTE;

public class IngredientApi extends BaseApi {
    public Response retrieveIngredientsInfo() {
        return given()
                .header("Content-type", "application/json")
                .get(BASIC_ROUTE);
    }
}