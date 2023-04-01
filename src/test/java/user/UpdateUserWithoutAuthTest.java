package user;

import base.UserAuthenticableTest;
import org.junit.Before;
import org.junit.Test;
import org.stellar.burgers.api.domain.User;

public class UpdateUserWithoutAuthTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
        credentials.setEmail("");
        credentials.setPassword("");
        userInfo = new User(credentials.getEmail(), credentials.getPassword(), "username1234");
    }

    @Test
    public void updateNameNonAuthUserError() {
        userInfo.setName("updated1234");
        userApi.updateUser(credentials, userInfo).then().statusCode(401);
    }

    @Test
    public void updateEmailNonAuthUserError() {
        userInfo.setEmail("updated1234");
        userApi.updateUser(credentials, userInfo).then().statusCode(401);
    }
}
