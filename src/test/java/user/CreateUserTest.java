package user;

import base.UserAuthenticableTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.apache.http.HttpStatus.*;

public class CreateUserTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
    }

    @Test
    @DisplayName("Create new user successfully")
    public void createUserSuccessfully() {
        userApi.createUser(userInfo)
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Create new user already exists error")
    public void createUserExistsError() {
        userApi.createUser(userInfo);
        userApi.createUser(userInfo)
                .then()
                .assertThat()
                .body("message", containsString("User already exists"))
                .and()
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Create without required fields error")
    public void createUserWithoutOneOfRequiredFieldsError() {
        userInfo.setEmail("");
        userApi.createUser(userInfo)
                .then()
                .assertThat()
                .body("message", containsString("Email, password and name are required fields"))
                .statusCode(SC_FORBIDDEN);
    }

    @After
    @Step("Delete user")
    public void deleteUser() {
        loginUser();
        if (userResponse != null) {
            userApi.deleteUser(userResponse);
        }
    }
}