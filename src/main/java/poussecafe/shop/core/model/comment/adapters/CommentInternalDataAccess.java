package poussecafe.shop.core.model.comment.adapters;

import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.comment.CommentDataAccess;
import poussecafe.shop.core.model.comment.CommentId;
import poussecafe.shop.core.model.comment.Comment.Root;
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
