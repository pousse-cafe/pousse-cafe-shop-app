package poussecafe.shop.core.model.comment;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.core.commands.UpdateComment;

public class UpdateRunner extends UpdateOneRunner<UpdateComment, CommentId, Comment.Root> {

    @Override
    protected CommentId aggregateId(UpdateComment message) {
        return message.commentId().value();
    }
}
