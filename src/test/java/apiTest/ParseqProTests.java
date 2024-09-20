package apiTest;

import io.qameta.allure.Owner;
import org.ParseqPro.enteties.Response.ResponseData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestList;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestListMethod;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestWithQueryParam;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestWithQueryParamFor500;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.getRequestWithQueryParamMethod;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.patchRequestWithBody;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.patchRequestWithBodyMethod;
import static org.junit.jupiter.api.Assertions.*;

@Tag("regress")
@DisplayName("Полученеи листов, Получение мутаций из списка, Изменение мутаций в списке")
@Owner("Artyom Kozak")
public class ParseqProTests {

    private static final String GET_END_POINT = "https://testapi2.parseq.pro/lists";

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
    //@Disabled("Error 500")
    @DisplayName("Replace mutations in list with mutations from body")
    void testReplaceMutation() {
        List<String> requestBody = Arrays.asList("string1");

        patchRequestWithBody(requestBody, PATCH_END_POINT, "name", END_OF_ENDPOINT, 200);
    }

    @ParameterizedTest
    @MethodSource("generateValidData")
    @DisplayName("Get list of all known mutations with annotations")
    void testGetAllMutations(int pageZeroBasedNumber, int pageSize) {
        ResponseData responseData = getRequestWithQueryParam(ResponseData.class, pageZeroBasedNumber, pageSize, MUTATIONS_END_PONT, 200);

        assertAll("Поле Resources не должно быть пустое",
                () -> assertNotNull(responseData.getResources().getFirst().getMutationId(), "Resources should not be empty"));
    }

    @ParameterizedTest
    @MethodSource("generateInvalidData")
    @DisplayName("Invalid data - Get list of all known mutations with annotations")
    void testGetAllMutationsWithInvalidData(int pageZeroBasedNumber, int pageSize) {
        getRequestWithQueryParamFor500(pageZeroBasedNumber, pageSize, MUTATIONS_END_PONT, 500);
    }

    @ParameterizedTest
    @MethodSource("methodsPatch")
    @DisplayName("Wrong methods - Replace mutations in list with mutations from body")
    void testReplaceMutationWithWrongMethod(String method) {
        List<String> requestBody = Arrays.asList("string1");

        patchRequestWithBodyMethod(method, requestBody, PATCH_END_POINT, "name", END_OF_ENDPOINT, 404);
    }

    @ParameterizedTest
    @MethodSource("methods")
    @DisplayName("Wrong method - Get list of all known mutations with annotations")
    void testGetAllMutationsWithWrongMethod(String method) {
        getRequestWithQueryParamMethod(method, 1, 1, MUTATIONS_END_PONT, 405);
    }

    @ParameterizedTest
    @MethodSource("methods")
    @DisplayName("Wrong method - Get Lists")
    void testGetListsWithWrongMethod(String method) {
        getRequestListMethod(method, GET_END_POINT, 405);
    }

    public static Stream<Arguments> generateValidData() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 0),
                Arguments.of(0, 1),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(10, 10),
                Arguments.of(10, 0));
    }

    public static Stream<Arguments> generateInvalidData() {
        return Stream.of(
                Arguments.of(-1, 0),
                Arguments.of(-1, -1),
                Arguments.of(0, -1),
                Arguments.of("one", 1),
                Arguments.of(1, "one"),
                Arguments.of(null, 1),
                Arguments.of(1, null)
        );
    }

    public static Stream<Arguments> methodsPatch() {
        return Stream.of(
                Arguments.of("GET"),
                Arguments.of("POST"),
                Arguments.of("PUT"),
                Arguments.of("DELETE"));
    }

    public static Stream<Arguments> methods() {
        return Stream.of(
                Arguments.of("POST"),
                Arguments.of("PUT"),
                Arguments.of("PATCH"),
                Arguments.of("DELETE"));
    }
}