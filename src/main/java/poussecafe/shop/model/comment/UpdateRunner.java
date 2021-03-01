package poussecafe.shop.model.comment;

import poussecafe.listeners.UpdateOneRunner;
import poussecafe.shop.commands.UpdateComment;

public class UpdateRunner extends UpdateOneRunner<UpdateComment, CommentId, Comment.Root> {

    @Override
    protected CommentId aggregateId(UpdateComment message) {
        return message.commentId().value();
    }
}
