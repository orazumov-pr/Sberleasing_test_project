package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;


@Epic("Сайт СберЛизинг")
@Feature("Комплексные тесты главной страницы")
public class SberLeasingTests extends TestBase {

    private MainPage mainPage = new MainPage();

    @Test
    @Tag("smoke")
    @DisplayName("Проверка заголовка страницы")
    public void pageTitleNameTest() {
        mainPage.checkPageTitle();
    }

    @Test
    @Tag("critical")
    @DisplayName("Строгая проверка правильности контактного телефона")
    public void phoneNumberTest() {
        mainPage.checkPhoneNumber();
    }

    @Test
    @DisplayName("Проверка ссылок на Социальные сети")
    @Tag("smoke")
    public void socialLinkTest() {
        mainPage.checkDzenLink();
        mainPage.checkTelegramLink();
        mainPage.checkVKLink();
        mainPage.checkOkLink();
    }

    @Test
    @DisplayName("Проверка ссылки на портал предоставления сведений по ЕГРЮЛ")
    @Tag("medium")
    public void innLinkTest() {
        mainPage.checkInnLink();
    }

    @Test
    @DisplayName("Проверка баннера на портал ЭДО E-leasing")
    @Tag("medium")
    public void edoLinkTest() {
        mainPage.verifyEdoPageOpened();
    }

    @Test
    @DisplayName("Проверка количества шагов получения лизинга")
    @Tag("smoke")
    public void stepsCountTest() {
        mainPage.checkStepsCount(5);
    }

}


