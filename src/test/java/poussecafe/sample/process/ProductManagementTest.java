package poussecafe.sample.process;

import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.commands.AddUnits;
import poussecafe.shop.commands.CreateProduct;
import poussecafe.shop.model.product.Product;
import poussecafe.shop.model.product.ProductId;
import poussecafe.shop.process.ProductManagement;
import poussecafe.test.ProcessCovered;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@ProcessCovered(ProductManagement.class)
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
        assertTrue(productRepository.getOptional(productId).isPresent());
    }

    private Product.Repository productRepository;

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
        Product.Root product = productRepository.get(productId);
        assertThat(product.attributes().availableUnits().value(), is(addUnits.units().value()));
        assertThat(product.attributes().totalUnits().value(), is(addUnits.units().value()));
    }
}
