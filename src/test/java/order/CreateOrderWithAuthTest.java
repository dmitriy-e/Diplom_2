package order;

import base.OrderTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class CreateOrderWithAuthTest extends OrderTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
        prepareIngredientsIdHashList();
    }

    @Test
    @DisplayName("Check create order with ingredients")
    public void createOrderWithIngredientsSuccess() {
        orderApi.createOrder(ingredientsIdHashList, userResponse)
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Check create without ingredients list")
    public void createOrderWithoutIngredientsError() {
        ingredientsIdHashList.clear();
        orderApi.createOrder(ingredientsIdHashList, userResponse)
                .then()
                .assertThat()
                .body("message", Matchers.containsString("Ingredient ids must be provided"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Check create with ingredient hashes")
    public void createOrderWithInvalidIdHashIngredientsError() {
        ingredientsIdHashList.add("-1");
        orderApi.createOrder(ingredientsIdHashList, userResponse)
                .then()
                .assertThat()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @After
    @Step("Delete user")
    public void deleteUser() {
        userApi.deleteUser(userResponse);
    }
}
