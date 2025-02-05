package poussecafe.shop.core.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.core.model.comment.CommentId;

public interface UpdateComment extends Command {

    Attribute<CommentId> commentId();

    Attribute<String> newText();
}
