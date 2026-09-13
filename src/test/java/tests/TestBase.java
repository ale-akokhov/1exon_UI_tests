package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ProjectConfig;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class TestBase {

    MainPage mainPage = new MainPage();
    static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    static String baseUrl = config.getBaseUrl();

    @BeforeAll
    static void configure() {
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.baseUrl = baseUrl;

//        setRemoteWebdriver();

        SelenideLogger.addListener("Allure Selenide", new AllureSelenide());
    }

    @AfterEach
    void addAttach() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();
        AllureAttachments.addVideo();
    }

    @AfterAll
    static void close() {
        closeWebDriver();
    }

    static void setRemoteWebdriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = config.getRemoteUrl();
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }

}
