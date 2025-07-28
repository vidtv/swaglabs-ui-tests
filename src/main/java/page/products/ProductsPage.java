package page.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * ProductsPage class represents the products page of the application.
 * <p>
 * It contains locators for elements on the products page,
 * such as the list of products.
 */
public class ProductsPage {
    private final Page page;

    // Sorting options
    public static final String SORT_BY_NAME = "Name (A to Z)";
    public static final String SORT_BY_NAME_DESC = "Name (Z to A)";
    public static final String SORT_BY_PRICE_ASC = "Price (low to high)";
    public static final String SORT_BY_PRICE_DESC = "Price (high to low)";

    public ProductsPage(Page page) {
        this.page = page;
    }

    /**
     * Return the locator for the product list on the products page.
     *
     * @return locator for the product list
     */
    public Locator getProductsListLocator() {
        return page.getByTestId("inventory-list");
    }

    /**
     * Select a sorting option from the dropdown on the products page.
     *
     * @param optionName the name of the sorting option to select
     */
    public void selectSortingOption(String optionName) {
        page.getByTestId("product-sort-container").selectOption(optionName);
    }

    /**
     * Return a list of all product items displayed on the products page.
     *
     * @return list of {@link ProductItem} objects representing each product
     */
    public List<ProductItem> getAllProductItems() {
        return page.getByTestId("inventory-item").all()
                .stream()
                .map(item -> new ProductItem(item))
                .toList();
    }

    /**
     * Return a list of all product names displayed on the products page.
     *
     * @return list of product names
     */
    public List<String> getAllProductNames() {
        return getAllProductItems().stream()
                .map(ProductItem::getProductName)
                .toList();
    }
}
