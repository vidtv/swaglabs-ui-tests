package page;

import com.microsoft.playwright.Page;

/**
 * ProductsPage class represents the products page of the application.
 * <p>
 * It contains locators for elements on the products page,
 * such as the list of products.
 */
public class ProductsPage {
    private final Page page;

    public final String productsList = ".inventory_list";

    public ProductsPage(Page page) {
        this.page = page;
    }
}
