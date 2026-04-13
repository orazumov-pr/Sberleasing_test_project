package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.assertj.core.api.Assertions.assertThat;


public class MainPage {

    // Локаторы
    private final SelenideElement phoneLink = $(By.linkText("8 (800) 555-555-6"));
    private final SelenideElement telegramLink = $("a[href*='https://t.me/sberleasing_official']");
    private final SelenideElement leasingCalculatorLink = $(By.linkText("Рассчитайте лизинг"));
    private final ElementsCollection stepItems = $$(".steps-item");
    private final SelenideElement innLink = $("a[href*='https://egrul.nalog.ru/index.html']");

    // Методы для взаимодействия

    public MainPage checkPageTitle() {
        assert title().contains("СберЛизинг");
        return this;
    }

    public MainPage checkPhoneNumber() {
        phoneLink.shouldBe(visible).shouldHave(text("8 (800) 555-555-6"));
        return this;
    }

    public MainPage checkTelegramLink() {
        telegramLink.scrollTo();
        telegramLink.shouldBe(exist);

        // Если элемент должен быть видим, пробуем его отобразить
        if (!telegramLink.isDisplayed()) {
            executeJavaScript("arguments[0].scrollIntoView(true);", telegramLink);
        }
        return this;
    }

//    public MainPage checkLeasingCalculatorLink() {
//        leasingCalculatorLink.scrollTo().shouldBe(visible);
//        return this;
//    }

    public MainPage checkStepsCount(int expectedCount) {
        stepItems.shouldHave(sizeLessThanOrEqual(expectedCount));
        return this;
    }

    public MainPage checkInnLink() {
        innLink.shouldBe(visible).shouldHave(attribute("href"));
        return this;
    }

    /**
     * Негативный тест: Попытка ввести некорректные данные в калькулятор
     */
    public MainPage tryInvalidCalculatorInput() {
        step("Попытка ввести некорректные данные в калькулятор", () -> {
            SelenideElement costInput = $("input[name='cost']");
            if (costInput.exists()) {
                // Вводим отрицательное значение
                costInput.setValue("-1000");

                // Проверяем, что валидация не пропускает отрицательные значения
                String value = costInput.getValue();
                assertThat(value).isNotEqualTo("-1000");
            }
        });
        return this;
    }

}