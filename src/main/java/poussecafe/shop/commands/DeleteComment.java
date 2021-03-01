package poussecafe.shop.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.model.comment.CommentId;

public interface DeleteComment extends Command {

    Attribute<CommentId> commentId();
}
