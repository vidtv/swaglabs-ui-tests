package page.products;

import com.microsoft.playwright.Locator;

/**
 * A class that represents a single product item on the products page.
 * <p>
 * It contains a locator for the product item, which can be used to interact with it.
 */
public class ProductItem {
    private final Locator locator;

    public ProductItem(Locator locator) {
        this.locator = locator;
    }

    /**
     * Returns the locator for the product item.
     *
     * @return locator for the product item
     */
    public Locator getSelf() {
        return locator;
    }

    /**
     * Returns the name of the product item.
     *
     * @return name of the product item as a String
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

    /**
     * Returns the locator for the "Add to cart" button of the product item.
     *
     * @return locator for the "Add to cart" button
     */
    public Locator getAddToCartButton() {
        return locator.getByText("Add to cart");
    }
}
