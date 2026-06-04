package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        // AllureSelenide listener - сам делает скриншоты и сохраняет HTML
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
        );

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.sberleasing.ru";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.headless = false;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        Configuration.browserCapabilities.setCapability("goog:chromeOptions", options);
    }

    @BeforeEach
    public void beforeEach() {
        open("/");
    }
}
