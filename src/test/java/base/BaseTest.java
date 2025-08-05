package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import page.login.LoginPage;
import page.products.ProductsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

/**
 * BaseTest class provides a setup for Playwright tests.
 * <p>
 * It initializes Playwright, creates a browser instance,
 * and provides a context and page for tests to use.
 * </br>
 * Also includes a LoginPage object for login-related tests.
 */
public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    // Pages
    protected LoginPage loginPage;
    protected ProductsPage productsPage;

    @BeforeClass
    protected void setUpClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)
                        .setArgs(java.util.List.of("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        );

        playwright.selectors().setTestIdAttribute("data-test");
    }

    @BeforeMethod
    protected void setUpBaseTest() {
        context = browser.newContext();
        page = context.newPage();

        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
    }

    /**
     * Logs in as a standard user and verifies that the products page is displayed.
     */
    protected void loginAsStandardUser() {
        loginPage.navigate();
        loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

        assertThat(productsPage.getProductsListLocator()).isVisible();
    }

    @AfterMethod
    protected void tearDownTest() {
        if (context != null) {
            context.close();
        }
    }

    @AfterClass
    protected void tearDownClass() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
