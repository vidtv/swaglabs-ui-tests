package products;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.products.ProductsPage;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

@Tag("Products")
public class AllProductsAreDisplayedTest extends BaseTest {

    // Pages
    private ProductsPage productsPage;

    // Test data
    private final int expectedProductQuantity = 6;
    private final List<String> expectedProductNames = List.of(
        "Sauce Labs Backpack",
        "Sauce Labs Bike Light",
        "Sauce Labs Bolt T-Shirt",
        "Sauce Labs Fleece Jacket",
        "Sauce Labs Onesie",
        "Test.allTheThings() T-Shirt (Red)"
    );

    @BeforeMethod
    public void setUpTest() {
        // Initialize products page
        productsPage = new ProductsPage(page);
    }

    @Test(testName = "Display all products after login")
    @Description("Verify that all products are displayed on the products page after logging in as a standard user")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Login as a standard user and verify that products page is opened", () -> {
            loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Verify that all products are displayed on the products page", () -> {
            assertEquals(productsPage.getAllProductItems().size(), expectedProductQuantity);

            productsPage.getAllProductItems().forEach(productItem ->
                    assertThat(productItem.getSelf()).isVisible()
            );

            assertEqualsNoOrder(productsPage.getAllProductNames(), expectedProductNames);
        });
    }
}
