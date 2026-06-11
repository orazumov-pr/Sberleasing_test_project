package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

public class SeoTests extends TestBase {

    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка наличия логотипа СберЛизинг")
    public void logoExistsTest() {
        mainPage.openPage()
                .checkLogoExists();
    }

    @Test
    @DisplayName("Проверка заголовка страницы")
    public void pageTitleNameTest() {
        mainPage.checkPageTitle();
    }

    @Test
    @DisplayName("Комплексная проверка SEO элементов")
    @Description("Проверяем мета-теги, заголовки, canonical-ссылку")
    public void complexSEOTest() {
        mainPage.openPage()
                .verifyMetaTags()
                .verifyHeadings()
                .verifyHeadingsStructure()
                .verifyCanonicalLink();
    }

    @Test
    @DisplayName("Комплексная проверка баннеров и акций")
    @Tag("banners")
    @Description("Проверяем баннеры: наличие, кликабельность, акции")
    public void complexBannersTest() {
        mainPage.openPage()
                .verifyMainBannerExists()
                .verifyAllBanners()
                .verifyPromotionsOnBanners()
                .verifyBannersClickable();
    }

}
