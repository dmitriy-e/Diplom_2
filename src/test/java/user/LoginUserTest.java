package user;

import base.UserAuthenticableTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class LoginUserTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
    }

    @Test
    @DisplayName("Login successfully")
    public void loginSuccessfully() {
        userApi.login(credentials.getEmail(), credentials.getPassword())
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Login with invalid login and password fails")
    public void loginWithInvalidLoginAndPasswordError() {
        userApi.login("invalidEmail", "invalidPassword")
                .then()
                .assertThat()
                .body("message", containsString("email or password are incorrect"))
                .statusCode(SC_UNAUTHORIZED);
    }

    @After
    @Step("Delete user")
    public void deleteUser() {
        userApi.deleteUser(userResponse);
    }
}