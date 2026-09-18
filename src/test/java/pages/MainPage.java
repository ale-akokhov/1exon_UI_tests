package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.assertj.core.api.SoftAssertions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tests.TestBase.getConsoleLogs;

public class MainPage {

    private final SelenideElement cookiePopup = $(".t657")
            .as("Попап с куки");
    private final SelenideElement cookieAccept = $(".t-btnflex__text")
            .as("Кнопка 'Ok' в попапе с куки");
    private final SelenideElement callbackButton = $("[data-elem-id='1733405499901']")
            .as("Кнопка 'Заказать звонок'");
    private final List<SelenideElement> callbackFormFields = List.of(
            $("[data-elem-id='1730899650581']").as("Модалка обратной связи"),
            $("[data-input-lid='1730899631817']").as("Инпут 'Имя'"),
            $("[data-input-lid='1730899631819']").as("Инпут 'Телефон'"),
            $("[data-input-lid='1740738991105']").as("Инпут 'E-mail'"),
            $("[data-input-lid='1740739101944']").as("Инпут 'Название компании'"),
            $("[data-input-lid='1740739289634']").as("Инпут 'ИНН'"),
            $("[data-input-lid='1732612519749']").as("Чекбокс 'Согласие с политикой'"),
            $("[data-input-lid='1732612558711']").as("Чекбокс 'Согласие на рассылку'"),
            $(".t-submit").as("Кнопка 'Отправить'")
    );
    private final SelenideElement surveyButton = $("[data-elem-id='1784642561033000001']")
            .as("Кнопка 'Опросный лист'");
    private final SelenideElement surveyPageName = $(".SurveyPage-Name")
            .as("Название страницы опросника");
    private static final List<Pattern> IGNORED_ERRORS = List.of(
            Pattern.compile("(chrome|moz|safari|ms-browser|edge)-extension://"),
            Pattern.compile("extensions::"),
            Pattern.compile("\\[Extension\\]")
    );
    private static final List<String> NAVIGATION_ITEMS = List.of(
            "Возможности",
            "О нас",
            "Кейсы",
            "Контакты",
            "Техподдержка",
            "sale@exon-group.ru",
            "Заказать звонок"
    );

    private static final String EXPECTED_TITLE = "Экзон: Управление строительством";
    private static final String EXPECTED_SURVEY_NAME = "Опросный лист ПО ЭКЗОН";

    public void checkMainPageTitle() {
        assertThat(title()).isEqualTo(EXPECTED_TITLE);
    }

    public void checkNoErrorsInLogs() {
        List<String> severeLogs = Arrays.stream(getConsoleLogs().split("\n"))
                .filter(line -> line.contains("SEVERE"))
                .filter(line -> IGNORED_ERRORS.stream()
                        .noneMatch(pattern -> pattern.matcher(line).find()))
                .toList();

        if (!severeLogs.isEmpty()) {
            Allure.addAttachment("SEVERE-логи", String.join("\n", severeLogs));
        }

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(severeLogs)
                .as("В логах консоли найдены SEVERE-ошибки (кроме ошибок расширений)")
                .isEmpty();
        softly.assertAll();
    }

    public void closePopUp() {
        if (cookiePopup.is(Condition.visible, Duration.ofSeconds(2))) {
            cookieAccept.click();
        }
    }

    public void clickCallbackButton() {
        callbackButton.click();
    }

    public void checkNavigationItems() {
        NAVIGATION_ITEMS.forEach(item ->
                $x("//*[text()='" + item + "']")
                        .as("Пункт навигации: '" + item + "'")
                        .shouldBe(visible));
    }

    public void checkCallbackFormVisible() {
        callbackFormFields.forEach(field -> field.shouldBe(visible));
    }

    public void clickSurveyButton() {
        surveyButton.click();
    }

    public void checkSurveyPageName() {
        surveyPageName.shouldHave(exactText(EXPECTED_SURVEY_NAME));
    }
}