package page.cart;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    /**
     * Returns a locator for the cart items displayed on the cart page.
     *
     * @return locator for the cart items
     */
    public List<CartItem> getCartItems() {
        return page.getByTestId("inventory-item").all()
                .stream()
                .map(item -> new CartItem(item))
                .toList();
    }

    public Locator getContinueShoppingButton() {
        return page.getByTestId("continue-shopping");
    }
}
