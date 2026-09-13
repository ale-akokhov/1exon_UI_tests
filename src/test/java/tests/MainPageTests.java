package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {

    @Test
    @Tag("smoke")
    @Owner("aakokhov")
    @Feature("Титульное название страницы")
    @Story("Веб адрес страницы")
    @DisplayName("Проверка титульного названия страницы")
    void mainPageTitleTest() {

        step(String.format("Открываем главную страницу сайта '%s'", config.getBaseUrl()), () ->
                open(baseUrl));

        step("Проверяем, что титульное название страницы содержит текст 'Экзон: Управление строительством'", () -> {
            mainPage.checkMainPageTitle();
        });
    }

    @Test
    @Tag("smoke")
    @Owner("aakokhov")
    @Feature("Логи консоли")
    @Story("Ошибки в логе консоли при работе сайта")
    @DisplayName("Проверка лога консоли страницы на наличие ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step(String.format("Открываем главную страницу сайта '%s'", config.getBaseUrl()), () ->
                open(baseUrl));

        step("Проверяем, что лог консоли не содержит текст: 'SEVERE'", () -> {
            mainPage.checkNoErrorsInLogs();
        });
    }

    @Test
    @Tag("smoke")
    @Owner("aakokhov")
    @Feature("Навигационная панель")
    @Story("Видимость текстовых элементов в навигационной панели")
    @DisplayName("Проверка видимости элементов в навигационной панели")
    void checkVisibilityOfHeaderElementsTest() {
        step(String.format("Открываем главную страницу сайта '%s'", config.getBaseUrl()), () ->
                open(baseUrl));

        step("Закрываем попап", () -> {
            mainPage.closePopUp();
        });

        step("Проверяем, что в навигационной панели отображаются текстовые элементы", () ->
                mainPage.checkNavigationItems("Возможности", "О нас", "Кейсы",
                        "Контакты", "Техподдержка", "sale@exon-group.ru", "Заказать звонок"));
    }

    @Test
    @Tag("smoke")
    @Owner("aakokhov")
    @Feature("Навигационная панель")
    @Story("Проверка кликабельности кнопок в навигационной панели")
    @DisplayName("Проверка открытия и содержания модалки при нажатии кнопки 'Заказать звонок'")
    void callbackFormShouldOpenTest() {
        step(String.format("Открываем главную страницу сайта '%s'", config.getBaseUrl()), () ->
                open(baseUrl));

        step("Закрываем попап", () -> {
            mainPage.closePopUp();
        });

        step("Кликаем по кнопке 'Заказать звонок'", () ->
                mainPage.clickCallbackButton());

        step("Проверяем, что модалка открывается, и в ней отображаются все поля формы", () ->
                mainPage.checkCallbackFormVisible());
    }

    @Test
    @Tag("smoke")
    @Owner("aakokhov")
    @Feature("Главная страница")
    @Story("Проверка кликабельности кнопок на главной странице")
    @DisplayName("Проверка открытия формы 'Опросный лист ПО ЭКЗОН'")
    void surveyShouldOpenTest() {
        step(String.format("Открываем главную страницу сайта '%s'", config.getBaseUrl()), () ->
                open(baseUrl));

        step("Закрываем попап", () -> {
            mainPage.closePopUp();
        });

        step("Кликаем по кнопке 'Опросный лист'", () ->
                mainPage.clickSurveyButton());

        step("Проверяем, что открывается Опросный лист ПО ЭКЗОН", () ->
                mainPage.checkSurveyPageName());
    }

}
