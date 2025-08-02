package page.cart;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Cart page of the application.
 * </p>
 * It provides methods to interact with elements on the cart page,
 * such as retrieving cart items and buttons.
 */
public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    /**
     * Return a locator for the cart items displayed on the cart page.
     *
     * @return locator for the list of cart items
     */
    public List<CartItem> getCartItems() {
        return page.getByTestId("inventory-item").all()
                .stream()
                .map(item -> new CartItem(item))
                .toList();
    }

    /**
     * Return a locator for the "Continue Shopping" button on the cart page.
     *
     * @return locator for the "Continue Shopping" button
     */
    public Locator getContinueShoppingButton() {
        return page.getByTestId("continue-shopping");
    }

    /**
     * Return a locator for the "Checkout" button on the cart page.
     *
     * @return locator for the "Checkout" button
     */
    public Locator getCheckoutButton() {
        return page.getByTestId("checkout");
    }
}
