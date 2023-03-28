package org.stellar.burgers.api.api;

import io.restassured.RestAssured;

import static org.stellar.burgers.api.constants.Constants.BASE_URI;


public class BaseApi {
    public BaseApi() {
        setupRequestSpecification();
    }

    public void setupRequestSpecification() {
        RestAssured.baseURI = BASE_URI;
    }
}