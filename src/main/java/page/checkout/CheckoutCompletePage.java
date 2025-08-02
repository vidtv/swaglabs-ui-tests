package page.checkout;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Checkout complete page in the application.
 * </p>
 * It provides locators of checkout complete page elements,
 * such as title, completion message header, and completion message.
 */
public class CheckoutCompletePage {
    private final Page page;

    // Page title
    public static final String CHECKOUT_COMPLETE_PAGE_TITLE = "Checkout: Complete!";

    // Completion message
    public static final String CHECKOUT_COMPLETE_MESSAGE_HEADER = "Thank you for your order!";
    public static final String CHECKOUT_COMPLETE_MESSAGE = "Your order has been dispatched, " +
            "and will arrive just as fast as the pony can get there!";

    public CheckoutCompletePage(Page page) {
        this.page = page;
    }

    /**
     * Get the locator for the checkout complete page title.
     *
     * @return locator for the checkout complete title
     */
    public Locator getCheckoutCompleteTitle() {
        return page.getByTestId("title");
    }

    /**
     * Get the locator for the checkout complete message header.
     *
     * @return locator for the checkout complete message header
     */
    public Locator getCheckoutCompleteMessageHeader() {
        return page.getByTestId("complete-header");
    }

    /**
     * Get the locator for the checkout complete message.
     *
     * @return locator for the checkout complete message
     */
    public Locator getCheckoutCompleteMessage() {
        return page.getByTestId("complete-text");
    }
}
