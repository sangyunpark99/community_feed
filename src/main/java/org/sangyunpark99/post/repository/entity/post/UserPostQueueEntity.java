package org.sangyunpark99.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user_post_queue")
@NoArgsConstructor
@Getter
public class UserPostQueueEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long postId;

    private Long authorId;

    public UserPostQueueEntity(Long userId, Long postId, Long authorId) {
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
    }
}
