package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends WebTestBase {

    @Test
    @Tag("web")
    @Owner("aakokhov")
    @Feature("Титульное название страницы")
    @Story("Веб адрес страницы")
    @DisplayName("Проверка титульного названия страницы")
    void mainPageTitleTest() {

        step(String.format("Открыть главную страницу сайта '%s'", config.getUIBaseUrl()), () ->
                open(config.getUIBaseUrl()));

        step("Проверить титульное название страницы", () -> {
            mainPage.checkMainPageTitle();
        });
    }

    @Test
    @Tag("web")
    @Owner("aakokhov")
    @Feature("Логи консоли")
    @Story("Ошибки в логе консоли при работе сайта")
    @DisplayName("Проверка лога консоли страницы на наличие ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step(String.format("Открыть главную страницу сайта '%s'", config.getUIBaseUrl()), () ->
                open(config.getUIBaseUrl()));

        step("Проверить, что в логах консоли нет SEVERE-ошибок", () -> {
            mainPage.checkNoErrorsInLogs();
        });
    }

    @Test
    @Tag("web")
    @Owner("aakokhov")
    @Feature("Навигационная панель")
    @Story("Видимость текстовых элементов в навигационной панели")
    @DisplayName("Проверка видимости элементов в навигационной панели")
    void checkVisibilityOfHeaderElementsTest() {
        step(String.format("Открыть главную страницу сайта '%s' и закрыть попап",
                config.getUIBaseUrl()), () -> {
            open(config.getUIBaseUrl());
            mainPage.closePopUp();
        });

        step("Проверить, что в навигационной панели отображаются текстовые элементы", () ->
                mainPage.checkNavigationItems());
    }

    @Test
    @Tag("web")
    @Owner("aakokhov")
    @Feature("Навигационная панель")
    @Story("Проверка кликабельности кнопок в навигационной панели")
    @DisplayName("Проверка открытия и содержания модалки при нажатии кнопки 'Заказать звонок'")
    void callbackFormShouldOpenTest() {
        step(String.format("Открыть главную страницу сайта '%s' и закрыть попап",
                config.getUIBaseUrl()), () -> {
            open(config.getUIBaseUrl());
            mainPage.closePopUp();
        });

        step("Нажать на кнопку 'Заказать звонок'. " +
                "Проверить, что открылась модалка со всеми полями", () -> {
            mainPage.clickCallbackButton();
            mainPage.checkCallbackFormVisible();
        });
    }

    @Test
    @Tag("web")
    @Owner("aakokhov")
    @Feature("Главная страница")
    @Story("Проверка кликабельности кнопок на главной странице")
    @DisplayName("Проверка открытия формы 'Опросный лист ПО ЭКЗОН'")
    void surveyShouldOpenTest() {
        step(String.format("Открыть главную страницу сайта '%s' и закрыть попап",
                config.getUIBaseUrl()), () -> {
            open(config.getUIBaseUrl());
            mainPage.closePopUp();
        });

        step("Открыть 'Опросный лист ПО ЭКЗОН' и проверить заголовок страницы", () -> {
            mainPage.clickSurveyButton();
            mainPage.checkSurveyPageName();
        });
    }
}
