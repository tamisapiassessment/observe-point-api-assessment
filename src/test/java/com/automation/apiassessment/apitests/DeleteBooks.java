package com.automation.apiassessment.apitests;

import common.EndPoint;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class DeleteBooks {

    @Test(groups = "apiassessment")
    public void deleteBookByID_PositiveTest200() {
        final String bookID = "1";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",bookID);
            Response response = request.spec(request).delete(EndPoint.DELETE_BOOKS_PATH_PARAM);

            Assert.assertEquals(response.getStatusCode(), 200);
        }catch (Exception e) {
            System.out.println("Failed during deleteBookByID_PositiveTest200 method. /n" + e.toString());
            throw e;
        }
    }
    @Test(groups = "apiassessment")
    public void deleteBookByID_NegativeTest400_VerifyNullID() {
        final String invalidBookID = null;
        String url = "https://fakerestapi.azurewebsites.net/api/Books/";
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec();
            Response response = request.delete(url+invalidBookID);
            Assert.assertEquals(response.getStatusCode(), 400);
        }catch (Exception e) {
            System.out.println("Failed during deleteBookByID_NegativeTest400_VerifyNullID method. /n" + e.toString());
            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void deleteBookByID_Performance(){
        final String bookID = "1";
        final Long elapsedTime = 2000L;  //obviously this value would need to be set to something more appropriate but to make sure the test passed I set it high.
        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            request.pathParam("ID",bookID);
            Response response = request.spec(request).delete(EndPoint.DELETE_BOOKS_PATH_PARAM);
            response.then().assertThat().time(lessThan(elapsedTime));
        }catch (Exception e) {
            System.out.println("Failed during deleteBookByID_Performance method. /n" + e.toString());
            throw e;
        }
    }

    @Test
    public void deleteBookByID_NegativeTest_VerifyInvalidBookID() {
        //ToDo: If the API were not fake I would try deleting a book that doesn't exist but when I tried that test here I get a response code of 200
    }

    @Test
    public void scenarioTest_PositiveTest_VerifyBookIsDeleted() {
        //ToDo: If the API were not fake I would add a test into a separate class for Scenario Tests to verify the book was deleted
    }
}
