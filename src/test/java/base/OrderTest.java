package base;

import io.qameta.allure.Step;
import org.stellar.burgers.api.api.IngredientApi;
import org.stellar.burgers.api.api.OrderApi;
import org.stellar.burgers.api.response.IngredientResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderTest extends UserAuthenticableTest {
    private IngredientApi ingredientApi;
    protected OrderApi orderApi;
    protected List<String> ingredientsIdHashList = new ArrayList<>();

    public OrderTest() {
        orderApi = new OrderApi();
    }

    @Step("Prepare ingredients hash list")
    public void prepareIngredientsIdHashList() {
        ingredientApi = new IngredientApi();
        ingredientApi.retrieveIngredientsInfo().getBody().as(IngredientResponse.class).getData().forEach(el -> ingredientsIdHashList.add(el.get_id()));
    }
}
