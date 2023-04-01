package user;

import base.UserAuthenticableTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
    }

    @Test
    public void loginSuccessfully() {
        userApi.login(credentials.getEmail(), credentials.getPassword()).then().statusCode(200);
    }

    @Test
    public void loginWithInvalidLoginAndPasswordError() {
        userApi.login("invalidEmail", "invalidPassword").then().statusCode(401);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser(userResponse);
    }
}