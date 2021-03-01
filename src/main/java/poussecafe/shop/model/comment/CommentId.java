package poussecafe.shop.model.comment;

import poussecafe.domain.ValueObject;
import poussecafe.util.StringId;

public class CommentId extends StringId implements ValueObject {

    public CommentId(String value) {
        super(value);
    }
}
