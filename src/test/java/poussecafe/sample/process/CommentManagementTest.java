package poussecafe.sample.process;

import java.time.LocalDateTime;
import org.junit.Test;
import poussecafe.sample.ShopTest;
import poussecafe.shop.commands.CreateComment;
import poussecafe.shop.commands.DeleteComment;
import poussecafe.shop.commands.UpdateComment;
import poussecafe.shop.model.comment.Comment;
import poussecafe.shop.model.comment.CommentId;
import poussecafe.shop.model.comment.CommentMetaData;
import poussecafe.shop.model.customer.CustomerId;
import poussecafe.shop.model.product.ProductId;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CommentManagementTest extends ShopTest {

    @Test
    public void commentCreation() {
        givenCreateCommentCommand();
        when(createComment);
        thenCommentExists(true);
        thenCommentHasMetaData(expectedMetaData);
        thenCommentHasText(expectedText);
    }

    private void givenCreateCommentCommand() {
        createComment = newCommand(CreateComment.class);
        createComment.commentId().value(commentId);
        createComment.metaData().value(expectedMetaData);
        createComment.text().value(expectedText);
    }

    private CommentId commentId = new CommentId("commentId");

    private CommentMetaData expectedMetaData = new CommentMetaData.Builder()
            .creationDateTime(LocalDateTime.now())
            .customerId(new CustomerId("customerId"))
            .productId(new ProductId("productId"))
            .build();

    private String expectedText = "Some text.";

    private CreateComment createComment;

    private void thenCommentExists(boolean expected) {
        var comment = commentRepository.getOptional(commentId);
        assertTrue(comment.isPresent() == expected);
    }

    private Comment.Repository commentRepository;

    private void thenCommentHasMetaData(CommentMetaData expectedMetaData) {
        var comment = commentRepository.get(commentId);
        assertThat(comment.attributes().metaData().value(), equalToObject(expectedMetaData));
    }

    private void thenCommentHasText(String expectedText) {
        var comment = commentRepository.get(commentId);
        assertThat(comment.attributes().text().value(), equalToObject(expectedText));
    }

    @Test
    public void commentUpdate() {
        givenExistingComment();
        givenUpdateCommentCommand();
        when(updateComment);
        thenCommentHasText(newText);
    }

    private void givenExistingComment() {
        givenCreateCommentCommand();
        submitCommand(createComment);
    }

    private void givenUpdateCommentCommand() {
        updateComment = newCommand(UpdateComment.class);
        updateComment.commentId().value(commentId);
        updateComment.newText().value(newText);
    }

    private String newText = "Another text.";

    private UpdateComment updateComment;

    @Test
    public void commentDeletion() {
        givenExistingComment();
        givenDeleteCommentCommand();
        when(deleteComment);
        thenCommentExists(false);
    }

    private void givenDeleteCommentCommand() {
        deleteComment = newCommand(DeleteComment.class);
        deleteComment.commentId().value(commentId);
    }

    private DeleteComment deleteComment;
}
