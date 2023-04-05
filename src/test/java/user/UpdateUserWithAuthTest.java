package user;

import base.UserAuthenticableTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

public class UpdateUserWithAuthTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
    }

    @Test
    @DisplayName("Update name authenticated user successfully")
    public void updateNameAuthUserSuccessfully() {
        userInfo.setName("updated12345");
        userApi.updateUser(credentials, userInfo)
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Update email authenticated user successfully")
    public void updateEmailAuthUserSuccessfully() {
        userInfo.setEmail("updated12345");
        userApi.updateUser(credentials, userInfo)
                .then()
                .assertThat()
                .body("success", is(true))
                .and()
                .statusCode(SC_OK);
    }

    @After
    @Step("Delete user")
    public void deleteUser() {
        userApi.deleteUser(userResponse);
    }
}
