package org.sangyunpark99.post.repository.entity.like;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.user.domain.User;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser) { // Post인 경우
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(),LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser) { // Comment인 경우
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }
}
