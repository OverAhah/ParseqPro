package apiTest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.ParseqPro.Restassured.generalFactory.RequestFactory.deleteRequestWithPath;
import static org.ParseqPro.Restassured.generalFactory.RequestFactory.postRequestWithQueryParam;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParseqProCreateListsTests {

    private static final String POST_END_POINT = "https://testapi2.parseq.pro/lists/create";

    private static final String PATCH_END_POINT = "https://testapi2.parseq.pro/lists/";

    @BeforeAll
    public static void setup() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @ParameterizedTest
    @MethodSource("generateValidData")
    @DisplayName("Delete list by name")
    @Order(1)
    void testDeleteLists(String name) {
        deleteRequestWithPath(PATCH_END_POINT, name, 204);
    }

    @ParameterizedTest
    @MethodSource("generateValidData")
    //@Disabled("Error 500")
    @DisplayName("Create new list")
    @Order(2)
    void testCreateLists(String name) {
        postRequestWithQueryParam(name, POST_END_POINT, 201);
    }

    @ParameterizedTest
    @MethodSource("generateInvalidData")
    @DisplayName("Delete list by name")
    @Order(3)
    void testDeleteListsWithInvalidData(String name) {
        deleteRequestWithPath(PATCH_END_POINT, name, 204);
    }

    @ParameterizedTest
    @MethodSource("generateInvalidData")
    //@Disabled("Error 500")
    @DisplayName("Create new list")
    @Order(4)
    void testCreateListsWithInvalidData(String name) {
        postRequestWithQueryParam(name, POST_END_POINT, 201);
    }

    public static Stream<Arguments> generateValidData() {
        return Stream.of(
                Arguments.of("1"),
                Arguments.of("2"),
                Arguments.of("3"),
                Arguments.of("one"),
                Arguments.of("two"));
    }

    public static Stream<Arguments> generateInvalidData() {
        return Stream.of(
                Arguments.of("ThisIsDefinitelyNotTheLongestInscriptionThatCouldBeHereButTtIsStillHereToCheckTheMaximumNumberOfCharacters"),
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of(1));
    }
}