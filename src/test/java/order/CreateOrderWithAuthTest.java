package order;

import base.OrderTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderWithAuthTest extends OrderTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
        prepareIngredientsIdHashList();
    }

    @Test
    public void createOrderWithIngredientsSuccess() {
        orderApi.createOrder(ingredientsIdHashList, userResponse).then().assertThat().statusCode(200);
    }

    @Test
    public void createOrderWithoutIngredientsError() {
        ingredientsIdHashList.clear();
        orderApi.createOrder(ingredientsIdHashList, userResponse).then().statusCode(400);
    }

    @Test
    public void createOrderWithInvalidIdHashIngredientsError() {
        ingredientsIdHashList.add("-1");
        orderApi.createOrder(ingredientsIdHashList, userResponse).then().statusCode(500);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser(userResponse);
    }
}
