package page;

import com.microsoft.playwright.Locator;

/**
 * A class that represents a single product item
 * on different pages of the application (cart and checkout overview pages).
 * <p>
 * It contains a locator for the product item, which can be used to interact with it,
 * and a name and a price of the product.
 */
public class ProductItem {
    protected final Locator locator;

    public ProductItem(Locator locator) {
        this.locator = locator;
    }

    /**
     * Returns the name of the product item.
     *
     * @return name of the product item
     */
    public String getProductName() {
        return locator.getByTestId("inventory-item-name").textContent();
    }

    /**
     * Returns the price of the product item.
     *
     * @return price of the product item
     */
    public Double getProductPrice() {
        var priceText = locator.getByTestId("inventory-item-price").textContent();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }
}
