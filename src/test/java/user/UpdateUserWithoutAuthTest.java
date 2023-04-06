package user;

import base.UserAuthenticableTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.stellar.burgers.api.domain.User;

import static org.hamcrest.Matchers.containsString;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class UpdateUserWithoutAuthTest extends UserAuthenticableTest {
    @Before
    @Step("Set empty credentials")
    public void setUp() {
        prepareUser();
        credentials.setEmail("");
        credentials.setPassword("");
        userInfo = new User(credentials.getEmail(), credentials.getPassword(), "username1234");
    }

    @Test
    @DisplayName("Update name for non auth user error")
    public void updateNameNonAuthUserError() {
        userInfo.setName("updated1234");
        userApi.updateUser(credentials, userInfo)
                .then()
                .assertThat()
                .body("message", containsString("You should be authorised"))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Update email for non auth user error")
    public void updateEmailNonAuthUserError() {
        userInfo.setEmail("updated1234");
        userApi.updateUser(credentials, userInfo)
                .then()
                .assertThat()
                .body("message", containsString("You should be authorised"))
                .and()
                .statusCode(SC_UNAUTHORIZED);
    }
}
