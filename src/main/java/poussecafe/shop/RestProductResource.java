package poussecafe.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import poussecafe.runtime.Runtime;
import poussecafe.shop.command.AddUnits;
import poussecafe.shop.command.CreateProduct;
import poussecafe.shop.domain.Product;
import poussecafe.shop.domain.ProductId;
import poussecafe.shop.domain.ProductRepository;
import poussecafe.shop.view.AddUnitsView;
import poussecafe.shop.view.CreateProductView;
import poussecafe.shop.view.ProductView;

@RestController
public class RestProductResource {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Runtime runtime;

    @PostMapping(path = "/product")
    public void createProduct(@RequestBody CreateProductView input) {
        logger.info("Creating product with id {}", input.id);
        ProductId productId = new ProductId(input.id);

        CreateProduct command = runtime.newCommand(CreateProduct.class);
        command.productId().value(productId);
        runtime.submitCommand(command);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(path = "/product/{id}")
    public ProductView getProduct(@PathVariable("id") String id) {
        logger.info("Fetching product with id {}", id);
        ProductId productId = new ProductId(id);
        Product product = productRepository.get(productId);

        ProductView view = new ProductView();
        view.id = id;
        view.availableUnits = product.attributes().availableUnits().value();
        view.totalUnits = product.attributes().totalUnits().value();
        return view;
    }

    @PostMapping(path = "/product/{id}/add-units")
    public void addUnits(@PathVariable("id") String id, @RequestBody AddUnitsView input) {
        logger.info("Adding {} units to product with id {}", input.units, id);
        ProductId productId = new ProductId(id);
        AddUnits command = runtime.newCommand(AddUnits.class);
        command.productId().value(productId);
        command.units().value(input.units);
        runtime.submitCommand(command);
    }
}
