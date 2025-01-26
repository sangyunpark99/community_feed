package org.sangyunpark99.post.repository.entity.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sangyunpark99.common.repository.entity.TimeBaseEntity;
import org.sangyunpark99.post.domain.Like;
import org.sangyunpark99.post.domain.comment.Comment;
import org.sangyunpark99.post.domain.content.CommentContent;
import org.sangyunpark99.post.repository.entity.post.PostEntity;
import org.sangyunpark99.user.repository.entity.UserEntity;

@Entity
@Table(name="community_comment")
@NoArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    private String content;

    private Integer likeCount;

    public CommentEntity(Comment comment) {
        this.id = comment.getId();
        this.likeCount = comment.getLikeCount();
        this.content = comment.getContent();
        this.post = new PostEntity(comment.getPost());
        this.user = new UserEntity(comment.getAuthor());
    }

    public Comment toComment() {
        return Comment.builder()
                .id(id)
                .post(post.toPost())
                .author(user.toUser())
                .content(new CommentContent(content))
                .likeManager(new Like(likeCount))
                .build();
    }
}
