package checkout;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.cart.CartPage;
import page.checkout.*;
import page.products.ProductsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static page.checkout.CheckoutCompletePage.*;
import static page.checkout.CheckoutOverviewPage.CHECKOUT_OVERVIEW_PAGE_TITLE;
import static page.checkout.CheckoutPage.FIRST_NAME_IS_REQUIRED_ERROR;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

public class CheckoutTest extends BaseTest {

    // Pages
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    // Test data
    private final String firstProductName = "Sauce Labs Backpack";
    private final String secondProductName = "Sauce Labs Bike Light";

    private final String testFirstName = "Janos";
    private final String testLastName = "Arany";
    private final String testPostalCode = "1051";

    private final int expectedCheckoutItemsCount = 2;

    @BeforeMethod
    public void setUpTest() {
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
        checkoutOverviewPage = new CheckoutOverviewPage(page);
        checkoutCompletePage = new CheckoutCompletePage(page);
    }

    @Test
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Login as a standard user and verify that products page is opened", () -> {
            loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Add two products to the cart, open the cart, click 'Checkout' button, " +
                "leave empty fields on the checkout page, click 'Continue' and check that error message is displayed", () -> {
            productsPage.getProductItemByName(firstProductName).getAddToCartButton().click();
            productsPage.getProductItemByName(secondProductName).getAddToCartButton().click();

            productsPage.getCartButtonLocator().click();
            cartPage.getCheckoutButton().click();
            checkoutPage.getContinueButton().click();

            assertThat(checkoutPage.getErrorNotification()).hasText(FIRST_NAME_IS_REQUIRED_ERROR);
        });

        step("4. Populate all the required fields, click 'Continue' button, " +
                "and check that the checkout overview page is opened", () -> {
            checkoutPage.getFirstNameInput().fill(testFirstName);
            checkoutPage.getLastNameInput().fill(testLastName);
            checkoutPage.getPostalCodeInput().fill(testPostalCode);
            checkoutPage.getContinueButton().click();

            assertThat(checkoutOverviewPage.getPageTitle()).hasText(CHECKOUT_OVERVIEW_PAGE_TITLE);
        });

        step("5. Verify that there are the same items displayed on the checkout overview page as on the cart page", () -> {
            assertEquals(checkoutOverviewPage.getCheckoutItems().size(), expectedCheckoutItemsCount);

            assertThat(checkoutOverviewPage.getCheckoutItems().get(0).getProductName()).hasText(firstProductName);
            assertThat(checkoutOverviewPage.getCheckoutItems().get(1).getProductName()).hasText(secondProductName);
        });

        step("6. Click 'Finish' button and check that the checkout complete page is opened " +
                "with a message about successful checkout completion", () -> {
            checkoutOverviewPage.getFinishButton().click();

            assertThat(checkoutCompletePage.getCheckoutCompleteTitle()).hasText(CHECKOUT_COMPLETE_PAGE_TITLE);

            assertThat(checkoutCompletePage.getCheckoutCompleteMessageHeader()).hasText(CHECKOUT_COMPLETE_MESSAGE_HEADER);
            assertThat(checkoutCompletePage.getCheckoutCompleteMessage()).hasText(CHECKOUT_COMPLETE_MESSAGE);
        });
    }
}
