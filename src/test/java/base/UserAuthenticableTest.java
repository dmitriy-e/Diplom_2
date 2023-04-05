package base;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.stellar.burgers.api.api.UserApi;
import org.stellar.burgers.api.domain.Credentials;
import org.stellar.burgers.api.domain.User;
import org.stellar.burgers.api.response.UserResponse;

public class UserAuthenticableTest extends BaseTest {
    protected UserApi userApi;
    protected Credentials credentials;
    protected User userInfo;
    protected UserResponse userResponse;

    public UserAuthenticableTest() {
        userApi = new UserApi();
    }

    @Step("Prepare user credentials")
    public void prepareUser() {
        String username = faker.name().username();
        credentials = new Credentials(username + "@yandex.ru", faker.internet().password());
        userInfo = new User(credentials.getEmail(), credentials.getPassword(), username);
    }

    @Step("Register new user")
    public void registerUser() {
        Response registerResponse = userApi.createUser(userInfo);
        if (registerResponse.then().extract().statusCode() == 200) {
            userResponse = registerResponse.as(UserResponse.class);
        }
    }

    @Step("Login user")
    public void loginUser() {
        Response loginResponse = userApi.login(userInfo.getEmail(), userInfo.getPassword());
        if (loginResponse.then().extract().statusCode() == 200) {
            userResponse = loginResponse.as(UserResponse.class);
        }
    }
}
