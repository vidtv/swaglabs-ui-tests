package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import page.LoginPage;

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
