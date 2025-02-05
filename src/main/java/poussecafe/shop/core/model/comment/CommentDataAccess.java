package poussecafe.shop.core.model.comment;

import poussecafe.domain.EntityDataAccess;
import poussecafe.shop.core.model.comment.Comment.Root;

public interface CommentDataAccess<D extends Root.Attributes> extends EntityDataAccess<CommentId, D> {
}
