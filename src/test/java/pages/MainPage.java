package pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tests.TestBase.getConsoleLogs;

public class MainPage {

    public void checkMainPageTitle() {
        String expectedTitle = "Экзон: Управление строительством";
        String actualTitle = title();

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    public void checkNoErrorsInLogs() {
        String consoleLogs = getConsoleLogs();
        String errorText = "SEVERE";

        assertThat(consoleLogs).doesNotContain(errorText);
    }

    public void closePopUp() {
        if ($(".t657").is(Condition.visible, Duration.ofSeconds(2))) {
            $(".t-btnflex__text").click();
        }
    }

    public void clickCallbackButton() {
        $("[data-elem-id='1733405499901']").click(); // Кнопка "Заказать звонок"
    }

    public void checkNavigationItems(String... items) {
        for (String item : items) {
            $x("//*[text()='" + item + "']")
                    .as("Пункт навигации: '" + item + "'")
                    .shouldBe(visible);
        }
    }

    public void checkCallbackFormVisible() {
        $("[data-elem-id='1730899650581']")
                .as("Отображение модалки").shouldBe(visible);
        $("[data-input-lid='1730899631817']")
                .as("Отображение инпута 'Имя'").shouldBe(visible);
        $("[data-input-lid='1730899631819']")
                .as("Отображение инпута 'Номер телефона'").shouldBe(visible);
        $("[data-input-lid='1740738991105']")
                .as("Отображение инпута 'E-mail'").shouldBe(visible);
        $("[data-input-lid='1740739101944']")
                .as("Отображение инпута 'Название компании'").shouldBe(visible);
        $("[data-input-lid='1740739289634']")
                .as("Отображение инпута 'ИНН'").shouldBe(visible);
        $("[data-input-lid='1732612519749']")
                .as("Отображение согласия с политикой и на обработку").shouldBe(visible);
        $("[data-input-lid='1732612558711']")
                .as("Отображение согласия на рассылку").shouldBe(visible);
        $(".t-submit")
                .as("Отображение кнопки 'Отправить'").shouldBe(visible);
    }

    public void clickSurveyButton() {
        $("[data-elem-id='1784642561033000001']").click(); // Кнопка "Опросный лист"
    }

    public void checkSurveyPageName() {
        $(".SurveyPage-Name").shouldHave(text("Опросный лист ПО ЭКЗОН"));
    }

}