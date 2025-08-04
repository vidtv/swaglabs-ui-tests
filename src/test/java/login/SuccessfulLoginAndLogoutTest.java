package login;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;

@Tag("Login")
public class SuccessfulLoginAndLogoutTest extends BaseTest {

    @Test(testName = "Successful login with valid credentials. \n" +
            "Session persistence - remain logged in after page refresh. \n" +
            "Logout from the application")
    @Description("Verify successful login with valid credentials. \n" +
            "Verify user remains logged in after refreshing the products page. \n" +
            "Verify user can log out successfully and it's redirected to the login page")
    public void test() {
        step("1. Open the login page, login as a standard user and verify that products page is opened",
                this::loginAsStandardUser
        );

        step("2. Refresh the page and check that products list is still displayed", () -> {
            page.reload();

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Logout from the application and check that login from is displayed", () -> {
            productsPage.logout();

            assertThat(loginPage.getLoginFormLocator()).isVisible();
        });
    }
}
