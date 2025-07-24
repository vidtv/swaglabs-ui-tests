package page;

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
}