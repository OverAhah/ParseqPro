package org.ParseqPro.Restassured.generalFactory;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
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
                .contentType(ContentType.JSON);
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

    public static <T> T postRequestWithQueryParam(Class<T> type, String name, String endPointUrl, int statusCode) {
        return requestSpec()
                .when()
                .queryParam("name", name)
                .log().uri()
                .post(endPointUrl)
                .then().log().all()
                .statusCode(statusCode)
                .extract().as(type);
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
}
