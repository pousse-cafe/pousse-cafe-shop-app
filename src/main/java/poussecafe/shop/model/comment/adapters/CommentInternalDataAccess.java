package poussecafe.shop.model.comment.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.model.comment.Comment.Root;
import poussecafe.shop.model.comment.CommentDataAccess;
import poussecafe.shop.model.comment.CommentId;
import poussecafe.storage.internal.InternalDataAccess;
import poussecafe.storage.internal.InternalStorage;

@DataAccessImplementation(
    aggregateRoot = Root.class,
    dataImplementation = CommentAttributes.class,
    storageName = InternalStorage.NAME
)
public class CommentInternalDataAccess
extends InternalDataAccess<CommentId, CommentAttributes>
implements CommentDataAccess<CommentAttributes> {

    public CommentInternalDataAccess() {
        versionField("version");
    }
}
