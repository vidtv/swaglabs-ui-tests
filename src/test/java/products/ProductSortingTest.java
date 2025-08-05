package products;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;
import page.products.ProductPageItem;

import static io.qameta.allure.Allure.step;
import static java.util.Comparator.reverseOrder;
import static org.testng.AssertJUnit.assertEquals;
import static page.products.ProductsPage.*;

@Tag("Products")
public class ProductSortingTest extends BaseTest {

    @Test(testName = "Sorting products by price (low to high, high to low) and name (A-Z, Z-A)")
    @Description("Verify that products can be sorted by price in ascending and descending order. " +
            "Verify that products can be sorted by name in ascending and descending order")
    public void test() {
        step("1. Open the login page, login as a standard user and verify that products page is opened",
                this::loginAsStandardUser
        );

        step("2. Sort products by price in ascending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_PRICE_ASC);

            var expectedProductPrices = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductPrice)
                    .sorted()
                    .toList();
            var actualProductPrices = productsPage.getAllProductItems().stream()
                            .map(ProductPageItem::getProductPrice)
                            .toList();

            assertEquals("Products are sorted by price in ascending order",
                    expectedProductPrices, actualProductPrices);
        });

        step("3. Sort products by price in descending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_PRICE_DESC);

            var expectedProductPrices = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductPrice)
                    .sorted(reverseOrder())
                    .toList();
            var actualProductPrices = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductPrice)
                    .toList();

            assertEquals("Products are sorted by price in descending order",
                    expectedProductPrices, actualProductPrices);
        });

        step("4. Sort products by name in ascending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_NAME);

            var expectedProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductName)
                    .sorted()
                    .toList();
            var actualProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductName)
                    .toList();

            assertEquals("Products are sorted by name in ascending order",
                    expectedProductNames, actualProductNames);
        });

        step("5. Sort products by name in descending order and verify that products are sorted correctly", () -> {
            productsPage.selectSortingOption(SORT_BY_NAME_DESC);

            var expectedProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductName)
                    .sorted(reverseOrder())
                    .toList();
            var actualProductNames = productsPage.getAllProductItems().stream()
                    .map(ProductPageItem::getProductName)
                    .toList();

            assertEquals("Products are sorted by name in descending order",
                    expectedProductNames, actualProductNames);
        });
    }
}
