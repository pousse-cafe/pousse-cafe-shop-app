package poussecafe.shop.core.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.core.commands.CreateComment;
import poussecafe.shop.core.model.comment.CommentId;
import poussecafe.shop.core.model.comment.CommentMetaData;

import static poussecafe.attribute.AttributeBuilder.single;
import static poussecafe.attribute.AttributeBuilder.stringId;

@MessageImplementation(message = CreateComment.class)
@SuppressWarnings("serial")
public class CreateCommentData implements Serializable, CreateComment {

    @Override
    public Attribute<CommentId> commentId() {
        return stringId(CommentId.class)
                .read(() -> commentId)
                .write(value -> commentId = value)
                .build();
    }

    private String commentId;

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
