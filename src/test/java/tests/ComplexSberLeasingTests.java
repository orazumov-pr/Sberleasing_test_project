package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;


@Epic("Сайт СберЛизинг")
@Feature("Комплексные тесты главной страницы")
public class ComplexSberLeasingTests extends TestBase {

    private MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Комплексная проверка навигации по сайту")
    @Tag("navigation")
    @Description("Проверяем все элементы навигации: меню, хедер, ссылки")
    public void complexNavigationTest() {
        mainPage.openPage()
                .verifyNavigationElements()
                .verifyMenuLinksAreValid()
                .verifyHeaderBehaviorOnScroll();
    }

    @Test
    @DisplayName("Комплексная проверка футера")
    @Tag("footer")
    @Description("Проверяем футер: видимость, ссылки, контакты, соцсети")
    public void complexFooterTest() {
        mainPage.openPage()
                .verifyFooterVisibility()
                .verifyFooterLinks()
                .verifyFooterContacts()
                .verifySocialLinksInFooter();
    }

}
