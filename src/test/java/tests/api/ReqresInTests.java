package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.models.LoginBodyLombokModel;
import tests.api.models.LoginResponseLombokModel;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.specs.LoginSpecs.loginRequestSpec;
import static tests.api.specs.LoginSpecs.loginResponseSpec;


public class ReqresInTests extends ApiTestBase {

    @Test
    @Tag("api")
    @Owner("aakokhov")
    @Feature("Авторизация")
    @Story("Успешная авторизация")
    @DisplayName("Проверка успешной авторизации пользователя")
    void loginWithSpecsTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract()
                .as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}