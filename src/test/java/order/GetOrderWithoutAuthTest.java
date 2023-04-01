package order;

import base.OrderTest;
import org.junit.Test;

public class GetOrderWithoutAuthTest extends OrderTest {
    @Test
    public void getAllOrdersSuccessfully() {
        orderApi.getAllOrders().then().statusCode(200);
    }
}
