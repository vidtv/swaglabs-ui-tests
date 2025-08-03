package page.checkout;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Checkout overview page in the application.
 * </p>
 * It provides locators for elements on the checkout overview page,
 * such as the page title, finish button, and list of items in the checkout.
 */
public class CheckoutOverviewPage {
    private final Page page;

    // Page title
    public static final String CHECKOUT_OVERVIEW_PAGE_TITLE = "Checkout: Overview";

    public CheckoutOverviewPage(Page page) {
        this.page = page;
    }

    /**
     * Get the locator for the checkout overview page title.
     *
     * @return locator for the checkout overview title
     */
    public Locator getPageTitle() {
        return page.getByTestId("title");
    }

    /**
     * Get the locator for the "Finish" button on the checkout overview page.
     *
     * @return locator for the "Finish" button
     */
    public Locator getFinishButton() {
        return page.getByTestId("finish");
    }

    /**
     * Return a locator for the items displayed on the checkout overview page.
     *
     * @return locator for the list of items
     */
    public List<CheckoutItem> getCheckoutItems() {
        return page.getByTestId("inventory-item").all()
                .stream()
                .map(item -> new CheckoutItem(item))
                .toList();
    }

    /**
     * Get the tax amount on the checkout overview page.
     *
     * @return tax amount as a Double
     */
    public Double getTaxAmount() {
        var taxText = page.getByTestId("tax-label").textContent();
        return Double.parseDouble(taxText.replace("Tax: $", "").trim());
    }

    /**
     * Get the total price on the checkout overview page.
     *
     * @return total price as a Double
     */
    public Double getTotalPrice() {
        var totalText = page.getByTestId("total-label").textContent();
        return Double.parseDouble(totalText.replace("Total: $", "").trim());
    }
}
