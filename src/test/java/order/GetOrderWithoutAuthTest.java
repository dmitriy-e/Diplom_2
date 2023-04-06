package order;

import base.OrderTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.SC_OK;

public class GetOrderWithoutAuthTest extends OrderTest {
    @Test
    @DisplayName("Get orders successfully")
    public void getAllOrdersSuccessfully() {
        orderApi.getAllOrders()
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }
}
