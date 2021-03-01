package poussecafe.shop.model.comment;

import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import poussecafe.domain.ValueObject;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.product.ProductId;

import static java.util.Objects.requireNonNull;
import static poussecafe.util.Equality.referenceEquals;

public class CommentMetaData implements ValueObject {

    public LocalDateTime creationDateTime() {
        return creationDateTime;
    }

    private LocalDateTime creationDateTime;

    public CustomerId customerId() {
        return customerId;
    }

    private CustomerId customerId;

    public ProductId productId() {
        return productId;
    }

    private ProductId productId;

    public static class Builder {

        public CommentMetaData build() {
            requireNonNull(metaData.creationDateTime);
            requireNonNull(metaData.customerId);
            requireNonNull(metaData.productId);
            return metaData;
        }

        private CommentMetaData metaData = new CommentMetaData();

        public Builder creationDateTime(LocalDateTime creationDateTime) {
            metaData.creationDateTime = creationDateTime;
            return this;
        }

        public Builder customerId(CustomerId customerId) {
            metaData.customerId = customerId;
            return this;
        }

        public Builder productId(ProductId productId) {
            metaData.productId = productId;
            return this;
        }
    }

    private CommentMetaData() {

    }

    @Override
    public boolean equals(Object obj) {
        return referenceEquals(this, obj).orElse(other -> new EqualsBuilder()
                .append(creationDateTime, other.creationDateTime)
                .append(customerId, other.customerId)
                .append(productId, other.productId)
                .build());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(creationDateTime)
                .append(customerId)
                .append(productId)
                .build();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(creationDateTime)
                .append(customerId)
                .append(productId)
                .build();
    }
}
