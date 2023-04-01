package order;

import base.OrderTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetOrderWithAuthTest extends OrderTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
    }

    @Test
    public void getAllOrdersSuccessfully() {
        orderApi.getAllOrders(userResponse).then().statusCode(200);
    }

    @Test
    public void getAllOrdersWithoutAccessTokenError() {
        userResponse.setAccessToken("");
        orderApi.getAllOrders(userResponse).then().statusCode(401);
    }

    @After
    public void deleteUser() {
        loginUser();
        if (userResponse != null) {
            userApi.deleteUser(userResponse);
        }
    }
}
