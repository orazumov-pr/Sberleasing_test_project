package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import utils.TestBase;

public class SberLeasingNegativeTests extends TestBase {

    private MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Негативный тест: Ввод отрицательных значений в калькулятор лизинга")
    @Tag("negative")
    public void invalidCalculatorInputTest() {
        mainPage.tryInvalidCalculatorInput();
    }
}
