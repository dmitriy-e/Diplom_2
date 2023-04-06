package order;

import base.OrderTest;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class CreateOrderWithoutAuthTest extends OrderTest {
    @Before
    public void setUp() {
        prepareIngredientsIdHashList();
    }

    @Test
    @DisplayName("Create order with ingredients success")
    public void createOrderWithIngredientsSuccess() {
        orderApi.createOrder(ingredientsIdHashList)
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Create order without ingredients error")
    public void createOrderWithoutIngredientsError() {
        ingredientsIdHashList.clear();
        orderApi.createOrder(ingredientsIdHashList)
                .then()
                .assertThat()
                .body("message", Matchers.containsString("Ingredient ids must be provided"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Create order with invalid id hash ingredients error")
    public void createOrderWithInvalidIdHashIngredientsError() {
        ingredientsIdHashList.add("-1");
        orderApi.createOrder(ingredientsIdHashList)
                .then()
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
