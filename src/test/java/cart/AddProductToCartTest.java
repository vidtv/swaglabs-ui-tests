package cart;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.cart.CartPage;
import page.products.ProductsPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

@Tag("Products")
@Tag("Cart")
public class AddProductToCartTest extends BaseTest {

    // Pages
    private ProductsPage productsPage;
    private CartPage cartPage;

    // Test data
    private final String firstProductName = "Sauce Labs Backpack";
    private final String secondProductName = "Sauce Labs Bike Light";

    private final int expectedCartItemsCount = 2;

    @BeforeMethod
    public void setUpTest() {
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
    }

    @Test(testName = "Add a product to the shopping cart. \n" +
            "Remove a product from the shopping cart. \n" +
            "Shopping cart - View added products")
    @Description("Verify a product can be added to the cart and cart badge updates. \n" +
            "Verify a product can be removed from the cart and cart badge updates accordingly. \n" +
            "Verify products added to the cart are displayed correctly in the cart page.")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Login as a standard user and verify that products page is opened", () -> {
            loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Add a first product from the list to the cart and verify that the cart badge is updated", () -> {
            productsPage.getProductItemByName(firstProductName).getAddToCartButton().click();

            assertThat(productsPage.getCartBadgeLocator()).isVisible();
            assertThat(productsPage.getCartBadgeLocator()).hasText("1");
        });

        step("4. Add a second product from the list to the cart and verify that the cart badge is updated", () -> {
            productsPage.getProductItemByName(secondProductName).getAddToCartButton().click();

            assertThat(productsPage.getCartBadgeLocator()).isVisible();
            assertThat(productsPage.getCartBadgeLocator()).hasText("2");
        });

        step("5. Verify that the cart contains both products", () -> {
            productsPage.getCartButtonLocator().click();

            var cartItems = cartPage.getCartItems();
            assertEquals(cartItems.size(), expectedCartItemsCount, "Cart should contain 2 items");

            assertEquals(cartItems.get(0).getProductName(), firstProductName);
            assertEquals(cartItems.get(1).getProductName(), secondProductName);
        });

        step("6. Return back to the Products page, remove the first product from the cart " +
                "and verify that the cart badge is updated accordingly", () -> {
            cartPage.getContinueShoppingButton().click();
            productsPage.getProductItemByName(firstProductName).getRemoveFromCartButton().click();

            assertThat(productsPage.getCartBadgeLocator()).isVisible();
            assertThat(productsPage.getCartBadgeLocator()).hasText("1");
        });

        step("7. Remove the second product from the cart and verify that the cart badge is no longer displayed", () -> {
            productsPage.getProductItemByName(secondProductName).getRemoveFromCartButton().click();

            assertThat(productsPage.getCartBadgeLocator()).isHidden();
        });
    }
}
