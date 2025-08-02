package page.checkout;

import com.microsoft.playwright.Locator;

/**
 * An item in the checkout process.
 * </p>
 * This class provides a method to retrieve the product name of the checkout item.
 */
public class CheckoutItem {
    private final Locator item;

    /**
     * Constructor for CheckoutItem.
     *
     * @param item locator for the checkout item element
     */
    public CheckoutItem(Locator item) {
        this.item = item;
    }

    /**
     * Get the locator for the product name of the checkout item.
     *
     * @return locator for the product name
     */
    public Locator getProductName() {
        return item.getByTestId("inventory-item-name");
    }
}
