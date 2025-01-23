package org.sangyunpark99.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import org.sangyunpark99.post.domain.content.PostState;

public class PostStateConverter implements AttributeConverter<PostState, String> {
    @Override
    public String convertToDatabaseColumn(PostState postState) {
        return postState.name();
    }

    @Override
    public PostState convertToEntityAttribute(String s) {
        return PostState.valueOf(s);
    }
}
