package poussecafe.shop.commands;

import poussecafe.attribute.Attribute;
import poussecafe.runtime.Command;
import poussecafe.shop.model.comment.CommentId;
import poussecafe.shop.model.comment.CommentMetaData;

public interface CreateComment extends Command {

    Attribute<CommentId> commentId();

    Attribute<String> text();

    Attribute<CommentMetaData> metaData();
}
