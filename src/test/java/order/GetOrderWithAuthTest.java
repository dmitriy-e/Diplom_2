package order;

import base.OrderTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class GetOrderWithAuthTest extends OrderTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
    }

    @Test
    @DisplayName("Get orders successfully")
    public void getAllOrdersSuccessfully() {
        orderApi.getAllOrders(userResponse)
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Get orders without token error")
    public void getAllOrdersWithoutAccessTokenError() {
        userResponse.setAccessToken("");
        orderApi.getAllOrders(userResponse)
                .then()
                .assertThat()
                .body("message", containsString("You should be authorised"))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    @Step("Delete user")
    public void deleteUser() {
        loginUser();
        if (userResponse != null) {
            userApi.deleteUser(userResponse);
        }
    }
}
