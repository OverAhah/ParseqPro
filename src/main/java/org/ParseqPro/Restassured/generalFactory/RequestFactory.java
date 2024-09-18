package org.ParseqPro.Restassured.generalFactory;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestFactory {

    private static final Logger log = LoggerFactory.getLogger(RequestFactory.class);

    public static RequestSpecification requestSpec() {
        return given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured().setRequestTemplate("http-request.ftl").setResponseTemplate("http-response.ftl"));
    }

    public static <T> T getRequestWithQueryParam(Class<T> type, int pageZeroBasedNumber, int pageSize, String endPointUrl, int statusCode) {
        return requestSpec()
                .when()
                .queryParams("pageZeroBasedNumber", pageZeroBasedNumber,"pageSize", pageSize)
                .get(endPointUrl)
                .then().log().all()
                .statusCode(statusCode)
                .extract().as(type);
    }

    public static <T> List<T> getRequestList(Class<T[]> type, String endPointUrl, int statusCode) {
        return Arrays.asList(requestSpec()
                .when()
                .get(endPointUrl)
                .then().log().all()
                .statusCode(statusCode)
                .extract().as(type));
    }

    public static Response postRequestWithQueryParam(String name, String endPointUrl, int statusCode) {
        return requestSpec()
                .when()
                .queryParam("name", name)
                .log().uri()
                .post(endPointUrl)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().response();
    }


    public static void patchRequestWithBody(Object body, String endPointUrl, String path, String endPointUrlEnd, int statusCode) {
        requestSpec()
                .when()
                .body(body)
                .log().uri()
                .patch(endPointUrl + path + endPointUrlEnd)
                .then().log().all()
                .statusCode(statusCode);
    }

    public static void deleteRequestWithPath(String endPoint, String path, int statusCode) {
        requestSpec()
                .when()
                .log().uri()
                .delete(endPoint + path)
                .then().log().all()
                .statusCode(statusCode);
    }

    public static void getRequestWithQueryParamFor500(int pageZeroBasedNumber, int pageSize, String endPointUrl, int statusCode) {
        requestSpec()
                .when()
                .queryParams("pageZeroBasedNumber", pageZeroBasedNumber,"pageSize", pageSize)
                .get(endPointUrl)
                .then().log().all()
                .statusCode(statusCode);
    }

    public static Response postRequestWithQueryParamMethod(String method, String name, String endPointUrl, int statusCode) {
        switch (method) {
            case "GET" -> {
                return requestSpec()
                        .when()
                        .queryParam("name", name)
                        .log().uri()
                        .get(endPointUrl)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().response();
            }
            case "PUT" -> {
                return requestSpec()
                        .when()
                        .queryParam("name", name)
                        .log().uri()
                        .put(endPointUrl)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().response();
            }
            case "PATCH" -> {
                return requestSpec()
                        .when()
                        .queryParam("name", name)
                        .log().uri()
                        .patch(endPointUrl)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().response();
            }
            case "DELETE" -> {
                return requestSpec()
                        .when()
                        .queryParam("name", name)
                        .log().uri()
                        .delete(endPointUrl)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().response();
            }
        }
        return null;
    }

    public static void deleteRequestWithPathMethod(String method, String endPoint, String path, int statusCode) {
        switch (method) {
            case "GET" -> {
                requestSpec()
                        .when()
                        .log().uri()
                        .get(endPoint + path)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "POST" -> {
                requestSpec()
                        .when()
                        .log().uri()
                        .post(endPoint + path)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "PUT" -> {
                requestSpec()
                        .when()
                        .log().uri()
                        .put(endPoint + path)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "PATCH" -> {
                requestSpec()
                        .when()
                        .log().uri()
                        .patch(endPoint + path)
                        .then().log().all()
                        .statusCode(statusCode);
            }
        }
    }

    public static void patchRequestWithBodyMethod(String method,Object body, String endPointUrl, String path, String endPointUrlEnd, int statusCode) {
        switch (method) {
            case "GET" -> {
                requestSpec()
                        .when()
                        .body(body)
                        .log().uri()
                        .get(endPointUrl + path + endPointUrlEnd)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "POST" -> {
                requestSpec()
                        .when()
                        .body(body)
                        .log().uri()
                        .post(endPointUrl + path + endPointUrlEnd)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "PUT" -> {
                requestSpec()
                        .when()
                        .body(body)
                        .log().uri()
                        .put(endPointUrl + path + endPointUrlEnd)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "DELETE" -> {
                requestSpec()
                        .when()
                        .body(body)
                        .log().uri()
                        .delete(endPointUrl + path + endPointUrlEnd)
                        .then().log().all()
                        .statusCode(statusCode);
            }
        }
    }

    public static void getRequestWithQueryParamMethod(String method, int pageZeroBasedNumber, int pageSize, String endPointUrl, int statusCode) {
        switch (method) {
            case "POST" -> {
                requestSpec()
                        .when()
                        .queryParams("pageZeroBasedNumber", pageZeroBasedNumber,"pageSize", pageSize)
                        .post(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "PUT" -> {
                requestSpec()
                        .when()
                        .queryParams("pageZeroBasedNumber", pageZeroBasedNumber,"pageSize", pageSize)
                        .put(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "PATCH" -> {
                requestSpec()
                        .when()
                        .queryParams("pageZeroBasedNumber", pageZeroBasedNumber,"pageSize", pageSize)
                        .patch(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode);
            }
            case "DELETE" -> {
                requestSpec()
                        .when()
                        .queryParams("pageZeroBasedNumber", pageZeroBasedNumber,"pageSize", pageSize)
                        .delete(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode);
            }
        }
    }

    public static void getRequestListMethod(String method, String endPointUrl, int statusCode) {
        switch (method) {
            case "POST" -> {
                Arrays.asList(requestSpec()
                        .when()
                        .post(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode));
            }
            case "PUT" -> {
                Arrays.asList(requestSpec()
                        .when()
                        .put(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode));
            }
            case "PATCH" -> {
                Arrays.asList(requestSpec()
                        .when()
                        .patch(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode));
            }
            case "DELETE" -> {
                Arrays.asList(requestSpec()
                        .when()
                        .delete(endPointUrl)
                        .then().log().all()
                        .statusCode(statusCode));
            }
        }
    }
}