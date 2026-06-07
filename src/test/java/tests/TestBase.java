package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;
import static com.codeborne.selenide.Selenide.open;


public class TestBase {

     @BeforeAll
    static void setUp() {

        WebDriverManager.chromedriver().setup();

                SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true)
        );

        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl");  //https://www.sberleasing.ru
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "128.0");

        String loginSelenoid = System.getProperty("loginSelenoid");
        String passwordSelenoid = System.getProperty("passwordSelenoid");
        String urlSelenoid = System.getProperty("urlSelenoid");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" + loginSelenoid + ":" + passwordSelenoid + "@" + urlSelenoid;

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
        Configuration.headless = false;

    }

    @BeforeEach
    public void beforeEach() {
        open("/");
    }
}
