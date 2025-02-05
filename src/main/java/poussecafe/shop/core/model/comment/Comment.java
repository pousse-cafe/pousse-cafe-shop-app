package poussecafe.shop.core.model.comment;

import poussecafe.attribute.Attribute;
import poussecafe.discovery.Aggregate;
import poussecafe.discovery.MessageListener;
import poussecafe.domain.AggregateFactory;
import poussecafe.domain.AggregateRepository;
import poussecafe.domain.AggregateRoot;
import poussecafe.domain.EntityAttributes;
import poussecafe.shop.core.commands.CreateComment;
import poussecafe.shop.core.commands.DeleteComment;
import poussecafe.shop.core.commands.UpdateComment;
import poussecafe.shop.core.process.CommentManagement;

@Aggregate
public class Comment {

    public static class Factory extends AggregateFactory<CommentId, Root, Root.Attributes> {

        @MessageListener(processes = CommentManagement.class)
        public Root newComment(CreateComment command) {
            var comment = newAggregateWithId(command.commentId().value());
            comment.attributes().metaData().valueOf(command.metaData());
            comment.attributes().text().valueOf(command.text());
            return comment;
        }
    }

    public static class Root extends AggregateRoot<CommentId, Root.Attributes> {

        @MessageListener(processes = CommentManagement.class, runner = UpdateRunner.class)
        public void update(UpdateComment command) {
            attributes().text().valueOf(command.newText());
        }

        public static interface Attributes extends EntityAttributes<CommentId> {

            Attribute<CommentMetaData> metaData();

            Attribute<String> text();
        }
    }

    public static class Repository extends AggregateRepository<CommentId, Root, Root.Attributes> {

        @MessageListener(processes = CommentManagement.class)
        public CommentId deleteComment(DeleteComment command) {
            return command.commentId().value();
        }

        @Override
        public CommentDataAccess<Root.Attributes> dataAccess() {
            return (CommentDataAccess<Root.Attributes>) super.dataAccess();
        }
    }

    private Comment() {
    }
}