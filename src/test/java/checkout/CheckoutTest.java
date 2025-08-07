package checkout;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.cart.CartPage;
import page.checkout.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static page.checkout.CheckoutCompletePage.*;
import static page.checkout.CheckoutOverviewPage.CHECKOUT_OVERVIEW_PAGE_TITLE;
import static page.checkout.CheckoutPage.FIRST_NAME_IS_REQUIRED_ERROR;

@Tag("Checkout")
public class CheckoutTest extends BaseTest {
    private Double firstProductPrice;
    private Double secondProductPrice;

    // Pages
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
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
        checkoutOverviewPage = new CheckoutOverviewPage(page);
        checkoutCompletePage = new CheckoutCompletePage(page);
    }

    @Test(testName = "Checkout - Fill in user information with valid data. \n" +
            "Checkout - Error message with missing mandatory fields. \n" +
            "Checkout - Complete purchase successfully",
    description = "Verify that user can enter valid first name, last name, and postal code on checkout info page. \n" +
            "Verify that error appears if any of the required checkout fields (first name, last name, postal code) are empty. \n" +
            "Verify that user can complete checkout and see the order confirmation")
    @Feature("Checkout")
    public void test() {
        step("1. Open the login page, login as a standard user and verify that products page is opened",
                this::loginAsStandardUser
        );

        step("2. Add two products to the cart, open the cart, click 'Checkout' button, " +
                "leave empty fields on the checkout page, click 'Continue' and check that error message is displayed", () -> {
            productsPage.getProductItemByName(firstProductName).getAddToCartButton().click();
            productsPage.getProductItemByName(secondProductName).getAddToCartButton().click();

            firstProductPrice = productsPage.getProductItemByName(firstProductName).getProductPrice();
            secondProductPrice = productsPage.getProductItemByName(secondProductName).getProductPrice();

            productsPage.getCartButtonLocator().click();
            cartPage.getCheckoutButton().click();
            checkoutPage.getContinueButton().click();

            assertThat(checkoutPage.getErrorNotification()).hasText(FIRST_NAME_IS_REQUIRED_ERROR);
        });

        step("3. Populate all the required fields, click 'Continue' button, " +
                "and check that the checkout overview page is opened", () -> {
            checkoutPage.getFirstNameInput().fill(testFirstName);
            checkoutPage.getLastNameInput().fill(testLastName);
            checkoutPage.getPostalCodeInput().fill(testPostalCode);
            checkoutPage.getContinueButton().click();

            assertThat(checkoutOverviewPage.getPageTitle()).hasText(CHECKOUT_OVERVIEW_PAGE_TITLE);
        });

        step("4. Verify that there are the same items with correct prices displayed on the checkout overview page as on the cart page " +
                "and total price is calculated correctly", () -> {
            assertEquals(checkoutOverviewPage.getCheckoutItems().size(), expectedCheckoutItemsCount);

            assertEquals(checkoutOverviewPage.getCheckoutItems().get(0).getProductName(), firstProductName);
            assertEquals(checkoutOverviewPage.getCheckoutItems().get(1).getProductName(), secondProductName);

            assertEquals(checkoutOverviewPage.getCheckoutItems().get(0).getProductPrice(), firstProductPrice);
            assertEquals(checkoutOverviewPage.getCheckoutItems().get(1).getProductPrice(), secondProductPrice);

            // The total price is calculated as the sum of the product prices plus tax
            var expectedTotalPrice = firstProductPrice + secondProductPrice + checkoutOverviewPage.getTaxAmount();
            assertEquals(checkoutOverviewPage.getTotalPrice(), expectedTotalPrice);
        });

        step("5. Click 'Finish' button and check that the checkout complete page is opened " +
                "with a message about successful checkout completion", () -> {
            checkoutOverviewPage.getFinishButton().click();

            assertThat(checkoutCompletePage.getCheckoutCompleteTitle()).hasText(CHECKOUT_COMPLETE_PAGE_TITLE);

            assertThat(checkoutCompletePage.getCheckoutCompleteMessageHeader()).hasText(CHECKOUT_COMPLETE_MESSAGE_HEADER);
            assertThat(checkoutCompletePage.getCheckoutCompleteMessage()).hasText(CHECKOUT_COMPLETE_MESSAGE);
        });
    }
}
