package poussecafe.shop.core.model.comment.adapters;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import poussecafe.attribute.Attribute;
import poussecafe.shop.core.commands.adapters.CommentMetaDataData;
import poussecafe.shop.core.model.comment.CommentId;
import poussecafe.shop.core.model.comment.CommentMetaData;
import poussecafe.shop.core.model.comment.Comment.Root;

import static poussecafe.attribute.AttributeBuilder.single;
import static poussecafe.attribute.AttributeBuilder.stringId;

@SuppressWarnings("serial")
@Entity
public class CommentAttributes implements Serializable, Root.Attributes {

    @Override
    public Attribute<CommentId> identifier() {
        return stringId(CommentId.class)
                .read(() -> identifier)
                .write(value -> identifier = value)
                .build();
    }

    @Id
    private String identifier;

    @Version
    private Long version;

    @Override
    public Attribute<CommentMetaData> metaData() {
        return single(CommentMetaData.class)
                .usingAutoAdapter(CommentMetaDataData.class)
                .read(() -> metaData)
                .write(value -> metaData = value)
                .build();
    }

    private CommentMetaDataData metaData;

    @Override
    public Attribute<String> text() {
        return single(String.class)
                .read(() -> text)
                .write(value -> text = value)
                .build();
    }

    private String text;
}