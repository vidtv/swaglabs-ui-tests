package login;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.products.ProductsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

@Tag("Login")
public class SuccessfulLoginAndLogoutTest extends BaseTest {

    // Pages
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUpTest() {
        // Initialize products page
        productsPage = new ProductsPage(page);
    }

    @Test(testName = "Successful login with valid credentials. \n" +
            "Logout from the application")
    @Description("Verify successful login with valid credentials. \n" +
            "Verify user can log out successfully and it's redirected to the login page")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Enter valid credentials and verify that a page with products list is displayed", () -> {
            loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Logout from the application and check that login from is displayed", () -> {
            productsPage.logout();

            assertThat(loginPage.getLoginFormLocator()).isVisible();
        });
    }
}
