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

    /**
     * Return a locator of a cart badge displayed on the products page.
     * <br>
     * The locator is used to identify the badge that shows the number of items in the cart.
     *
     * @return locator of a cart badge
     */
    public Locator getCartBadgeLocator() {
        return page.getByTestId("shopping-cart-badge");
    }

    /**
     * Return a locator for the cart button on the products page.
     * <br>
     * The locator is used to identify the button that opens the shopping cart.
     *
     * @return locator for the cart button
     */
    public Locator getCartButtonLocator() {
        return page.locator("#shopping_cart_container");
    }

    /**
     * Logout from the application.
     * <p>
     * This method clicks on the burger menu button and selects the logout option.
     */
    public void logout() {
        page.locator("#react-burger-menu-btn").click();
        page.getByTestId("logout-sidebar-link").click();
    }

    /**
     * Get a product item by its name.
     *
     * @param productName the name of the product to find
     * @return the {@link ProductItem} object representing the product
     * @throws RuntimeException if the product with the specified name is not found
     */
    public ProductItem getProductItemByName(String productName) {
        return getAllProductItems().stream()
                .filter(item -> item.getProductName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with name '" + productName + "' not found"));
    }
}
