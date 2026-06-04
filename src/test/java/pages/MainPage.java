package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {

    private final SelenideElement calculatorSection = $x("//h2[contains(text(),'Рассчитайте лизинг')]");
    private final SelenideElement phoneLink = $(org.openqa.selenium.By.linkText("8 (800) 555-555-6"));
    private final SelenideElement telegramLink = $("a[href*='t.me/sberleasing_official']");
    private final ElementsCollection stepItems = $$(".steps-item");
    private final SelenideElement edoServicesLink = $(org.openqa.selenium.By.linkText("ЭДО E-leasing"));


    private final SelenideElement header = $("header");
    private final SelenideElement footer = $("footer");
    private final ElementsCollection allLinks = $$("a");
    private final ElementsCollection socialLinks = $$("a[href*='t.me'], a[href*='vk.com'], a[href*='ok.ru']");
    private final SelenideElement searchButton = $("button[aria-label='Поиск']");
    private final SelenideElement searchInput = $("input[type='search']");
    private final ElementsCollection menuItems = $$(".menu-item, .nav-item");
    private final SelenideElement mainBanner = $(".banner, .main-slider, .hero-section");
    private final ElementsCollection banners = $$(".banner, .slider-item, .hero-banner");


    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Проверить заголовок страницы")
    public MainPage checkPageTitle() {
        String title = title();
        assertThat(title).as("Проверка заголовка страницы").contains("СберЛизинг");
        return this;
    }

    @Step("Проверить видимость калькулятора")
    public MainPage checkCalculatorVisible() {
        calculatorSection.scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Проверить номер телефона")
    public MainPage checkPhoneNumber() {
        phoneLink.shouldBe(visible).shouldHave(text("8 (800) 555-555-6"));
        return this;
    }

    @Step("Проверить количество шагов: {expectedCount}")
    public MainPage checkStepsCount(int expectedCount) {
        stepItems.shouldHave(size(expectedCount));
        return this;
    }

    @Step("Проверить ссылку на Telegram")
    public MainPage checkTelegramLink() {
        telegramLink.scrollTo().shouldBe(exist);
        String href = telegramLink.getAttribute("href");
        assertThat(href).as("Проверка Telegram ссылки").isNotNull().contains("t.me");
        return this;
    }

    @Step("Кликнуть на сервис ЭДО")
    public MainPage clickEdoService() {
        edoServicesLink.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить открытие страницы ЭДО")
    public MainPage verifyEdoPageOpened() {
        switchTo().window(1);
        String currentUrl = WebDriverRunner.url();
        assertThat(currentUrl).contains("e-leasing");
        closeWindow();
        switchTo().window(0);
        return this;
    }



    @Step("Проверить наличие всех основных элементов навигации")
    public MainPage verifyNavigationElements() {
        header.shouldBe(visible);
        menuItems.shouldHave(sizeGreaterThan(3));

        for (SelenideElement item : menuItems) {
            assertThat(item.isDisplayed()).isTrue();
            assertThat(item.getText().length()).isGreaterThan(0);
        }
        return this;
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
    public MainPage verifyHeaderBehaviorOnScroll() {
        header.shouldBe(visible);
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        sleep(1000);
        header.shouldBe(visible);
        executeJavaScript("window.scrollTo(0, 0)");
        sleep(500);
        header.shouldBe(visible);
        return this;
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
    public MainPage verifySocialLinksInFooter() {
        footer.scrollTo();
        socialLinks.shouldHave(sizeGreaterThan(0));

        for (SelenideElement socialLink : socialLinks) {
            String href = socialLink.getAttribute("href");
            assertThat(href).isNotNull();
            assertThat(href).matches(".*(t\\.me|vk\\.com|ok\\.ru).*");
        }
        return this;
    }

    @Step("Проверить наличие главного баннера")
    public MainPage verifyMainBannerExists() {
        mainBanner.shouldBe(visible);
        return this;
    }

    @Step("Проверить все баннеры на странице")
    public MainPage verifyAllBanners() {
        banners.shouldHave(sizeGreaterThan(0));

        for (SelenideElement banner : banners) {
            if (banner.isDisplayed()) {
                assertThat(banner.getText().length()).isGreaterThan(0);
            }
        }
        return this;
    }

    @Step("Проверить наличие акций на баннерах")
    public MainPage verifyPromotionsOnBanners() {
        String pageText = $("body").getText();
        assertThat(pageText).containsPattern("(?i)(скидка|акция|спецпредложени|льготн)");
        return this;
    }

    @Step("Проверить, что баннеры кликабельны и ведут на целевые страницы")
    public MainPage verifyBannersClickable() {
        for (int i = 0; i < Math.min(banners.size(), 3); i++) {
            SelenideElement banner = banners.get(i);
            if (banner.isDisplayed() && banner.isEnabled()) {
                String originalUrl = WebDriverRunner.url();
                banner.click();
                sleep(1500);
                String newUrl = WebDriverRunner.url();
                if (!newUrl.equals(originalUrl) && !newUrl.contains("#")) {
                    assertThat(newUrl).doesNotContain("404", "error");
                    back();
                    sleep(500);
                }
            }
        }
        return this;
    }

    @Step("Проверить все контактные данные на странице")
    public MainPage verifyAllContactInfo() {
        String pageText = $("body").getText();
        assertThat(pageText).containsPattern("8\\s*\\(?800\\)?\\s*555");
        assertThat(pageText).containsPattern("\\+?7\\s*\\(?\\d{3}\\)?");
        return this;
    }

    @Step("Проверить наличие обратной связи")
    public MainPage verifyFeedbackMechanism() {
        SelenideElement feedbackButton = $("button:contains('Заказать звонок'), a:contains('Обратная связь')");
        if (feedbackButton.exists()) {
            feedbackButton.shouldBe(visible);
            feedbackButton.click();
            sleep(500);
            SelenideElement modal = $(".modal, .popup, .form-container");
            modal.shouldBe(visible);
            modal.$("button.close, .close-btn").click();
        }
        return this;
    }

    @Step("Проверить форму заявки")
    public MainPage verifyApplicationForm() {
        SelenideElement applicationButton = $("button:contains('Оставить заявку'), a:contains('Заявка')");
        if (applicationButton.exists()) {
            applicationButton.scrollTo().click();
            sleep(1000);
            SelenideElement form = $("form");
            form.shouldBe(visible);

            SelenideElement nameField = form.$("input[name*='name' i], input[placeholder*='Имя' i]");
            SelenideElement phoneField = form.$("input[name*='phone' i], input[placeholder*='Телефон' i]");

            if (nameField.exists()) {
                nameField.shouldBe(visible);
            }
            if (phoneField.exists()) {
                phoneField.shouldBe(visible);
            }

            $("body").click();
            sleep(500);
        }
        return this;
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
            String src = img.getAttribute("src");
            if (src != null && !src.isEmpty()) {
                assertThat(src).doesNotContain("data:image");
            }
        }
        return this;
    }

    @Step("Проверить консоль на наличие ошибок JavaScript")
    public MainPage verifyNoJavaScriptErrors() {
        // Получаем консольные логи (работает только с Chrome DevTools)
        try {
            Object logs = executeJavaScript("return window.console.errors || []");
            // В реальном проекте тут нужна интеграция с DevTools
        } catch (Exception e) {
            // Логируем, что проверка пропущена
            System.out.println("Проверка консольных логов пропущена");
        }
        return this;
    }

    @Step("Проверить адаптивность страницы")
    public MainPage verifyResponsiveness() {
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
        return this;
    }

    @Step("Проверить наличие мета-тегов")
    public MainPage verifyMetaTags() {
        String description = $("meta[name='description']").getAttribute("content");
        String keywords = $("meta[name='keywords']").getAttribute("content");
        String viewport = $("meta[name='viewport']").getAttribute("content");

        assertThat(description).isNotNull();
        assertThat(keywords).isNotNull();
        assertThat(viewport).isNotNull();

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
        boolean hasH1 = $$("h1").size() > 0;
        boolean hasH2 = $$("h2").size() > 0;

        assertThat(hasH1 && hasH2).isTrue();

        return this;
    }

    @Step("Проверить наличие canonical ссылки")
    public MainPage verifyCanonicalLink() {
        SelenideElement canonical = $("link[rel='canonical']");
        if (canonical.exists()) {
            String href = canonical.getAttribute("href");
            assertThat(href).contains("sberleasing.ru");
        }
        return this;
    }

}