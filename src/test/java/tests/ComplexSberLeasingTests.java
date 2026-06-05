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
    @DisplayName("Проверка контактной информации")
    @Tag("contacts")
    @Description("Проверка всех контактных данных на странице")
    public void complexContactsTest() {
        mainPage.openPage()
                .verifyAllContactInfo();
    }

    @Test
    @DisplayName("Проверка навигации по сайту")
    @Tag("navigation")
    @Description("Проверяем элементы навигации: хедер, ссылки")
    public void complexNavigationTest() {
        mainPage.openPage()
                .verifyMenuLinksAreValid()
                .verifyHeaderBehaviorOnScroll();
    }

    @Test
    @DisplayName("Комплексная проверка производительности страницы")
    @Tag("performance")
    @Description("Проверяем время загрузки, изображения, адаптивность")
    public void complexPerformanceTest() {
        mainPage.openPage()
                .verifyPageLoadTime()
                .verifyAllImagesLoaded()
                .verifyResponsiveness();
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

    @Test
    @DisplayName("Комплексная проверка SEO элементов")
    @Tag("seo")
    @Description("Проверяем мета-теги, заголовки, canonical ссылку")
    public void complexSEOTest() {
        mainPage.openPage()
                .verifyMetaTags()
                .verifyHeadings()
                .verifyHeadingsStructure()
                .verifyCanonicalLink();
    }

    @Test
    public void complexBannersTest() {
        mainPage.openPage()
                .verifyMainBannerExists()
                .verifyAllBanners()
                .verifyPromotionsOnBanners()
                .verifyBannersClickable();
    }

    @Test
    @DisplayName("Проверка ссылки 'Узнать об ЭДО' в блоке онлайн-сервисов")
    @Tag("critical")
    @Tag("links")
    @Description("Проверяем, что ссылка 'Узнать об ЭДО' ведет на правильный URL и открывается в новой вкладке")
    public void edoLearnMoreLinkTest() {
        mainPage.openPage()
                .scrollToOnlineServices()
                .verifyEdoLearnMoreLink()
                .clickEdoLearnMoreLinkAndVerify();
    }


}



