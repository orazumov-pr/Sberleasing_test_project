package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class TestBase {

    @BeforeAll
    public static void setUp() {

        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.sberleasing.ru";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.headless = false;

        Configuration.browserCapabilities.setCapability("goog:chromeOptions",
                new org.openqa.selenium.chrome.ChromeOptions()
                        .addArguments("--remote-allow-origins=*")
                        .addArguments("--disable-dev-shm-usage")
                        .addArguments("--no-sandbox")
        );
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    public void openBaseUrl() {
        open("/");
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
//      Attach.attachAsText("Some file", "Some content");
        closeWebDriver();
    }
}
