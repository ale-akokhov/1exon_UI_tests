package tests.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class UserSpecs {
    public static RequestSpecification userRequestSpec = with()
//            .baseUri("https://reqres.in")
            .header("x-api-key", "free_user_3HEWO04M9ZkzbAYcEuV4djhoXDn")
            .basePath("/api")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification userResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("data.id", notNullValue())
            .build();
}