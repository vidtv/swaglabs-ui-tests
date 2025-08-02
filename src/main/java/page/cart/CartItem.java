package page.cart;

import com.microsoft.playwright.Locator;

/**
 * A class that represents a single item in the shopping cart.
 * <p>
 * It contains a locator for the cart item, which can be used to interact with it.
 */
public class CartItem {
    private final Locator item;

    /**
     * Constructor for CartItem.
     *
     * @param item Locator for the cart item element.
     */
    public CartItem(Locator item) {
        this.item = item;
    }

    /**
     * Return the locator for the name of the cart item.
     *
     * @return locator for the cart item's name
     */
    public Locator getProductName() {
        return item.getByTestId("inventory-item-name");
    }
}
