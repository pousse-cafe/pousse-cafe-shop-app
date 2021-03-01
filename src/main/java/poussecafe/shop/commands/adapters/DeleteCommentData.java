package poussecafe.shop.commands.adapters;

import java.io.Serializable;
import poussecafe.attribute.Attribute;
import poussecafe.discovery.MessageImplementation;
import poussecafe.shop.commands.DeleteComment;
import poussecafe.shop.model.comment.CommentId;

import static poussecafe.attribute.AttributeBuilder.stringId;

@MessageImplementation(message = DeleteComment.class)
@SuppressWarnings("serial")
public class DeleteCommentData implements Serializable, DeleteComment {

    @Override
    public Attribute<CommentId> commentId() {
        return stringId(CommentId.class)
                .read(() -> commentId)
                .write(value -> commentId = value)
                .build();
    }

    private String commentId;
}
