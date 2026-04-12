package utils;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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

        // Отключаем проверку CDP для стабильности
        Configuration.browserCapabilities.setCapability("goog:chromeOptions",
                new org.openqa.selenium.chrome.ChromeOptions()
                        .addArguments("--remote-allow-origins=*")
                        .addArguments("--disable-dev-shm-usage")
                        .addArguments("--no-sandbox")
        );
    }

    @BeforeEach
    public void openBaseUrl() {
        open("/");
    }
}