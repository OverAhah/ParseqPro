package apiTest;

import io.qameta.allure.Owner;
import org.ParseqPro.enteties.Response.ResponseData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.ParseqPro.Restassured.generalFactory.RequestFactory.deleteRequestWithPath;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestList;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestWithQueryParam;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.patchRequestWithBody;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.postRequestWithQueryParam;
import static org.junit.jupiter.api.Assertions.*;

@Owner("Artyom Kozak")
public class ParseqProTests {

    private static final String GET_END_POINT = "https://testapi2.parseq.pro/lists";

    private static final String POST_END_POINT = "https://testapi2.parseq.pro/lists/create";

    private static final String PATCH_END_POINT = "https://testapi2.parseq.pro/lists/";

    private static final String MUTATIONS_END_PONT = "https://testapi2.parseq.pro/mutations";

    private static final String END_OF_ENDPOINT = "/mutations";

    @Test
    @DisplayName("Get Lists")
    void testGetLists() {
        List<ResponseData.GetLists> responseData = getRequestList(ResponseData.GetLists[].class, GET_END_POINT, 200);

        assertAll(
                () -> assertNotNull(responseData, "ResponseData cannot be null"),
                () -> assertFalse(responseData.isEmpty(), "responseData should not be empty"),
                () -> assertNotNull(responseData.get(0).getName(), "name cannot be null"),
                () -> assertNotNull(responseData.get(0).getMutations(), "mutations cannot be null")
        );
    }

    @Test
    @Disabled("Error 500")
    @DisplayName("Create new list")
    void testCreateLists() {
        ResponseData responseData = postRequestWithQueryParam(ResponseData.class, "1",POST_END_POINT, 201);

        assertAll(
                () -> assertNull(responseData, "ResponseData must be null"));
    }

    @Test
    @Disabled("Error 500")
    @DisplayName("Replace mutations in list with mutations from body")
    void testReplaceMutation() {
        List<String> requestBody = Arrays.asList("string1");

        patchRequestWithBody(requestBody, PATCH_END_POINT, "name", END_OF_ENDPOINT, 200);
    }

    @Test
    @DisplayName("Delete list by name")
    void testDeleteLists() {
        deleteRequestWithPath(PATCH_END_POINT, "name", 204);
    }

    @Test
    @DisplayName("Get list of all known mutations with annotations")
    void testGetAllMutations() {
        ResponseData responseData = getRequestWithQueryParam(ResponseData.class, 0, 10, MUTATIONS_END_PONT, 200);

        assertAll("Все поля должны быть не пусты",
                () -> assertNotNull(responseData.getResources().getFirst().getMutationId(), "Resources should not be empty"));
    }
}