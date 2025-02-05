package poussecafe.shop.core.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.core.commands.UpdateComment;
import poussecafe.shop.core.model.comment.CommentId;

import static poussecafe.attribute.AttributeBuilder.single;
import static poussecafe.attribute.AttributeBuilder.stringId;

@MessageImplementation(message = UpdateComment.class)
@SuppressWarnings("serial")
public class UpdateCommentData implements Serializable, UpdateComment {

    @Override
    public Attribute<CommentId> commentId() {
        return stringId(CommentId.class)
                .read(() -> commentId)
                .write(value -> commentId = value)
                .build();
    }

    private String commentId;

    @Override
    public Attribute<String> newText() {
        return single(String.class)
                .read(() -> newText)
                .write(value -> newText = value)
                .build();
    }

    private String newText;
}
