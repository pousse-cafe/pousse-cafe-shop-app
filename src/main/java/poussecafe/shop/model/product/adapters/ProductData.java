package poussecafe.shop.model.product.adapters;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import poussecafe.attribute.Attribute;
import poussecafe.attribute.AttributeBuilder;
import poussecafe.shop.model.product.ProductRoot;
import poussecafe.shop.model.product.ProductId;

@SuppressWarnings("serial")
@Entity
public class ProductData implements ProductRoot.Attributes, Serializable {

    @Override
    public Attribute<ProductId> identifier() {
        return AttributeBuilder.stringId(ProductId.class)
                .read(() -> productId)
                .write(value -> productId = value)
                .build();
    }

    @Id
    private String productId;

    @Override
    public Attribute<Integer> totalUnits() {
        return AttributeBuilder.single(Integer.class)
                .read(() -> totalUnits)
                .write(value -> totalUnits = value)
                .build();
    }

    private int totalUnits;

    @Override
    public Attribute<Integer> availableUnits() {
        return AttributeBuilder.single(Integer.class)
                .read(() -> availableUnits)
                .write(value -> availableUnits = value)
                .build();
    }

    private int availableUnits;
}
