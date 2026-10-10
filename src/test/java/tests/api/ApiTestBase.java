package tests.api;

import config.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {

    protected static final ProjectConfig config =
            ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @BeforeAll
    static void configureApi() {
        RestAssured.baseURI = config.getApiBaseUrl();
    }
}