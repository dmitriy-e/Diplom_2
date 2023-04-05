package base;

import com.github.javafaker.Faker;

public class BaseTest {
    protected Faker faker;

    public BaseTest() {
        faker = new Faker();
    }
}
