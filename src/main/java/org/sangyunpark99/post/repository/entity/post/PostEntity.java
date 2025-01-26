package org.sangyunpark99.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;
import org.sangyunpark99.post.domain.Like;
import org.sangyunpark99.post.domain.Post;
import org.sangyunpark99.post.domain.content.PostContent;
import org.sangyunpark99.post.domain.content.PostState;
import org.sangyunpark99.user.repository.entity.UserEntity;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@DynamicUpdate
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Convert(converter = PostStateConverter.class)
    private PostState state;

    private Integer likeCount;

    @ColumnDefault("0")
    private int commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.content = post.getContentText();
        this.likeCount = post.getLikeCount();
        this.author = new UserEntity(post.getAuthor());
        this.state = post.getState();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .content(new PostContent(content))
                .state(state)
                .author(author.toUser())
                .likeCount(new Like(likeCount))
                .build();
    }
}
