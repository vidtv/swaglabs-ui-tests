package login;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static page.login.LoginPage.USERNAME_IS_REQUIRED_ERROR;

@Tag("Login")
public class EmptyLoginPasswordTest extends BaseTest {

    // Test data
    private final String emptyString = "";

    @Test(testName = "Empty login and password fields",
            description = "Verify error message when login and password fields are empty")
    @Feature("Login")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Leave login and password fields empty, try to log in and verify that a correct error message is displayed", () -> {
            loginPage.login(emptyString, emptyString);

            assertThat(loginPage.getErrorNotification()).isVisible();
            assertThat(loginPage.getErrorNotification()).hasText(USERNAME_IS_REQUIRED_ERROR);
        });
    }
}
