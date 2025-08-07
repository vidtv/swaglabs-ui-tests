package login;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static page.login.LoginPage.YOU_CAN_ONLY_ACCESS_PRODUCTS_PAGE_AFTER_LOGGING_IN_ERROR;
import static util.Constants.PRODUCTS_PAGE_URL;

@Tag("Login")
public class ProtectedPageAccessTest extends BaseTest {

    @Test(testName = "Invalid URL access redirects to login",
            description = "Verify that navigating directly to a protected page without being logged in redirects user to the login page")
    @Feature("Login")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Try to access a products page without logging in and check that the user remains on the login page " +
                "and an error notification is displayed", () -> {
            page.navigate(PRODUCTS_PAGE_URL);

            assertThat(loginPage.getLoginFormLocator()).isVisible();
            assertThat(loginPage.getErrorNotification()).hasText(YOU_CAN_ONLY_ACCESS_PRODUCTS_PAGE_AFTER_LOGGING_IN_ERROR);
        });
    }
}
