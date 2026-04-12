package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import pages.MainPage;
import utils.TestBase;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class SberLeasingTests extends TestBase  {

    private MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка заголовка страницы")
    public void pageTitleTest() {
        mainPage.openPage()
                .checkPageTitle();
    }

    @Test
    @DisplayName("Проверка контактного телефона")
    @Tag("critical")
    public void phoneNumberTest() {
        mainPage.openPage()
                .checkPhoneNumber();
    }

}
