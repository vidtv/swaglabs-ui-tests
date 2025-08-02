package login;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static io.qameta.allure.Allure.step;
import static page.login.LoginPage.USER_LOCKED_OUT_ERROR;
import static util.Constants.LOGIN_LOCKED_OUT_USER;
import static util.Constants.PASSWORD;

@Tag("Login")
public class LockedOutUserTest extends BaseTest {

    @Test(testName = "Login attempt with locked out user")
    @Description("Verify error message when trying to log in with a locked out user")
    public void test() {
        step("1. Open the login page",
                loginPage::navigate
        );

        step("2. Enter locked out user credentials and verify that a correct error message is displayed", () -> {
            loginPage.login(LOGIN_LOCKED_OUT_USER, PASSWORD);

            assertThat(loginPage.getErrorNotification()).isVisible();
            assertThat(loginPage.getErrorNotification()).hasText(USER_LOCKED_OUT_ERROR);
        });
    }
}
