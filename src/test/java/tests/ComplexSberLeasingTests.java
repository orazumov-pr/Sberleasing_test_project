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

    @Test
    @DisplayName("Комплексная проверка контактной информации")
    @Tag("contacts")
    @Description("Проверяем контакты, форму заявки, обратную связь")
    public void complexContactsTest() {
        mainPage.openPage()
                .verifyAllContactInfo()
                .verifyFeedbackMechanism()
                .verifyApplicationForm();
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
}
