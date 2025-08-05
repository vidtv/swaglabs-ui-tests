package page.products;

import com.microsoft.playwright.Locator;

/**
 * A class that represents a single product item on the products page.
 * <p>
 * It contains a locator for the product item, which can be used to interact with it,
 * and buttons for adding and removal products.
 */
public class ProductPageItem extends page.ProductItem {

    /**
     * Constructor for ProductPageItem.
     *
     * @param locator Locator for the product item
     */
    public ProductPageItem(Locator locator) {
        super(locator);
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
     * Returns the locator for the "Add to cart" button of the product item.
     *
     * @return locator for the "Add to cart" button
     */
    public Locator getAddToCartButton() {
        return locator.getByText("Add to cart");
    }

    /**
     * Returns the locator for the "Remove" button of the product item.
     * <br/>
     * This button is displayed if a product has been added to cart.
     *
     * @return locator for the "Remove" button
     */
    public Locator getRemoveFromCartButton() {
        return locator.getByText("Remove");
    }
}
