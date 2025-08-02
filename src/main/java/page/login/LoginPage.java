package page.login;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static util.Constants.BASE_URL;

/**
 * A class that represents the login page of the application.
 * <p>
 * It provides methods to interact with the login form,
 * such as entering credentials and clicking the login button.
 */
public class LoginPage {
    private final Page page;

    // Error message texts
    public static final String INCORRECT_LOGIN_PASSWORD_ERROR = "Epic sadface: Username and password do not match any user in this service";
    public static final String USERNAME_IS_REQUIRED_ERROR = "Epic sadface: Username is required";
    public static final String USER_LOCKED_OUT_ERROR = "Epic sadface: Sorry, this user has been locked out.";
    public static final String YOU_CAN_ONLY_ACCESS_PRODUCTS_PAGE_AFTER_LOGGING_IN_ERROR =
            "Epic sadface: You can only access '/inventory.html' when you are logged in.";

    public LoginPage(Page page) {
        this.page = page;
    }

    /**
     * Navigate to the login page using the base URL.
     */
    public void navigate() {
        page.navigate(BASE_URL);
    }

    /**
     * Log in to the application with the provided username and password.
     *
     * @param username The username to log in with.
     * @param password The password to log in with.
     */
    public void login(String username, String password) {
        page.fill("#user-name", username);
        page.fill("#password", password);
        page.click("#login-button");
    }

    /**
     * Get a locator for the incorrect login/password error message displayed on the login page.
     * This is used to verify if an error occurs during login.
     */
    public Locator getErrorNotification() {
        return page.getByTestId("error");
    }

    /**
     * Get a locator for the login form on the login page.
     * This is used to verify if the login form is visible after logging out.
     *
     * @return Locator for the login form.
     */
    public Locator getLoginFormLocator() {
        return page.locator(".login-box");
    }
}