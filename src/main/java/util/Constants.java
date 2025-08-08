package util;

/**
 * Constants class to hold configuration values for the application.
 * It provides default values for login credentials and the base environment URL.
 */
public class Constants {

    /**
     * Base URL for the application.
     * Used to navigate to the login page and other application pages.
     */
    public static String BASE_URL = "https://www.saucedemo.com/";

    /**
     * URL for the products page.
     * This is constructed using the base URL and the specific path for the products page.
     */
    public static String PRODUCTS_PAGE_URL = BASE_URL + "inventory.html";

    /**
     * A username for a standard user.
     */
    public static final String LOGIN_STANDARD_USER = System.getenv("LOGIN_STANDARD_USER");

    /**
     * A username for a locked-out user.
     */
    public static final String LOGIN_LOCKED_OUT_USER = System.getenv("LOGIN_LOCKED_OUT_USER");

    /**
     * A password for the application.
     */
    public static String PASSWORD = System.getenv("PASSWORD");
}
