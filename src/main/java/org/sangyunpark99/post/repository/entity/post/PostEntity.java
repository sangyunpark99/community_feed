package org.sangyunpark99.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;
import org.sangyunpark99.post.domain.Like;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.PostContent;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.repository.entity.UserEntity;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
public class PostEntity extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Convert(converter = PostStateConverter.class)
    private PostState state;

    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.content = post.getContentText();
        this.likeCount = post.getLikeCount();
        this.author = new UserEntity(post.getAuthor());
        this.state = state;
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .content(new PostContent(content))
                .state(state)
                .likeCount(new Like(likeCount))
                .build();
    }
}
