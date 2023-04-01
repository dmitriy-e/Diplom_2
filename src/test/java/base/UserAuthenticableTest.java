package base;

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

    public void prepareUser() {
        credentials = new Credentials("user1234@yandex.ru", "1234");
        userInfo = new User(credentials.getEmail(), credentials.getPassword(), "username1234");
    }

    public void registerUser() {
        Response registerResponse = userApi.createUser(userInfo);
        if (registerResponse.then().extract().statusCode() == 200) {
            userResponse = registerResponse.as(UserResponse.class);
        }
    }

    public void loginUser() {
        Response loginResponse = userApi.login(userInfo.getEmail(), userInfo.getPassword());
        if (loginResponse.then().extract().statusCode() == 200) {
            userResponse = loginResponse.as(UserResponse.class);
        }
    }
}
