package com.automation.apiassessment.apitests;

import common.EndPoint;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PostBooks {

String pattern = "yyyy-MM-dd HH:mm:ssZ";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    @Test(groups = "apiassessment")
    public void postBook_PositiveTest200() {
        HashMap<String,Object> requestParams = new HashMap<String,Object>();
        requestParams.put("id",201);
        requestParams.put("title","Book 201");
        requestParams.put("description","Lorem is overused.");
        requestParams.put("pageCount ",0);
        requestParams.put("excerpt","Lorem is overused. Lorem is overused.");
        requestParams.put("publishDate ",simpleDateFormat.format(new Date()));

        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            JSONObject json = new JSONObject();
            json.put("ID", requestParams.get("id"));
            json.put("PageCount ",requestParams.get("pageCount"));
            json.put("Title",requestParams.get("title"));
            json.put("Description",requestParams.get("description"));
            json.put("Excerpt",requestParams.get("excerpt"));
            json.put("PublishDate ",requestParams.get("publishDate"));
            request.body(json.toJSONString());
            Response response = request.post(EndPoint.POST_BOOKS_PARAM);
            Assert.assertEquals(response.getStatusCode(), 200);
        }catch (Exception e) {
            System.out.println("Failed during postBook_PositiveTest200 method. /n" + e.toString());
            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void postBook_PositiveTest_VerifyResponseBody() {
        HashMap<String,Object> requestParams = new HashMap<String,Object>();
        requestParams.put("id",201);
        requestParams.put("title","Book 201");
        requestParams.put("description","Lorem is overused.");
        requestParams.put("pageCount",0);
        requestParams.put("excerpt","Lorem is overused. Lorem is overused.");
        requestParams.put("publishDate ",simpleDateFormat.format(new Date()));

        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            JSONObject json = new JSONObject();
            json.put("ID", requestParams.get("id"));
            json.put("PageCount ",requestParams.get("pageCount"));
            json.put("Title",requestParams.get("title"));
            json.put("Description",requestParams.get("description"));
            json.put("Excerpt",requestParams.get("excerpt"));
            json.put("PublishDate ",requestParams.get("publishDate"));
            request.body(json.toJSONString());

            Response response = request.post(EndPoint.POST_BOOKS_PARAM);

            response.then().assertThat().body("ID", equalTo(requestParams.get("id")))
                    .body("Title", equalTo(requestParams.get("title")))
                    .body("Description", equalTo(requestParams.get("description")))
                    .body("PageCount", equalTo(requestParams.get("pageCount")))
                    .body("Excerpt", equalTo(requestParams.get("excerpt"))).log().all();
        }catch (Exception e) {
            System.out.println("Failed during postBook_PositiveTest_VerifyResponseBody method. /n" + e.toString());
            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void postBook_NegativeTest_VerifyPostingOnlyID() {
        HashMap<String,Object> requestParams = new HashMap<String,Object>();
        requestParams.put("id",202);
        requestParams.put("title",null);
        requestParams.put("description",null);
        requestParams.put("pageCount",0);
        requestParams.put("excerpt",null);
        requestParams.put("publishDate ",simpleDateFormat.format(new Date()));

        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            JSONObject json = new JSONObject();
            json.put("ID", requestParams.get("id"));
            request.body(json.toJSONString());

            Response response = request.post(EndPoint.POST_BOOKS_PARAM);

            response.then().assertThat().body("ID", equalTo(requestParams.get("id")))
                    .body("Title", equalTo(requestParams.get("title")))
                    .body("Description", equalTo(requestParams.get("description")))
                    .body("PageCount", equalTo(requestParams.get("pageCount")))
                    .body("Excerpt", equalTo(requestParams.get("excerpt"))).log().all();
        }catch (Exception e) {
            System.out.println("Failed during postBook_PositiveTest_VerifyResponseBody method. /n" + e.toString());
            throw e;
        }
    }

    @Test(groups = "apiassessment")
    public void postBook_Performance(){
        final Long elapsedTime = 3000L;  //obviously this value would need to be set to something more appropriate but to make sure the test passed I set it high.

        HashMap<String,Object> requestParams = new HashMap<String,Object>();
        requestParams.put("id",201);
        requestParams.put("title","Book 201");
        requestParams.put("description","Lorem is overused.");
        requestParams.put("pageCount ",0);
        requestParams.put("excerpt","Lorem is overused. Lorem is overused.");
        requestParams.put("publishDate ",simpleDateFormat.format(new Date()));

        try {
            RequestSpecification request = new com.automation.apiassessment.framework.Configuration().getRequestSpec(); //sets header content type to JSON
            JSONObject json = new JSONObject();
            json.put("ID", requestParams.get("id"));
            json.put("PageCount ",requestParams.get("pageCount"));
            json.put("Title",requestParams.get("title"));
            json.put("Description",requestParams.get("description"));
            json.put("Excerpt",requestParams.get("excerpt"));
            json.put("PublishDate ",requestParams.get("publishDate"));
            request.body(json.toJSONString());
            Response response = request.post(EndPoint.POST_BOOKS_PARAM);
            response.then().assertThat().time(lessThan(elapsedTime));
        }catch (Exception e) {
            System.out.println("Failed during postBook_Performance method. /n" + e.toString());
            throw e;
        }
    }

    @Test
    public void scenarioTest_PositiveTest_VerifyBookPosted() {
        //ToDo: If the API were not fake I would add tests to a separate class for Scenario Tests to verify the book was created
    }
}
