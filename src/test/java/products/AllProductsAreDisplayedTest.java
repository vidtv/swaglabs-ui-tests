package products;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;

@Tag("Products")
public class AllProductsAreDisplayedTest extends BaseTest {

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

    @Test(testName = "Display all products after login")
    @Description("Verify that all products are displayed on the products page after logging in as a standard user")
    public void test() {
        step("1. Open the login page, login as a standard user and verify that products page is opened",
                this::loginAsStandardUser
        );

        step("2. Verify that all products are displayed on the products page", () -> {
            assertEquals(productsPage.getAllProductItems().size(), expectedProductQuantity);

            productsPage.getAllProductItems().forEach(productItem ->
                    assertThat(productItem.getSelf()).isVisible()
            );

            assertEqualsNoOrder(productsPage.getAllProductNames(), expectedProductNames);
        });
    }
}
