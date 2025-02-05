package poussecafe.shop.core.model.comment;

import poussecafe.annotations.Trivial;
import poussecafe.domain.ValueObject;
import poussecafe.util.StringId;

@Trivial
public class CommentId extends StringId implements ValueObject {

    public CommentId(String value) {
        super(value);
    }
}
