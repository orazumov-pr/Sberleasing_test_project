package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {

    private final SelenideElement phoneLink = $(org.openqa.selenium.By.linkText("8 (800) 555-555-6"));
    private final SelenideElement dzenLink = $("a[href*='https://zen.yandex.ru/sberleasing']");
    private final SelenideElement telegramLink = $("a[href*='t.me/sberleasing_official']");
    private final SelenideElement vkLink = $("a[href*='https://vk.com/public190909714']");
    private final SelenideElement okLink = $("a[href*='https://ok.ru/sberleasing']");
    private final SelenideElement innLink = $("a[href*='https://egrul.nalog.ru/index.html']");
    private final SelenideElement header = $("header");
    private final SelenideElement footer = $("footer");
    private final ElementsCollection socialLinks = $$("a[href*='t.me'], a[href*='vk.com'], a[href*='ok.ru']");
    private final ElementsCollection menuItems = $$(".menu-item, .nav-item");
    private final SelenideElement mainBanner = $(".banner, .main-slider, .hero-section");
    private final ElementsCollection banners = $$(".banner, .slider-item, .hero-banner");


    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Проверить наличие логотипа на странице")
    public void checkLogoExists() {
        SelenideElement logo = $("a[aria-label='АО «Сбербанк Лизинг»']");
        logo.shouldBe(visible);
    }

    @Step("Проверить заголовок страницы")
    public void checkPageTitle() {
        String title = title();
        assertThat(title).as("Проверка заголовка страницы").contains("СберЛизинг");
    }

    public void checkInnLink() {
        innLink.shouldBe(visible).shouldHave(attribute("href"));
    }

    @Step("Проверить номер телефона")
    public void checkPhoneNumber() {
        phoneLink.shouldBe(visible).shouldHave(text("8 (800) 555-555-6"));
    }

    @Step("Проверить наличие информации о новостях на странице")
    public void checkNewsBlockExists() {
        SelenideElement newsText = $x("//*[contains(text(), 'Новости лизинга')]");
        newsText.shouldBe(exist);
        SelenideElement parentBlock = newsText.parent();
        System.out.println("Найден блок с новостями: " + parentBlock.getText().substring(0, Math.min(100, parentBlock.getText().length())));
    }

    @Step("Проверить ссылку на Дзен")
    public void checkDzenLink() {
        dzenLink.scrollTo().shouldBe(exist);
        String href = dzenLink.getAttribute("href");
        assertThat(href).as("Проверка ссылки на Дзен").isNotNull().contains("zen.yandex.ru");
    }

    @Step("Проверить ссылку на Telegram")
    public void checkTelegramLink() {
        telegramLink.scrollTo().shouldBe(exist);
        String href = telegramLink.getAttribute("href");
        assertThat(href).as("Проверка Telegram ссылки").isNotNull().contains("t.me");
    }

    @Step("Проверить ссылку на VK")
    public void checkVKLink() {
        vkLink.scrollTo().shouldBe(exist);
        String href = vkLink.getAttribute("href");
        assertThat(href).as("Проверка ссылки на VK").isNotNull().contains("vk.com");
    }

    @Step("Проверить ссылку на ОК")
    public void checkOkLink() {
        okLink.scrollTo().shouldBe(exist);
        String href = okLink.getAttribute("href");
        assertThat(href).as("Проверка ссылки на Одноклассники").isNotNull().contains("ok.ru");
    }


    @Step("Проверить, что все ссылки в меню ведут на страницы без ошибок")
    public MainPage verifyMenuLinksAreValid() {
        for (int i = 0; i < Math.min(menuItems.size(), 5); i++) {
            SelenideElement menuItem = menuItems.get(i);
            String menuText = menuItem.getText();
            if (!menuText.isEmpty() && !menuText.contains("Личный кабинет")) {
                menuItem.click();
                sleep(1000);
                String currentUrl = WebDriverRunner.url();
                assertThat(currentUrl).doesNotContain("404", "error", "not-found");
                assertThat(title()).isNotNull();
                open("/");
                sleep(500);
            }
        }
        return this;
    }

    @Step("Проверить работу хедера при скролле")
    public void verifyHeaderBehaviorOnScroll() {
        header.shouldBe(visible);
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        sleep(1000);
        header.shouldBe(visible);
        executeJavaScript("window.scrollTo(0, 0)");
        sleep(500);
        header.shouldBe(visible);
    }


    @Step("Проверить видимость футера и его содержимого")
    public MainPage verifyFooterVisibility() {
        footer.scrollTo();
        footer.shouldBe(visible);
        return this;
    }

    @Step("Проверить все ссылки в футере")
    public MainPage verifyFooterLinks() {
        ElementsCollection footerLinks = footer.$$("a");
        footerLinks.shouldHave(sizeGreaterThan(5));

        for (SelenideElement link : footerLinks) {
            assertThat(link.getAttribute("href")).isNotNull();
        }
        return this;
    }

    @Step("Проверить наличие контактов в футере")
    public MainPage verifyFooterContacts() {
        String footerText = footer.getText();
        assertThat(footerText).contains("8 (800)");
        assertThat(footerText).contains("СберЛизинг");
        return this;
    }

    @Step("Проверить наличие ссылок на социальные сети в футере")
    public void verifySocialLinksInFooter() {
        footer.scrollTo();
        socialLinks.shouldHave(sizeGreaterThan(0));

        for (SelenideElement socialLink : socialLinks) {
            String href = socialLink.getAttribute("href");
            assertThat(href).isNotNull();
            assertThat(href).matches(".*(t\\.me|vk\\.com|ok\\.ru).*");
        }
    }

    @Step("Проверить наличие главного баннера")
    public MainPage verifyMainBannerExists() {
        mainBanner.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие баннеров на странице")
    public MainPage verifyAllBanners() {
        boolean hasBanners = !banners.isEmpty();

        if (!hasBanners) {
            hasBanners = $("img[src*='banner']").exists() ||
                    $("div[style*='background']").exists() ||
                    $("section:has(img)").exists();
        }

        assertThat(hasBanners)
                .as("На главной странице должны присутствовать баннеры или промо-материалы")
                .isTrue();

        System.out.println("Найдено " + banners.size() + " потенциальных баннеров");

        return this;
    }

    @Step("Проверить наличие акций на баннерах")
    public MainPage verifyPromotionsOnBanners() {
        String pageText = $("body").getText();
        assertThat(pageText).containsPattern("(?i)(скидка|акция|спецпредложени|льготн)");
        return this;
    }

    @Step("Проверить, что баннеры кликабельны и ведут на целевые страницы")
    public void verifyBannersClickable() {
        for (int i = 0; i < Math.min(banners.size(), 3); i++) {
            SelenideElement banner = banners.get(i);
            if (banner.isDisplayed() && banner.isEnabled()) {
                String originalUrl = WebDriverRunner.url();
                banner.click();
                sleep(1500);
                String newUrl = WebDriverRunner.url();
                assert newUrl != null;
                if (!newUrl.equals(originalUrl) && !newUrl.contains("#")) {
                    assertThat(newUrl).doesNotContain("404", "error");
                    back();
                    sleep(500);
                }
            }
        }
    }

    @Step("Проверить все контактные данные на странице")
    public void verifyAllContactInfo() {
        String pageText = $("body").getText();
        assertThat(pageText).containsPattern("8\\s*\\(?800\\)?\\s*555");
        assertThat(pageText).containsPattern("\\+?7\\s*\\(?\\d{3}\\)?");
    }


    @Step("Проверить время загрузки страницы")
    public MainPage verifyPageLoadTime() {
        long startTime = System.currentTimeMillis();
        open("/");
        long loadTime = System.currentTimeMillis() - startTime;
        assertThat(loadTime).isLessThan(5000);
        return this;
    }

    @Step("Проверить, что все изображения загружаются")
    public MainPage verifyAllImagesLoaded() {
        ElementsCollection images = $$("img");
        images.shouldHave(sizeGreaterThan(0));

        for (SelenideElement img : images) {
            // Получаем src или data-src (для lazy loading)
            String src = img.getAttribute("src");
            if (src == null || src.isEmpty()) {
                src = img.getAttribute("data-src");
            }

            // Проверяем, что источник изображения не пустой
            assertThat(src)
                    .as("Изображение не имеет источника (src или data-src)")
                    .isNotNull()
                    .isNotEmpty();
        }

        return this;
    }

    @Step("Проверить адаптивность страницы")
    public void verifyResponsiveness() {
        String[] viewports = {"375x667", "768x1024", "1366x768", "1920x1080"};

        for (String viewport : viewports) {
            executeJavaScript("window.resizeTo(" + viewport.replace("x", ",") + ")");
            sleep(1000);
            $("body").shouldBe(visible);

            if (viewport.equals("375x667")) {
                SelenideElement mobileMenu = $(".mobile-menu, .hamburger");
                if (mobileMenu.exists()) {
                    mobileMenu.shouldBe(visible);
                }
            }
        }

        executeJavaScript("window.resizeTo(1920, 1080)");
    }

    @Step("Проверить наличие мета-тегов")
    public MainPage verifyMetaTags() {
        // Проверяем только description (самый важный SEO тег)
        SelenideElement metaDescription = $("meta[name='description']");
        assertThat(metaDescription.exists())
                .as("Мета-тег 'description' должен присутствовать")
                .isTrue();

        String description = metaDescription.getAttribute("content");
        assertThat(description)
                .as("Мета-тег 'description' не должен быть пустым")
                .isNotNull()
                .isNotEmpty();

        SelenideElement metaViewport = $("meta[name='viewport']");
        assertThat(metaViewport.exists())
                .as("Мета-тег 'viewport' должен присутствовать")
                .isTrue();

        return this;
    }

    @Step("Проверить наличие заголовков H1-H2")
    public MainPage verifyHeadings() {
        ElementsCollection h1 = $$("h1");
        ElementsCollection h2 = $$("h2");

        assertThat(h1.size()).isGreaterThanOrEqualTo(1);
        assertThat(h2.size()).isGreaterThanOrEqualTo(3);

        for (SelenideElement heading : h1) {
            assertThat(heading.getText().length()).isGreaterThan(0);
        }

        return this;
    }

    @Step("Проверить структуру заголовков")
    public MainPage verifyHeadingsStructure() {
        boolean hasH1 = !$$("h1").isEmpty();
        boolean hasH2 = !$$("h2").isEmpty();

        assertThat(hasH1 && hasH2).isTrue();

        return this;
    }

    @Step("Проверить наличие canonical ссылки")
    public void verifyCanonicalLink() {
        SelenideElement canonical = $("link[rel='canonical']");
        if (canonical.exists()) {
            String href = canonical.getAttribute("href");
            assertThat(href).contains("sberleasing.ru");
        }
    }

}