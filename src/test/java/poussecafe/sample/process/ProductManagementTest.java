package poussecafe.sample.process;

import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.commands.AddUnits;
import poussecafe.shop.commands.CreateProduct;
import poussecafe.shop.model.product.Product;
import poussecafe.shop.model.product.ProductId;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ProductManagementTest extends ShopTest {

    private ProductId productId;

    @Test
    public void productCanBeCreated() {
        givenProductId();
        whenSubmittingProductCreationCommand();
        thenProductCreated();
    }

    private void givenProductId() {
        productId = new ProductId("product-id");
    }

    private void whenSubmittingProductCreationCommand() {
        createProduct();
    }

    protected void createProduct() {
        CreateProduct command = newCommand(CreateProduct.class);
        command.productId().value(productId);
        submitCommand(command);
    }

    private void thenProductCreated() {
        assertTrue(getOptional(Product.class, productId).isPresent());
    }

    @Test
    public void unitsCanBeAdded() {
        givenProductWithZeroUnits();
        whenSubmittingAddUnitsCommand();
        thenProductHasAddedUnits();
    }

    private void givenProductWithZeroUnits() {
        givenProductId();
        createProduct();
    }

    private void whenSubmittingAddUnitsCommand() {
        addUnits = newCommand(AddUnits.class);
        addUnits.productId().value(productId);
        addUnits.units().value(1);
        submitCommand(addUnits);
    }

    private AddUnits addUnits;

    private void thenProductHasAddedUnits() {
        Product product = getOptional(Product.class, productId).orElseThrow();
        assertThat(product.attributes().availableUnits().value(), is(addUnits.units().value()));
        assertThat(product.attributes().totalUnits().value(), is(addUnits.units().value()));
    }
}
