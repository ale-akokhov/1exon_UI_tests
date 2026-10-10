package tests.api;

import config.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class ApiTestBase {

    protected static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    protected static RequestSpecification requestSpec;

    @BeforeAll
    static void configureApi() {
        RestAssured.baseURI = config.getApiBaseUrl();

        requestSpec = given()
                .header("x-api-key", config.getApiKey())
                .contentType("application/json")
                .log().all();
    }
}