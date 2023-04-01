package user;

import base.UserAuthenticableTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdateUserWithAuthTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
        registerUser();
    }

    @Test
    public void updateNameAuthUserSuccessfully() {
        userInfo.setName("updated12345");
        userApi.updateUser(credentials, userInfo).then().statusCode(200);
    }

    @Test
    public void updateEmailAuthUserSuccessfully() {
        userInfo.setEmail("updated12345");
        userApi.updateUser(credentials, userInfo).then().statusCode(200);
    }

    @After
    public void deleteUser() {
        userApi.deleteUser(userResponse);
    }
}
