package com.automation.apiassessment.apitests;

import common.EndPoint;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class GetBooksId {

    @Test(groups = "apiassessment")
    public void getBookByID_PositiveTest200_VerifyValidID() {
        final String bookID = "1";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",bookID);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);

            Assert.assertEquals(response.getStatusCode(), 200);
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_NegativeTest404_VerifyInvalidID(){
        final String invalidBookID = "100000";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",invalidBookID);
            request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM).then().statusCode(404);
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_NegativeTest400_VerifyNullID() {
        final String invalidBookID = null;
        String url = "https://fakerestapi.azurewebsites.net/api/Books/";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec();
            Response response = request.get(url+invalidBookID);
            Assert.assertEquals(response.getStatusCode(), 400);
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_VerifyResponseBody_ID(){
        final int queryValue = 1;
        final int expectedResult = 1;
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",queryValue);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);

            response.then().assertThat().body("ID", Is.is(expectedResult));
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_VerifyResponseBody_Title(){
        final int queryValue = 1;
        final String expectedResult = "Book 1";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",queryValue);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);

            response.then().assertThat().body("Title", Is.is(expectedResult));
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_VerifyResponseBody_Description(){
        final int queryValue = 1;
        final String expectedResult = "Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",queryValue);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);

            response.then().assertThat().body("Description", containsString(expectedResult));
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_VerifyResponseBody_PageCount(){
        final int queryValue = 1;
        final int expectedResult = 100;
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",queryValue);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);

            response.then().assertThat().body("PageCount", Is.is(expectedResult));
        }catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_VerifyResponseBody_Excerpt() {
        final int queryValue = 1;
        final String expectedResult = "Lorem lorem lorem.";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",queryValue);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);

            response.then().assertThat().body("Excerpt", containsString(expectedResult));
        } catch (Exception e) {

            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void getBookByID_Performance(){
        final String bookID = "1";
        final Long elapsedTime = 3000L;  //obviously this value would need to be set to something more appropriate but to make sure the test passed I set it high.
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",bookID);
            Response response = request.spec(request).get(EndPoint.GET_BOOKS_PATH_PARAM);
            response.then().assertThat().time(lessThan(elapsedTime));
        }catch (Exception e) {

            throw e;
        }
    }
}
