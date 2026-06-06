package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;


@Epic("Сайт СберЛизинг - Главная страница")
@Feature("Точные проверки параметров на главной странице")
public class SberLeasingTests extends TestBase {

    private MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка наличия логотипа СберЛизинг")
    @Tag("seo")
    public void logoExistsTest() {
        mainPage.openPage()
                .checkLogoExists();
    }

    @Test
    @Tag("seo")
    @DisplayName("Проверка заголовка страницы")
    public void pageTitleNameTest() {
        mainPage.checkPageTitle();
    }

    @Test
    @Tag("contacts")
    @DisplayName("Строгая проверка правильности контактного телефона")
    public void phoneNumberTest() {
        mainPage.checkPhoneNumber();
    }

    @Test
    @DisplayName("Проверка наличия блока с новостями")
    @Tag("navigation")
    public void newsBlockExistsTest() {
        mainPage.openPage()
                .checkNewsBlockExists();
    }

    @Test
    @DisplayName("Проверка ссылок на Социальные сети")
    @Tag("contacts")
    public void socialLinkTest() {
        mainPage.checkDzenLink();
        mainPage.checkTelegramLink();
        mainPage.checkVKLink();
        mainPage.checkOkLink();
    }

    @Test
    @DisplayName("Проверка ссылки на портал предоставления сведений по ЕГРЮЛ")
    @Tag("navigation")
    public void innLinkTest() {
        mainPage.checkInnLink();
    }
}


