package poussecafe.shop.commands.adapters;

import java.io.Serializable;
import java.time.LocalDateTime;
import poussecafe.shop.model.comment.CommentMetaData;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.product.ProductId;

@SuppressWarnings("serial")
public class CommentMetaDataData implements Serializable {

    public static CommentMetaDataData adapt(CommentMetaData metaData) {
        CommentMetaDataData data = new CommentMetaDataData();
        data.creationDateTime = metaData.creationDateTime().toString();
        data.customerId = metaData.customerId().toString();
        data.productId = metaData.productId().toString();
        return data;
    }

    String creationDateTime;

    String customerId;

    String productId;

    public CommentMetaData adapt() {
        return new CommentMetaData.Builder()
                .creationDateTime(LocalDateTime.parse(creationDateTime))
                .customerId(new CustomerId(customerId))
                .productId(new ProductId(productId))
                .build();
    }
}
