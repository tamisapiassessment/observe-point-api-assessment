package com.automation.apiassessment.framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class Configuration {

    @BeforeSuite(alwaysRun = true)
    public void configure() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";
        RestAssured.basePath = "/api";
    }

    public RequestSpecification getRequestSpec(){
        return RestAssured.given().contentType(ContentType.JSON);
    }
}
