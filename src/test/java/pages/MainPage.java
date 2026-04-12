package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    // Локаторы
    private final SelenideElement phoneLink = $(By.linkText("8 (800) 555-555-6"));
    private final SelenideElement calculatorSection = $x("//section[.//h2[contains(text(),'Рассчитайте лизинг')]]");


    // Методы для взаимодействия
    public MainPage openPage() {
        open("/");
        return this;
    }

    public MainPage checkPageTitle() {
        assert title().contains("СберЛизинг");
        return this;
    }

    public MainPage checkPhoneNumber() {
        phoneLink.shouldBe(visible).shouldHave(text("8 (800) 555-555-6"));
        return this;
    }

    public MainPage checkCalculatorVisible() {
        calculatorSection.shouldBe(visible);
        return this;
    }

}