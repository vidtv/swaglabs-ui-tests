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
     * A username for the application.
     * Default value is set to "invalidUsername" if not specified via system property.
     */
    public static final String LOGIN = System.getProperty("LOGIN", "invalidUsername");

    /**
     * A password for the application.
     * Default value is set to "invalidPassword" if not specified via system property.
     */
    public static String PASSWORD = System.getProperty("PASSWORD", "invalidPassword");
}
