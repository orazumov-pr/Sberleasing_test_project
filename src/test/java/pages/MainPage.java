package pages;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {


    private final SelenideElement calculatorSection = $x("//h2[contains(text(),'Рассчитайте лизинг')]");
    private final SelenideElement phoneLink = $(org.openqa.selenium.By.linkText("8 (800) 555-555-6"));
    private final SelenideElement telegramLink = $("a[href*='t.me/sberleasing_official']");
    private final ElementsCollection stepItems = $$(".steps-item");
    private final SelenideElement edoServicesLink = $(org.openqa.selenium.By.linkText("ЭДО E-leasing"));


    // ========== БАЗОВЫЕ МЕТОДЫ (уже были) ==========

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Проверить заголовок страницы")
    public MainPage checkPageTitle() {
        String title = title();
        assertThat(title).as("Проверка заголовка страницы").contains("СберЛизинг");
        return this;
    }


    @Step("Проверить номер телефона")
    public MainPage checkPhoneNumber() {
        phoneLink.shouldBe(visible).shouldHave(text("8 (800) 555-555-6"));
        return this;
    }

    @Step("Проверить количество шагов: {expectedCount}")
    public MainPage checkStepsCount(int expectedCount) {
        stepItems.shouldHave(size(expectedCount));
        return this;
    }

    @Step("Проверить ссылку на Telegram")
    public MainPage checkTelegramLink() {
        telegramLink.scrollTo().shouldBe(exist);
        String href = telegramLink.getAttribute("href");
        assertThat(href).as("Проверка Telegram ссылки").isNotNull().contains("t.me");
        return this;
    }



}