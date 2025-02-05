package poussecafe.shop.core.model.comment.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import poussecafe.discovery.DataAccessImplementation;
import poussecafe.shop.core.model.comment.CommentDataAccess;
import poussecafe.shop.core.model.comment.CommentId;
import poussecafe.shop.core.model.comment.Comment.Root;
import poussecafe.spring.jpa.storage.JpaDataAccess;
import poussecafe.spring.jpa.storage.SpringJpaStorage;

@DataAccessImplementation(aggregateRoot = Root.class, dataImplementation = CommentAttributes.class, storageName = SpringJpaStorage.NAME)
public class CommentSpringJpaDataAccess extends JpaDataAccess<CommentId, CommentAttributes, String>
        implements
            CommentDataAccess<CommentAttributes> {

    @Override
    protected String convertId(CommentId key) {
        return key.stringValue();
    }

    @Override
    protected CommentAttributesJpaRepository jpaRepository() {
        return repository;
    }

    @Autowired
    private CommentAttributesJpaRepository repository;
}
