package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import utils.TestBase;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class SberLeasingTests extends TestBase {

    private MainPage mainPage = new MainPage();

    @Test
    @Tag("smoke")
    @DisplayName("Проверка заголовка страницы")
    public void pageTitleNameTest() {
        step("Проверить корректность тега title", () -> {
            mainPage.checkPageTitle();
        });
    }

    @Test
    @DisplayName("Проверка контактного телефона")
    @Tag("critical")
    public void phoneNumberTest() {
        step("Проверить корректность номера телефона", () -> {
            mainPage.checkPhoneNumber();
        });
    }

    @Test
    @DisplayName("Проверка ссылки на Telegram-канал")
    @Tag("critical")
    public void telegramLinkTest() {
        mainPage.checkTelegramLink();
    }

    @Test
    @DisplayName("Проверка количества шагов получения лизинга")
    @Tag("smoke")
    public void stepsCountTest() {
        mainPage.checkStepsCount(5);
    }

    @Test
    @DisplayName("Проверка ссылки на портал предоставления сведений по ЕГРЮЛ")
    @Tag("medium")
    public void innLinkTest() {
        mainPage.checkInnLink();
    }

    // Негативный тест
    @Test
    @DisplayName("Негативный тест: Ввод отрицательных значений в калькулятор лизинга")
    @Tag("negative")
    public void invalidCalculatorInputTest() {
        mainPage.tryInvalidCalculatorInput();
    }

}
