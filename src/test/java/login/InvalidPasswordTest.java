package login;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static page.login.LoginPage.INCORRECT_LOGIN_PASSWORD_ERROR;
import static util.Constants.LOGIN_STANDARD_USER;

@Tag("Login")
public class InvalidPasswordTest extends BaseTest {

    // Test data
    private final String invalidPassword = "invalidPassword";

    @Test(testName = "Invalid password login attempt")
    @Description("Verify error message with an invalid password")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Enter invalid password and verify that a correct error message is displayed", () -> {
            loginPage.login(LOGIN_STANDARD_USER, invalidPassword);

            assertThat(loginPage.getErrorNotification()).isVisible();
            assertThat(loginPage.getErrorNotification()).hasText(INCORRECT_LOGIN_PASSWORD_ERROR);
        });
    }
}
