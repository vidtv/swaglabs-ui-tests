package products;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;
import page.products.ProductItem;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static java.util.Comparator.reverseOrder;
import static org.testng.AssertJUnit.assertEquals;
import static page.products.ProductsPage.*;
import static util.Constants.LOGIN_STANDARD_USER;
import static util.Constants.PASSWORD;

@Tag("Products")
public class ProductSortingTest extends BaseTest {

    @Test(testName = "Sorting products by price (low to high, high to low) and name (A-Z, Z-A)")
    @Description("Verify that products can be sorted by price in ascending and descending order. " +
            "Verify that products can be sorted by name in ascending and descending order")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Login as a standard user and verify that products page is opened", () -> {
            loginPage.login(LOGIN_STANDARD_USER, PASSWORD);

            assertThat(productsPage.getProductsListLocator()).isVisible();
        });

        step("3. Sort products by price in ascending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_PRICE_ASC);

            var expectedProductPrices = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductPrice)
                    .sorted()
                    .toList();
            var actualProductPrices = productsPage.getAllProductItems().stream()
                            .map(ProductItem::getProductPrice)
                            .toList();

            assertEquals("Products are sorted by price in ascending order",
                    expectedProductPrices, actualProductPrices);
        });

        step("4. Sort products by price in descending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_PRICE_DESC);

            var expectedProductPrices = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductPrice)
                    .sorted(reverseOrder())
                    .toList();
            var actualProductPrices = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductPrice)
                    .toList();

            assertEquals("Products are sorted by price in descending order",
                    expectedProductPrices, actualProductPrices);
        });

        step("5. Sort products by name in ascending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_NAME);

            var expectedProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductName)
                    .sorted()
                    .toList();
            var actualProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductName)
                    .toList();

            assertEquals("Products are sorted by name in ascending order",
                    expectedProductNames, actualProductNames);
        });

        step("6. Sort products by name in descending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_NAME_DESC);

            var expectedProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductName)
                    .sorted(reverseOrder())
                    .toList();
            var actualProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductItem::getProductName)
                    .toList();

            assertEquals("Products are sorted by name in descending order",
                    expectedProductNames, actualProductNames);
        });
    }
}
