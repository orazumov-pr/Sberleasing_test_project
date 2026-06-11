package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class NavigationTests extends TestBase{

    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка наличия блока с новостями")
    public void newsBlockExistsTest() {
        mainPage.openPage()
                .checkNewsBlockExists();
    }

    @Test
    @DisplayName("Проверка ссылки на портал предоставления сведений по ЕГРЮЛ")
    public void innLinkTest() {
        mainPage.checkInnLink();
    }

    @Test
    @DisplayName("Проверка навигации по сайту")
    @Description("Проверяем элементы навигации: хедер, ссылки")
    public void complexNavigationTest() {
        mainPage.openPage()
                .verifyMenuLinksAreValid()
                .verifyHeaderBehaviorOnScroll();
    }

    @Test
    @DisplayName("Комплексная проверка футера")
    @Description("Проверяем футер: видимость, ссылки, контакты, соцсети")
    public void complexFooterTest() {
        mainPage.openPage()
                .verifyFooterVisibility()
                .verifyFooterLinks()
                .verifyFooterContacts()
                .verifySocialLinksInFooter();
    }

    @Test
    @DisplayName("Комплексная проверка производительности страницы")
    @Description("Проверяем время загрузки, изображения, адаптивность")
    public void complexPerformanceTest() {
        mainPage.openPage()
                .verifyPageLoadTime()
                .verifyAllImagesLoaded()
                .verifyResponsiveness();
    }
}
