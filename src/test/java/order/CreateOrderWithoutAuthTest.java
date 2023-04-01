package order;

import base.OrderTest;
import org.junit.Before;
import org.junit.Test;

public class CreateOrderWithoutAuthTest extends OrderTest {
    @Before
    public void setUp() {
        prepareIngredientsIdHashList();
    }

    @Test
    public void createOrderWithIngredientsSuccess() {
        orderApi.createOrder(ingredientsIdHashList).then().assertThat().statusCode(200);
    }

    @Test
    public void createOrderWithoutIngredientsError() {
        ingredientsIdHashList.clear();
        orderApi.createOrder(ingredientsIdHashList).then().statusCode(400);
    }

    @Test
    public void createOrderWithInvalidIdHashIngredientsError() {
        ingredientsIdHashList.add("-1");
        orderApi.createOrder(ingredientsIdHashList).then().statusCode(500);
    }
}
