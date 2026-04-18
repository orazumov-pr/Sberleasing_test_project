package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.assertj.core.api.Assertions.assertThat;


public class MainPage {

    // Локаторы
    private final SelenideElement phoneLink = $(By.linkText("8 (800) 555-555-6"));
    private final SelenideElement telegramLink = $("a[href*='https://t.me/sberleasing_official']");
    private final ElementsCollection stepItems = $$(".steps-item");
    private final SelenideElement innLink = $("a[href*='https://egrul.nalog.ru/index.html']");

    // Методы для взаимодействия

    public void checkPageTitle() {
        assert title().contains("СберЛизинг");
    }

    public void checkPhoneNumber() {
        phoneLink.shouldBe(visible).shouldHave(text("8 (800) 555-555-6"));
    }

    public void checkTelegramLink() {
        telegramLink.scrollTo();
        telegramLink.shouldBe(exist);

        if (!telegramLink.isDisplayed()) {
            executeJavaScript("arguments[0].scrollIntoView(true);", telegramLink);
        }
    }

    public void checkStepsCount(int expectedCount) {
        stepItems.shouldHave(sizeLessThanOrEqual(expectedCount));
    }

    public void checkInnLink() {
        innLink.shouldBe(visible).shouldHave(attribute("href"));
    }

    /**
     * Негативный тест: Попытка ввести некорректные данные в калькулятор
     */
    public void tryInvalidCalculatorInput() {
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
    }

}