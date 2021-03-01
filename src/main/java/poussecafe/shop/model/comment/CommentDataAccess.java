package poussecafe.shop.model.comment;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.model.comment.Comment.Root;

public interface CommentDataAccess<D extends Root.Attributes> extends EntityDataAccess<CommentId, D> {
}
