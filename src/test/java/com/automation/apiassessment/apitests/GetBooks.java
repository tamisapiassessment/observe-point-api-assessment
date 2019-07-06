package com.automation.apiassessment.apitests;

import common.EndPoint;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasSize;

public class GetBooks {

    @Test(groups = "apiassessment")
    public void getBooks_PositiveTest200() {
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            Response response = request.get(EndPoint.GET_BOOKS);
            Assert.assertEquals(response.getStatusCode(), 200);
        }catch (Exception e) {
            System.out.println("Failed during getBooks_PositiveTest200 method. /n" + e.toString());
            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBooks_PositiveTest_VerifyResponseCount() {
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            Response response = request.get(EndPoint.GET_BOOKS);
            response.then().assertThat().body("ID", hasSize(200));
        }catch (Exception e) {
            System.out.println("Failed during getBooks_PositiveTest_VerifyResponseCount method. /n" + e.toString());
            throw e;
        }
    }

}
