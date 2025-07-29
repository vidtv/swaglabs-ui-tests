package cart;

import base.BaseTest;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

@Tag("Products")
@Tag("Cart")
public class AddProductToCartTest extends BaseTest {

    @Test
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Login as a standard user and verify that products page is opened", () -> {
            loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Add a first product from the list to the cart and verify that the badge is updated", () -> {
            productsPage.getAllProductItems().get(0).getAddToCartButton().click();

            assertThat(productsPage.getCartBadgeLocator()).isVisible();
            assertThat(productsPage.getCartBadgeLocator()).hasText("1");
        });
    }
}
