package page.checkout;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Checkout page of the application.
 * <p>
 * It contains locators for elements on the checkout page,
 * such as the "Continue" button, input fields, and error notification.
 */
public class CheckoutPage {
    private final Page page;

    // Error notification text
    public static final String FIRST_NAME_IS_REQUIRED_ERROR = "Error: First Name is required";

    public CheckoutPage(Page page) {
        this.page = page;
    }

    /**
     * Get the locator for the first name input field.
     *
     * @return locator for the first name input
     */
    public Locator getFirstNameInput() {
        return page.getByTestId("firstName");
    }

    /**
     * Get the locator for the last name input field.
     *
     * @return locator for the last name input
     */
    public Locator getLastNameInput() {
        return page.getByTestId("lastName");
    }

    /**
     * Get the locator for the postal code input field.
     *
     * @return locator for the postal code input
     */
    public Locator getPostalCodeInput() {
        return page.getByTestId("postalCode");
    }

    /**
     * Get the locator for the "Continue" button on the checkout page.
     *
     * @return locator for the "Continue" button
     */
    public Locator getContinueButton() {
        return page.getByTestId("continue");
    }

    /**
     * Get the locator for the error notification on the checkout page.
     * </br>
     * This notification appears when there is an error in the input fields
     * (e.g., when the first name is not provided).
     *
     * @return locator for the error notification
     */
    public Locator getErrorNotification() {
        return page.getByTestId("error");
    }
}
