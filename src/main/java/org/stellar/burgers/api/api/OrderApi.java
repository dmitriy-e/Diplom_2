package org.stellar.burgers.api.api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.stellar.burgers.api.response.UserResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.stellar.burgers.api.routes.OrderRoutes.ALL_ROUTE;
import static org.stellar.burgers.api.routes.OrderRoutes.BASIC_ROUTE;

public class OrderApi extends BaseApi {
    @Step("Send POST create order request to /orders")
    public Response createOrder(List<String> ingredientsIdHashList) {
        Gson gson = new Gson();

        gson.toJson(ingredientsIdHashList);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(String.format("{\"ingredients\": %s}", gson.toJson(ingredientsIdHashList)))
                .post(BASIC_ROUTE);
    }

    @Step("Send POST create order request to /orders")
    public Response createOrder(List<String> ingredientsIdHashList, UserResponse userResponse) {
        Gson gson = new Gson();

        gson.toJson(ingredientsIdHashList);
        return given()
                .header("Content-type", "application/json")
                .header("authorization", userResponse.getAccessToken())
                .and()
                .body(String.format("{\"ingredients\": %s}", gson.toJson(ingredientsIdHashList)))
                .post(BASIC_ROUTE);
    }

    @Step("Send GET request /orders/all to retrieve all orders")
    public Response getAllOrders() {
        return given()
                .header("Content-type", "application/json")
                .get(ALL_ROUTE);
    }

    @Step("Send GET request /orders to retrieve all orders")
    public Response getAllOrders(UserResponse userResponse) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", userResponse.getAccessToken())
                .get(BASIC_ROUTE);
    }
}
