package org.stellar.burgers.api.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.stellar.burgers.api.domain.Credentials;
import org.stellar.burgers.api.domain.User;
import org.stellar.burgers.api.response.UserResponse;

import static io.restassured.RestAssured.given;
import static org.stellar.burgers.api.routes.UserRoutes.*;

public class UserApi extends BaseApi {
    @Step("Send POST create user request to /auth/register")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(REGISTER_ROUTE);
    }

    @Step("Send POST login user request to /auth/login")
    public Response login(String email, String password) {
        Credentials credentials = new Credentials(email, password);

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(credentials)
                .post(LOGIN_ROUTE);
    }

    @Step("Get accessToken for authenticated user")
    public String getAccessTokenAuthenticatedUser(Credentials credentials) {
        return login(credentials.getEmail(), credentials.getPassword()).then().extract().body().path("accessToken");
    }

    @Step("Update request to auth/user")
    public Response updateUser(Credentials credentials, User userInfo) {
        String accessToken = getAccessTokenAuthenticatedUser(credentials);

        if (accessToken != null) {
            return given()
                    .header("Content-type", "application/json")
                    .header("authorization", accessToken)
                    .and().body(userInfo)
                    .patch(USER_ROUTE);
        }

        return given()
                .header("Content-type", "application/json")
                .and().body(userInfo)
                .patch(USER_ROUTE);
    }

    @Step("Delete request to auth/user")
    public void deleteUser(UserResponse userResponse) {
        given()
                .header("Content-type", "application/json")
                .header("authorization", userResponse.getAccessToken())
                .delete(USER_ROUTE);
    }
}