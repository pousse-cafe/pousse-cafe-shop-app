package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.comment.CommentId;

public interface DeleteComment extends Command {

    Attribute<CommentId> commentId();
}
