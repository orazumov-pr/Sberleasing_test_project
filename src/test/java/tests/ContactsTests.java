package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Epic("Тестирование главной страницы сайта СберЛизинг")
@Feature("Комплексная проверка контактных данных на странице")
@Story("Проверка точности контактных данных: телефоны, ссылки")
@Tag("contacts")

public class ContactsTests extends TestBase {

    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Строгая проверка правильности контактного телефона")
    @Description("Точная проверка номера телефона в шапке главной страницы")
    public void phoneNumberTest() {
        mainPage.checkPhoneNumber();
    }

    @Test
    @DisplayName("Проверка ссылок на Социальные сети")
    @Description("Точечная проверка всех ссылок на соцсети в футере")
    public void socialLinkTest() {
        mainPage.checkDzenLink();
        mainPage.checkTelegramLink();
        mainPage.checkVKLink();
        mainPage.checkOkLink();
    }

    @Test
    @DisplayName("Проверка контактной информации")
    @Description("Проверка всех контактных данных на странице")
    public void complexContactsTest() {
        mainPage.openPage()
                .verifyAllContactInfo();
    }

}


