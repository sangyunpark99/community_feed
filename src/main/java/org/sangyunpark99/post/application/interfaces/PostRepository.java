package org.sangyunpark99.post.application.interfaces;

import org.sangyunpark99.post.domain.Post;

import java.util.Optional;

public interface PostRepository  {

    Post save(Post post);

    Post findById(Long id);
}
