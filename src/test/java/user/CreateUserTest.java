package user;

import base.UserAuthenticableTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends UserAuthenticableTest {
    @Before
    public void setUp() {
        prepareUser();
    }

    @Test
    public void createUserSuccessfully() {
        userApi.createUser(userInfo).then().statusCode(200);
    }

    @Test
    public void createUserExistsError() {
        userApi.createUser(userInfo);
        userApi.createUser(userInfo).then().statusCode(403);
    }

    @Test
    public void createUserWithoutOneOfRequiredFieldsError() {
        userInfo.setEmail("");
        userApi.createUser(userInfo).then().statusCode(403);
    }

    @After
    public void deleteUser() {
        loginUser();
        if (userResponse != null) {
            userApi.deleteUser(userResponse);
        }
    }
}